package com.vocrecaptor.web.client.remote;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
	
	void checkLogin(String login, AsyncCallback<Boolean> callback);
	
	void register(String login, String password, AsyncCallback<Integer> callback);
	
	void login(String login, String password, AsyncCallback<Integer> callback);
	
	void getUserCount(AsyncCallback<Integer> callback);
	
	
}
