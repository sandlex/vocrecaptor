package com.vocrecaptor.web.client.remote;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.vocrecaptor.web.client.remote.transferobjects.DictionaryTransferObject;
import com.vocrecaptor.web.client.remote.transferobjects.WordsPage;

@RemoteServiceRelativePath("dictionaryService")
public interface DictionaryService extends RemoteService {

	//create, delete, find (returns flag), get (returns TO), getAll, count
	
	Long create(DictionaryTransferObject dictionary);
	
	List<DictionaryTransferObject> getByUser(Long user);
	
	List<DictionaryTransferObject> getPublic();
	
	Long delete(DictionaryTransferObject dictionary);
	
	Integer count();
	
	DictionaryTransferObject get(Long id);
	
	WordsPage getWordsPage(Long dictId, Integer page);
	
}
