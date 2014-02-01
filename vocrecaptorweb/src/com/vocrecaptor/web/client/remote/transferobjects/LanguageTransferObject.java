package com.vocrecaptor.web.client.remote.transferobjects;

import java.io.Serializable;

/**
 * Class represents language entity.
 *
 * @author Alexey Peskov
 */
@SuppressWarnings("serial")
public class LanguageTransferObject implements Serializable {

	private String shortName;
	
	private String name;
	
	public LanguageTransferObject() {
	}
	
	public LanguageTransferObject(String shortName, String name) {
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
