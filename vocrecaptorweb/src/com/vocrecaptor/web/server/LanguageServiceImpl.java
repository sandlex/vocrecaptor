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
import com.vocrecaptor.web.client.remote.LanguageService;
import com.vocrecaptor.web.client.remote.transferobjects.LanguageTransferObject;
import com.vocrecaptor.web.server.jdo.Language;

@SuppressWarnings("serial")
public class LanguageServiceImpl extends RemoteServiceServlet implements
		LanguageService {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();

		if (request.getSession().getAttribute("userToGetLanguages") != null) {
			response.setContentType("text/xml");
			out.print(processGetLanguages(request, "userToGetLanguages"));
			out.close();
			return;
		}

	}
	
	@Override
	public List<LanguageTransferObject> getAll() {

		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		Query query = pm.newQuery(Language.class);
		List<Language> languages = (List<Language>) query.execute();

		List<LanguageTransferObject> result = new ArrayList<LanguageTransferObject>(
				languages.size());
		for (Language language : languages) {
			result.add(new LanguageTransferObject(language
					.getShortName(), language.getName()));
		}

		return result;
	}

	@Override
	public Integer createTestData() {

		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		List<Language> languages = new ArrayList<Language>();
		languages.add(new Language("gb", "English"));
		languages.add(new Language("ru", "Russian"));
		pm.makePersistentAll(languages);

		return 0;
	}

	@Override
	public Integer count() {

		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		Query query = pm.newQuery(Language.class);
		List<Language> results = (List<Language>) query.execute();

		return results.size();
	}

	@Override
	public Integer deleteAll() {

		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		Query query = pm.newQuery(Language.class);
		List<Language> languages = (List<Language>) query.execute();
		
		pm.deletePersistentAll(languages);

		return 0;
	}
	
	private Object processGetLanguages(HttpServletRequest request,
			String attr) {
		//String userId = (String) request.getSession().getAttribute(attr);
		request.getSession().removeAttribute(attr);

		List<LanguageTransferObject> languages = getAll();
		StringBuffer result = new StringBuffer();

		if (languages.size() > 0) {
			result.append("<list>");
			for (LanguageTransferObject language : languages) {
				result.append("<dict><shortName>");
				result.append(language.getShortName());
				result.append("</shortName><name>");
				result.append(language.getName());
				result.append("</name></dict>");
			}
			result.append("</list>");
		} else {
			result.append("empty");
		}

		return result.toString();
	}
	

}
