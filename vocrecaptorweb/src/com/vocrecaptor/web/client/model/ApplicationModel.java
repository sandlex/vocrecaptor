package com.vocrecaptor.web.client.model;

import com.vocrecaptor.web.client.remote.transferobjects.UserTransferObject;

public class ApplicationModel {

	private UserTransferObject user;
	
	public void setUser(UserTransferObject user) {
		this.user = user;
	}

	public UserTransferObject getUser() {
		return user;
	}

}
