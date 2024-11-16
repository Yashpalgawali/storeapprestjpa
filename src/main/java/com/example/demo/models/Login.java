package com.example.demo.models;


public class Login {

	private Integer login_id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String role;
	
	private int enabled;

	public Integer getLogin_id() {
		return login_id;
	}

	public void setLogin_id(Integer login_id) {
		this.login_id = login_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	/**
	 * 
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param login_id
	 * @param username
	 * @param password
	 * @param email
	 * @param role
	 * @param enabled
	 */
	public Login(Integer login_id, String username, String password, String email, String role, int enabled) {
		super();
		this.login_id = login_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.enabled = enabled;
	}

}
