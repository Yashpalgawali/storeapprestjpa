package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_users")
@SequenceGenerator(name = "user_seq" , allocationSize = 1,initialValue = 1)
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "user_seq")
	private Integer user_id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private int enabled;
	
	private String role;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @param user_id
	 * @param username
	 * @param password
	 * @param email
	 * @param enabled
	 * @param role
	 */
	public Users(Integer user_id, String username, String password, String email, int enabled, String role) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.role = role;
	}

	/**
	 * 
	 */
	public Users() {
		super();
	}

	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", enabled=" + enabled + ", role=" + role + "]";
	}

}
