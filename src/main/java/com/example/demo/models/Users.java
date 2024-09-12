package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
	
}
