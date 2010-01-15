package com.vocrecaptor.web.client.remote.transferobjects;

import java.io.Serializable;

public class DictionaryCategorySessionBean implements Serializable {

	private static final long serialVersionUID = 6611855824779114753L;
	
	private String dictId;
	private String category;
	private String session;
	
	public DictionaryCategorySessionBean() {
	}

	public DictionaryCategorySessionBean(String dictId, String category,
			String session) {
		this.dictId = dictId;
		this.category = category;
		this.session = session;
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}
	
}
