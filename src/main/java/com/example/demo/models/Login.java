package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {

	private Integer login_id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String role;
	
	private int enabled;
}
