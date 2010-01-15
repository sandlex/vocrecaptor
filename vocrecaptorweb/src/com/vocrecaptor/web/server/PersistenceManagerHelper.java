package com.vocrecaptor.web.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;

public class PersistenceManagerHelper {
	
	private static PersistenceManager instance = null;
	
	private PersistenceManagerHelper() {
		
	}
	
	public static PersistenceManager getPersistenceManager() {
		if (instance == null) {
			instance = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
		}
		
		return instance;
	}

}
