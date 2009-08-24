package com.vocrecaptor.web.client.controls;

public interface Validatable {

	//TODO Do it another way (enum of constants maybe)
	final String PASSWORDS_ARE_NOT_MATCHING = "Passwords are not matching";
	final String LOGIN_IS_NOT_AVAILABLE = "Login is not available";
	final String WRONG_LOGIN_PASSWORD = "Wrong login/password";
	
	void setValid();
	void setInvalid();
}
