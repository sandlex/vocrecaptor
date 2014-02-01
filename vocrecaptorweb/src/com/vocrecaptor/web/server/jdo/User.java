package com.vocrecaptor.web.server.jdo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Class represents an user entity.
 *
 * @author Alexey Peskov
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String login;
	
	@Persistent
	private String password;
	
	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public User(Long id, String login, String password) {
		this(login, password);
		this.id = id;
	}

	public Long getId() {
		return id;
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
