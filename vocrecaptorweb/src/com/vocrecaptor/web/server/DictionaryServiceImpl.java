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

import com.google.appengine.api.datastore.Text;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.vocrecaptor.web.client.remote.DictionaryService;
import com.vocrecaptor.web.client.remote.transferobjects.DictionaryCategorySessionBean;
import com.vocrecaptor.web.client.remote.transferobjects.DictionaryTransferObject;
import com.vocrecaptor.web.client.remote.transferobjects.WordBean;
import com.vocrecaptor.web.client.remote.transferobjects.WordsPage;
import com.vocrecaptor.web.server.jdo.Dictionary;
import com.vocrecaptor.web.server.servlet.GetDictionaries.GetDictionariesBean;
import com.vocrecaptor.web.server.utils.DictionaryFileUtil;

@SuppressWarnings("serial")
public class DictionaryServiceImpl extends RemoteServiceServlet implements
		DictionaryService {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf8");

		// response.setHeader("encoding", "utf8");
		// response.setHeader("charset", "utf8");
		// response.setHeader("Content-Type", "text/html; charset=utf8");

		PrintWriter out = response.getWriter();

		if (request.getSession().getAttribute("create") != null) {
			out.print(processCreateDictionary(request, "create"));
			out.close();
			return;
		}

		if (request.getSession().getAttribute("append") != null) {
			out.print(processAppendDictionary(request, "append"));
			out.close();
			return;
		}

		if (request.getSession().getAttribute("replace") != null) {
			out.print(processReplaceDictionary(request, "replace"));
			out.close();
			return;
		}

		if (request.getSession().getAttribute("merge") != null) {
			out.print(processMergeDictionary(request, "merge"));
			out.close();
			return;
		}

		if (request.getSession().getAttribute("beanToGetDictionaries") != null) {
			response.setContentType("text/xml");
			out.print(processGetDictionaries(request, "beanToGetDictionaries"));
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
			response.setContentType("text/xml;charset=utf8");
			out
					.print(processGetDictionary(request,
							"dictionaryToGetDictionary"));
			out.close();

			return;
		}

		if (request.getSession().getAttribute("dictionaryToDownload") != null) {
			response.setContentType("text/xml");
			processDownload(request, response, "dictionaryToDownload");
			return;
		}

	}

	// protected static String recodeToUTF(String source) {
	// if (source != null) {
	// try {
	// return new String(source.getBytes("ISO8859-1"), "utf8");
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	// }
	//		
	// return null;
	// }

	@Override
	public Long create(DictionaryTransferObject dictionaryTO) {
		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		pm.currentTransaction().begin();
		Dictionary dictionary;

		dictionary = dictionaryDTOToDictionary(dictionaryTO);
		Long result = pm.makePersistent(dictionary).getId();
		pm.currentTransaction().commit();

		return result;
	}

	@Override
	public Long delete(DictionaryTransferObject dictionary) {

		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		Query query = pm.newQuery(Dictionary.class);
		query.setFilter("id == idParam");
		query.declareParameters("Long idParam");

		List<Dictionary> dictionaries = (List<Dictionary>) query
				.execute(dictionary.getId());

		pm.deletePersistentAll(dictionaries);

		return 0L;
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
			result.add(dictionaryToDTO(dictionary).reduceMemoryFootprint());
		}

		return result;
	}

	private List<DictionaryTransferObject> getPublic(String learn,
			String through) {
		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		Query query = pm.newQuery(Dictionary.class);
		query
				.setFilter("learningLanguage == learnParam && throughLanguage == throughParam && isPublic == true");
		query.declareParameters("String learnParam, String throughParam");

		Object[] params = new Object[2];
		params[0] = learn;
		params[1] = through;

		List<Dictionary> dictionaries = (List<Dictionary>) query
				.executeWithArray(params);

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

		return result.get(0);// .reduceMemoryFootprint();//FIXME
	}

	private DictionaryTransferObject dictionaryToDTO(Dictionary dictionary) {
		return new DictionaryTransferObject(dictionary.getId(), dictionary
				.getUser(), dictionary.getLearningLanguage(), dictionary
				.getThroughLanguage(), dictionary.getIsPublic(), dictionary
				.getDescription(), dictionary.getFile().getValue());
	}

	private Dictionary dictionaryDTOToDictionary(
			DictionaryTransferObject dictionary) {
		return new Dictionary(dictionary.getId(), dictionary.getUser(),
				dictionary.getLearningLanguage(), dictionary
						.getThroughLanguage(), dictionary.getIsPublic(),
				dictionary.getDescription(), new Text(dictionary.getFile()));
	}

	private Object processCreateDictionary(HttpServletRequest request,
			String attr) {
		DictionaryTransferObject dictionaryTO = (DictionaryTransferObject) request
				.getSession().getAttribute(attr);

		request.getSession().removeAttribute(attr);

		return create(dictionaryTO);
	}

	private Object processGetDictionaries(HttpServletRequest request,
			String attr) {

		GetDictionariesBean bean = (GetDictionariesBean) request.getSession()
				.getAttribute(attr);
		request.getSession().removeAttribute(attr);

		List<DictionaryTransferObject> dictionaries;

		if ("private".equals(bean.getPublicCriteria())) {
			dictionaries = getByUser(Long.valueOf(bean.getUserId()));
		} else if ("public".equals(bean.getPublicCriteria())) {
			dictionaries = getPublic(bean.getLearningLanguage(), bean
					.getThroughLanguage());
		} else {
			throw new IllegalArgumentException();
		}

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

	private String processGetDictionary(HttpServletRequest request, String attr) {
		DictionaryCategorySessionBean bean = (DictionaryCategorySessionBean) request
				.getSession().getAttribute(attr);
		request.getSession().removeAttribute(attr);

		DictionaryTransferObject dictionary = get(Long.valueOf(bean.getDictId()));

		StringBuffer result = new StringBuffer();

		List<WordBean> words = DictionaryFileUtil.getWords(dictionary, bean.getCategory(), bean.getSession());

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

	// FIXME Almost equals with get()
	public DictionaryTransferObject getWithContent(Long id) {

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

	@Override
	public WordsPage getWordsPage(Long dictId, Integer page) {

		DictionaryTransferObject dictionary = getWithContent(dictId);
		WordsPage wordsPage = DictionaryFileUtil.get15StartingFrom(dictionary,
				page);

		// TODO Auto-generated method stub
		return wordsPage;
	}

	private void processDownload(HttpServletRequest request,
			HttpServletResponse response, String attr) {
		String dictId = (String) request.getSession().getAttribute(attr);
		request.getSession().removeAttribute(attr);

		DictionaryTransferObject dictionary = get(Long.valueOf(dictId));

		response.setContentType("text/plain");
		response.setHeader("Content-Disposition", "Attachment;Filename=\""
				+ dictionary.getDescription() + "." + "txt" + "\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "must-revalidate");

		try {
			response.getWriter().write(dictionary.getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void update(Long id, String file) {
		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		pm.currentTransaction().begin();

		Dictionary dictionary = pm.getObjectById(Dictionary.class, id);
		// TODO Check result file size and throw exception to notify user
		dictionary.setFile(new Text(file));
		pm.currentTransaction().commit();
	}

	// FIXME Join with next two
	private String processReplaceDictionary(HttpServletRequest request,
			String attr) {
		DictionaryTransferObject dictionaryTO = (DictionaryTransferObject) request
				.getSession().getAttribute(attr);

		request.getSession().removeAttribute(attr);

		String addition = dictionaryTO.getFile();

		update(dictionaryTO.getId(), addition);

		return outputer(addition);
	}

	private String processAppendDictionary(HttpServletRequest request,
			String attr) {
		DictionaryTransferObject dictionaryTO = (DictionaryTransferObject) request
				.getSession().getAttribute(attr);

		// TODO add special exception for bad file format, large file
		request.getSession().removeAttribute(attr);

		DictionaryTransferObject current = get(dictionaryTO.getId());

		String addition = current.getFile() + dictionaryTO.getFile();

		update(dictionaryTO.getId(), addition);

		return outputer(addition);
	}

	private String processMergeDictionary(HttpServletRequest request,
			String attr) {
		DictionaryTransferObject dictionaryTO = (DictionaryTransferObject) request
				.getSession().getAttribute(attr);

		request.getSession().removeAttribute(attr);

		DictionaryTransferObject current = get(dictionaryTO.getId());

		String addition = DictionaryFileUtil.getDifferences(current.getFile(),
				dictionaryTO.getFile());
		update(dictionaryTO.getId(), current.getFile() + addition);

		return outputer(addition);
	}

	private String outputer(String addition) {
		int number = ("".equals(addition) ? 0 : addition.split("\n").length);
		return "~Dictionary file updated ("
				+ ((number > 1 || number == 0) ? number + " new items)"
						: number + " new item)") + "~";
	}

	// TODO Refactor with get(lang, lang)
	@Override
	public List<DictionaryTransferObject> getPublic() {
		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		Query query = pm.newQuery(Dictionary.class);
		query.setFilter("isPublic == true");

		List<Dictionary> dictionaries = (List<Dictionary>) query.execute();

		List<DictionaryTransferObject> result = new ArrayList<DictionaryTransferObject>(
				dictionaries.size());
		for (Dictionary dictionary : dictionaries) {
			result.add(dictionaryToDTO(dictionary));
		}

		return result;
	}

}
