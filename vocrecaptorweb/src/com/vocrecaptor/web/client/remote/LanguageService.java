package com.vocrecaptor.web.client.remote;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.vocrecaptor.web.client.remote.transferobjects.LanguageTransferObject;

@RemoteServiceRelativePath("languageService")
public interface LanguageService extends RemoteService {

	List<LanguageTransferObject> getAll();
	
	Integer createTestData();
	
	Integer count();
	
}
