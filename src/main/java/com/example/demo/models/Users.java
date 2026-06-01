package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tbl_users")
@SequenceGenerator(name = "user_seq" , allocationSize = 1,initialValue = 1)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor 
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "user_seq")
	private Integer user_id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private int enabled;
	
	private String role;

}
