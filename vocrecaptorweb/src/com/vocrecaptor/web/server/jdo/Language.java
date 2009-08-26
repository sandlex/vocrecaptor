package com.vocrecaptor.web.server.jdo;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Class represents a language entity.
 *
 * @author Alexey Peskov
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Language {

	@PrimaryKey
	@Persistent
	private String shortName;
	
	@Persistent
	private String name;
	
	public Language(String shortName, String name) {
		this.shortName = shortName;
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
