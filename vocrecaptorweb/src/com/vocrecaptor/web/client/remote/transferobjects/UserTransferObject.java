package com.vocrecaptor.web.client.remote.transferobjects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserTransferObject implements Serializable {

	private Long id;

	private String login;
	
	private String password;
	
	public UserTransferObject() {
	}
	
	public UserTransferObject(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public UserTransferObject(Long id, String login, String password) {
		this(login, password);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
