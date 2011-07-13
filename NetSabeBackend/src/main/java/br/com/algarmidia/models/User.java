package br.com.algarmidia.models;

@javax.persistence.Entity
public class User extends Entity {

	private String name;
	private String password;
	private String email;

	public User() {}

	public User(Long id, String name, String password, String email) {
		super();
		setId(id);
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
}
