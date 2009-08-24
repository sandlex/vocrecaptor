package com.vocrecaptor.web.client.controls;

public interface Validatable {

	//TODO Do it another way (enum of constants maybe)
	final String PASSWORDS_ARE_NOT_MATCHING = "Passwords are not matching";
	final String LOGIN_IS_NOT_AVAILABLE = "Login is not available";
	final String NO_USER_WITH_SUCH_LOGIN = "No user with such login";
	final String WRONG_PASSWORD = "Wrong password";
	
	void setValid();
	void setInvalid();
}
