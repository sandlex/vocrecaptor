package com.vocrecaptor.web.client.remote;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("userService")
public interface UserService extends RemoteService {
	
	/**
	 * Checks if specified login is available for registration.
	 * @param login login to check
	 * @return true if available (user with such login doesn't exist in database)
	 * otherwise false
	 */
	Boolean find(String login);

	/**
	 * Registers an user.
	 * @param user User object with parameters
	 * @return user id
	 */
	Long create(String login, String password);

	
	Long find(String login, String password);
	
	
	/**
	 * Returns a number of registered users.
	 * @return a number of registered users
	 */
	Integer count();
	
}
