package com.vocrecaptor.web.server.jdo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Class represents a dictionary entity.
 * 
 * @author Alexey Peskov
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Dictionary {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private Long user;
	
	@Persistent
	private /*Long*/String learningLanguage;
	
	@Persistent
	private /*Long*/String throughLanguage;
	
	@Persistent
	private Boolean isPublic;
	
	@Persistent
	private String description;
	
	@Persistent
	private byte[] file;

	public Dictionary(Long id, Long user, /*Long*/String learningLanguage,
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