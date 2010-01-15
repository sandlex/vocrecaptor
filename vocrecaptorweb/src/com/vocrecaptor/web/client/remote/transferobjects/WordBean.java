package com.vocrecaptor.web.client.remote.transferobjects;

import java.io.Serializable;

public class WordBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3262984316757341377L;
	
	private String definition;
	private String translation;
	private String example;
	
	public WordBean() {
	}

	public WordBean(String definition, String translation, String example) {
		this.definition = definition;
		this.translation = translation;
		this.example = example;
	}
	
	public String getDefinition() {
		return definition;
	}
	public String getTranslation() {
		return translation;
	}
	public String getExample() {
		return example;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public void setExample(String example) {
		this.example = example;
	}

}

