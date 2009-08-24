package com.vocrecaptor.web.client.remote.transferobjects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DictionaryTransferObject implements Serializable {

	private Long id;

	private Long user;

	private /*Long*/String learningLanguage;

	private /*Long*/String throughLanguage;

	private Boolean isPublic;

	private String description;

	private byte[] file;

	public DictionaryTransferObject() {
	}
	
	public DictionaryTransferObject(Long id, Long user, /*Long*/String learningLanguage,
			/*Long*/String throughLanguage, Boolean isPublic, String description,
			byte[] file) {
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

	public /*Long*/String getLearningLanguage() {
		return learningLanguage;
	}

	public void setLearningLanguage(/*Long*/String learningLanguage) {
		this.learningLanguage = learningLanguage;
	}

	public /*Long*/String getThroughLanguage() {
		return throughLanguage;
	}

	public void setThroughLanguage(/*Long*/String throughLanguage) {
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

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

}
