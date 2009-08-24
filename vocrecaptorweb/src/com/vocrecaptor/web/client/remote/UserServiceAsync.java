package com.vocrecaptor.web.client.remote;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
	
	void find(String login, AsyncCallback<Boolean> callback);
	
	void create(String login, String password, AsyncCallback<Long> callback);
	
	void find(String login, String password, AsyncCallback<Long> callback);
	
	void count(AsyncCallback<Integer> callback);
	
	
}
