package com.vocrecaptor.web.client.remote;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.vocrecaptor.web.client.remote.transferobjects.LanguageTransferObject;

public interface LanguageServiceAsync {

	void getAll(AsyncCallback<List<LanguageTransferObject>> callback);
	
	void createTestData(AsyncCallback<Integer> callback);
	
	void count(AsyncCallback<Integer> callback);

	void deleteAll(AsyncCallback<Integer> callback);

}
