package com.vocrecaptor.web.server.utils;

public class WordBean {
	
	private String definition;
	private String translation;
	private String example;
	
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

}

