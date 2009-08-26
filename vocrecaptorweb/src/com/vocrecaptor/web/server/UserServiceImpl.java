package com.vocrecaptor.web.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.vocrecaptor.web.client.remote.UserService;
import com.vocrecaptor.web.client.remote.transferobjects.UserTransferObject;
import com.vocrecaptor.web.server.jdo.User;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class UserServiceImpl extends RemoteServiceServlet implements
		UserService {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
			
		UserTransferObject userTO = (UserTransferObject) request.getSession().getAttribute("userToLogin");
		System.out.println(userTO.getLogin() + ":" + userTO.getPassword());
		request.getSession().removeAttribute("userToLogin");
		
		out.print(find(userTO.getLogin(), userTO.getPassword()));
		out.close();
	}

	//TODO Case-insensitive user names i.e. vocrecaptor=Vocrecaptor 
	
	@Override
	public Boolean find(String login) {

		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		try {
			Query query = pm.newQuery(User.class);
			query.setFilter("login == loginParam");
			query.declareParameters("String loginParam");

			List<User> results = (List<User>) query.execute(login);

			return results.isEmpty();
		} finally {
			if (pm.currentTransaction().isActive()) {
				pm.currentTransaction().rollback();
			}
		}
	}

	@Override
	public Long create(String login, String password) {

		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		try {
			pm.currentTransaction().begin();
			User user = new User(login, password);
			Long id = pm.makePersistent(user).getId();
			pm.currentTransaction().commit();

			return id;
		} finally {
			if (pm.currentTransaction().isActive()) {
				pm.currentTransaction().rollback();
			}
		}
	}

	@Override
	public Long find(String login, String password) {

		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		try {
			Query query = pm.newQuery(User.class);
			query.setFilter("login == loginParam");
			query.declareParameters("String loginParam");
			List<User> results = (List<User>) query.execute(login);

			if (results.isEmpty()) {
				return -1L;
			}

			if (!password.equals(results.get(0).getPassword())) {
				return -2L;
			}

			return results.get(0).getId();
		} finally {
			if (pm.currentTransaction().isActive()) {
				pm.currentTransaction().rollback();
			}
		}
	}

	@Override
	public Integer count() {

		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		try {
			Query query = pm.newQuery(User.class);
			List<User> results = (List<User>) query.execute();

			return results.size();
		} finally {
			if (pm.currentTransaction().isActive()) {
				pm.currentTransaction().rollback();
			}
		}
	}

}
