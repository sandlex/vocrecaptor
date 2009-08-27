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
import com.vocrecaptor.web.server.utils.DictionaryFileUtil;
import com.vocrecaptor.web.server.utils.WordBean;

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

		if (request.getSession().getAttribute("dictionary") != null) {
			out.print(processCreateDictionary(request));
			out.close();
			return;
		}

		if (request.getSession().getAttribute("userToGetDictionaries") != null) {
			response.setContentType("text/xml");
			out.print(processGetDictionaries(request, "userToGetDictionaries"));
			out.close();
			return;
		}

		if (request.getSession().getAttribute("dictionaryToGetCategories") != null) {
			response.setContentType("text/xml");
			out
					.print(processGetCategories(request,
							"dictionaryToGetCategories"));
			out.close();
			return;
		}

		if (request.getSession().getAttribute("dictionaryToGetSessions") != null) {
			response.setContentType("text/xml");
			out.print(processGetSessions(request, "dictionaryToGetSessions"));
			out.close();
			return;
		}

		if (request.getSession().getAttribute("dictionaryToGetDictionary") != null) {
			response.setContentType("text/xml");
			out
					.print(processGetDictionary(request,
							"dictionaryToGetDictionary"));
			out.close();
			return;
		}

	}

	@Override
	public Long create(DictionaryTransferObject dictionaryTO) {
		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		pm.currentTransaction().begin();
		Dictionary dictionary = dictionaryDTOToDictionary(dictionaryTO);
		// FIXME java.lang.IllegalArgumentException: file: byte[] properties
		// must be 500 bytes or less. Instead, use
		// com.google.appengine.api.datastore.Blob, which can store binary
		// data of any size.
		Long result = pm.makePersistent(dictionary).getId();
		pm.currentTransaction().commit();

		return result;
	}

	@Override
	public Long delete(DictionaryTransferObject dictionary) {
		return null;
	}

	@Override
	public List<DictionaryTransferObject> getByUser(Long user) {
		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

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
	}

	@Override
	public Integer count() {

		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		Query query = pm.newQuery(Dictionary.class);
		List<Dictionary> results = (List<Dictionary>) query.execute();

		return results.size();
	}

	@Override
	public DictionaryTransferObject get(Long id) {

		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		Query query = pm.newQuery(Dictionary.class);
		query.setFilter("id == idParam");
		query.declareParameters("Long idParam");

		List<Dictionary> dictionaries = (List<Dictionary>) query.execute(id);
		// FIXME Ensure that there is only one object with such id

		List<DictionaryTransferObject> result = new ArrayList<DictionaryTransferObject>(
				dictionaries.size());
		for (Dictionary dictionary : dictionaries) {
			result.add(dictionaryToDTO(dictionary));
		}

		return result.get(0);
	}

	private DictionaryTransferObject dictionaryToDTO(Dictionary dictionary) {
		return new DictionaryTransferObject(dictionary.getId(), dictionary
				.getUser(), dictionary.getLearningLanguage(), dictionary
				.getThroughLanguage(), dictionary.getIsPublic(), dictionary
				.getDescription(), dictionary.getFile());
	}

	private Dictionary dictionaryDTOToDictionary(
			DictionaryTransferObject dictionary) {
		return new Dictionary(dictionary.getId(), dictionary.getUser(),
				dictionary.getLearningLanguage(), dictionary
						.getThroughLanguage(), dictionary.getIsPublic(),
				dictionary.getDescription(), dictionary.getFile());
	}

	private Object processCreateDictionary(HttpServletRequest request) {
		DictionaryTransferObject dictionaryTO = (DictionaryTransferObject) request
				.getSession().getAttribute("dictionary");
		System.out.println("+++++++++++++++++++++++++ Why not all records?" + dictionaryTO.getFile().length);
		request.getSession().removeAttribute("dictionary");

		return create(dictionaryTO);
	}

	private Object processGetDictionaries(HttpServletRequest request,
			String attr) {
		String userId = (String) request.getSession().getAttribute(attr);
		request.getSession().removeAttribute(attr);

		List<DictionaryTransferObject> dictionaries = getByUser(Long
				.valueOf(userId));
		StringBuffer result = new StringBuffer();

		if (dictionaries.size() > 0) {
			result.append("<list>");
			for (DictionaryTransferObject dictionary : dictionaries) {
				result.append("<dict><id>");
				result.append(dictionary.getId());
				result.append("</id><desc>");
				result.append(dictionary.getDescription());
				result.append("</desc></dict>");
			}
			result.append("</list>");
		} else {
			result.append("empty");
		}

		return result.toString();
	}

	private Object processGetCategories(HttpServletRequest request, String attr) {
		return getParametersAsString("categories", request, attr);
	}

	private Object processGetSessions(HttpServletRequest request, String attr) {
		return getParametersAsString("sessions", request, attr);
	}

	private Object getParametersAsString(String type,
			HttpServletRequest request, String attr) {

		String dictId = (String) request.getSession().getAttribute(attr);
		request.getSession().removeAttribute(attr);

		DictionaryTransferObject dictionary = get(Long.valueOf(dictId));

		StringBuffer result = new StringBuffer();

		List<String> parameters;

		// FIXME Add enum
		if ("categories".equals(type)) {
			parameters = DictionaryFileUtil.getCategories(dictionary);
		} else if ("sessions".equals(type)) {
			parameters = DictionaryFileUtil.getSessions(dictionary);
		} else {
			throw new IllegalArgumentException();
		}

		if (parameters.size() > 0) {

			result.append("<l>");
			for (String value : parameters) {
				result.append("<v>");
				result.append(value);
				result.append("</v>");
			}
			result.append("</l>");
		} else {
			result.append("empty");
		}

		return result.toString();
	}

	private Object processGetDictionary(HttpServletRequest request, String attr) {
		String dictId = (String) request.getSession().getAttribute(attr);
		request.getSession().removeAttribute(attr);

		DictionaryTransferObject dictionary = get(Long.valueOf(dictId));

		StringBuffer result = new StringBuffer();

		List<WordBean> words = DictionaryFileUtil.getWords(dictionary);

		if (words.size() > 0) {

			result.append("<dc>");
			for (WordBean word : words) {
				result.append("<w><d>");
				result.append(word.getDefinition());
				result.append("</d>");
				result.append("<t>");
				result.append(word.getTranslation());
				result.append("</t>");
				result.append("<e>");
				result.append(word.getExample());
				result.append("</e></w>");
			}
			result.append("</dc>");
		} else {
			result.append("empty");
		}

		return result.toString();
	}

}
