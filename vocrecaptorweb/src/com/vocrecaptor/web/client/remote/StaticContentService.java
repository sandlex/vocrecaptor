package com.vocrecaptor.web.client.remote;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("staticContentService")
public interface StaticContentService extends RemoteService {
	
	String getHtmlContent(String fileName);
}
