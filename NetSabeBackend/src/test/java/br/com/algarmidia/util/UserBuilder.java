package br.com.algarmidia.util;

import br.com.algarmidia.models.User;

public class UserBuilder {

	private Long id;
	private String name;
	private String password;
	private String email;

	public UserBuilder(){}

	public UserBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public UserBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public UserBuilder withPassword(String password) {
		this.password = password;
		return this;
	}

	public UserBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	public User create() {
		return new User(id, name, password, email);
	}
}
