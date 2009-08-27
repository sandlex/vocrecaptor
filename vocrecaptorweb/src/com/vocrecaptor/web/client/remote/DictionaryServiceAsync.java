package com.vocrecaptor.web.client.remote;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.vocrecaptor.web.client.remote.transferobjects.DictionaryTransferObject;

public interface DictionaryServiceAsync {

	void create(DictionaryTransferObject dictionary, AsyncCallback<Long> callback);

	void getByUser(Long user, AsyncCallback<List<DictionaryTransferObject>> callback);

	void delete(DictionaryTransferObject dictionary, AsyncCallback<Long> callback);

	void count(AsyncCallback<Integer> callback);

	void get(Long id, AsyncCallback<DictionaryTransferObject> callback);
}
