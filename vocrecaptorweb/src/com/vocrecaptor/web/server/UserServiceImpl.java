package com.vocrecaptor.web.server;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.vocrecaptor.web.client.model.User;
import com.vocrecaptor.web.client.remote.UserService;
import com.vocrecaptor.web.server.jdo.PMF;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class UserServiceImpl extends RemoteServiceServlet implements
		UserService {
	
	private PersistenceManager pm = PMF.get().getPersistenceManager();
	
	@Override
	public Boolean checkLogin(String login) {
		
		Query query = pm.newQuery(User.class);
	    query.setFilter("login == loginParam");
	    query.declareParameters("String loginParam");

	    try {
	        List<User> results = (List<User>) query.execute(login);
	        
	        return results.isEmpty();
	    } finally {
	        query.closeAll();
	    }		
	}

	
	@Override
	public Integer register(String login, String password) {

		User user = new User(login, password);

        try {
            pm.makePersistent(user);
        } finally {
            pm.close();
        }
        
		return 0;
	}
	
	
	
	@Override
	public Integer login(String login, String password) {
		
		Query query = pm.newQuery(User.class);
	    query.setFilter("login == loginParam");
	    query.declareParameters("String loginParam");
	    List<User> results;

	    try {
	        results = (List<User>) query.execute(login);

	        if (results.isEmpty()) {
	        	return 1;
	        }
	    } finally {
	        query.closeAll();
	    }		

	    if (!password.equals(results.get(0).getPassword())) {
	    	return 2;
	    }
	    
	    return 0;
	}


	@Override
	public Integer getUserCount() {

		Query query = pm.newQuery(User.class);
	    List<User> results;

	    try {
	        results = (List<User>) query.execute();
	    } finally {
	        query.closeAll();
	    }		

	    return results.size();
	}
	
}
