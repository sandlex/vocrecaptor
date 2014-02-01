package com.vocrecaptor.web.client.remote.transferobjects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DictionaryTransferObject implements Serializable {

	private Long id;

	private Long user;

	private String learningLanguage;

	private String throughLanguage;

	private Boolean isPublic;

	private String description;

	private String file;

	public DictionaryTransferObject() {
	}
	
	public DictionaryTransferObject(Long id, Long user, String learningLanguage,
			String throughLanguage, Boolean isPublic, String description,
			String file) {
		this.id = id;
		this.user = user;
		this.learningLanguage = learningLanguage;
		this.throughLanguage = throughLanguage;
		this.isPublic = isPublic;
		this.description = description;
		this.file = file;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public String getLearningLanguage() {
		return learningLanguage;
	}

	public void setLearningLanguage(String learningLanguage) {
		this.learningLanguage = learningLanguage;
	}

	public String getThroughLanguage() {
		return throughLanguage;
	}

	public void setThroughLanguage(String throughLanguage) {
		this.throughLanguage = throughLanguage;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public DictionaryTransferObject reduceMemoryFootprint() {
		this.file = "";
		
		return this;
	}

}
