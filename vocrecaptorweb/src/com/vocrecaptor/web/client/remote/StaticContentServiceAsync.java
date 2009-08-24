package com.vocrecaptor.web.client.remote;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface StaticContentServiceAsync {

	void getHtmlContent(String fileName, AsyncCallback<String> callback);
}
