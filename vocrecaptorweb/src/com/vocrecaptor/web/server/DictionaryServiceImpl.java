package com.vocrecaptor.web.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.vocrecaptor.web.client.remote.DictionaryService;
import com.vocrecaptor.web.client.remote.transferobjects.DictionaryTransferObject;
import com.vocrecaptor.web.server.jdo.Dictionary;

@SuppressWarnings("serial")
public class DictionaryServiceImpl extends RemoteServiceServlet implements
		DictionaryService {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
			
		System.out.println(request.getSession().getId());
		DictionaryTransferObject dictionaryTO = (DictionaryTransferObject) request.getSession().getAttribute("dictionary"); 
		request.getSession().removeAttribute("dictionary");
		
		out.print(create(dictionaryTO));
		out.close();
	}
	
	@Override
	public Long create(DictionaryTransferObject dictionaryTO) {
		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		try {
			pm.currentTransaction().begin();
			Dictionary dictionary = dictionaryDTOToDictionary(dictionaryTO);
			//FIXME java.lang.IllegalArgumentException: file: byte[] properties must be 500 bytes or less.  Instead, use com.google.appengine.api.datastore.Blob, which can store binary data of any size.
			Long result = pm.makePersistent(dictionary).getId();
			pm.currentTransaction().commit();

			return result;
		} finally {
			if (pm.currentTransaction().isActive()) {
				pm.currentTransaction().rollback();
			}
		}
	}

	@Override
	public Long delete(DictionaryTransferObject dictionary) {
		return null;
	}

	@Override
	public List<DictionaryTransferObject> getByUser(Long user) {
		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		try {
			Query query = pm.newQuery(Dictionary.class);
			query.setFilter("user == userParam");
			query.declareParameters("Long userParam");
			
			List<Dictionary> dictionaries = (List<Dictionary>) query.execute(user);

			List<DictionaryTransferObject> result = new ArrayList<DictionaryTransferObject>(
					dictionaries.size());
			for (Dictionary dictionary : dictionaries) {
				result.add(dictionaryToDTO(dictionary));
			}

			return result;
		} finally {
			//FIXME Check all these cases and remove rollback
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
			Query query = pm.newQuery(Dictionary.class);
			List<Dictionary> results = (List<Dictionary>) query.execute();

			return results.size();
		} finally {
			if (pm.currentTransaction().isActive()) {
				pm.currentTransaction().rollback();
			}
		}
	}
	
	private DictionaryTransferObject dictionaryToDTO(Dictionary dictionary) {
		return new DictionaryTransferObject(dictionary.getId(), dictionary.getUser(), dictionary.getLearningLanguage(), 
				dictionary.getThroughLanguage(), dictionary.getIsPublic(), dictionary.getDescription(), dictionary.getFile());
	}
	
	private Dictionary dictionaryDTOToDictionary(DictionaryTransferObject dictionary) {
		return new Dictionary(dictionary.getId(), dictionary.getUser(), dictionary.getLearningLanguage(), 
				dictionary.getThroughLanguage(), dictionary.getIsPublic(), dictionary.getDescription(), dictionary.getFile());
	}
	
}
