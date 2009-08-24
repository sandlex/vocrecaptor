package com.vocrecaptor.web.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.vocrecaptor.web.client.remote.LanguageService;
import com.vocrecaptor.web.client.remote.transferobjects.LanguageTransferObject;
import com.vocrecaptor.web.server.jdo.Language;

@SuppressWarnings("serial")
public class LanguageServiceImpl extends RemoteServiceServlet implements
		LanguageService {

	@Override
	public List<LanguageTransferObject> getAll() {

		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		try {
			Query query = pm.newQuery(Language.class);
			List<Language> languages = (List<Language>) query.execute();
			
			List<LanguageTransferObject> result = new ArrayList<LanguageTransferObject>(languages.size());
			for (Language language : languages) {
				result.add(new LanguageTransferObject(/*language.getId(), */language.getShortName(), language.getName()));
			}

			return result;
		} finally {
			if (pm.currentTransaction().isActive()) {
				pm.currentTransaction().rollback();
			}
		}
	}

	@Override
	public Integer createTestData() {

		PersistenceManager pm = PersistenceManagerHelper
				.getPersistenceManager();

		try {
			//pm.currentTransaction().begin();
			List<Language> languages = new ArrayList<Language>();
			languages.add(new Language("en", "English"));
			languages.add(new Language("de", "German"));
			languages.add(new Language("ru", "Russian"));
			pm.makePersistentAll(languages);
			//pm.currentTransaction().commit();

			return 0;
		} finally {
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
			Query query = pm.newQuery(Language.class);
			List<Language> results = (List<Language>) query.execute();

			return results.size();
		} finally {
			if (pm.currentTransaction().isActive()) {
				pm.currentTransaction().rollback();
			}
		}
	}

}
