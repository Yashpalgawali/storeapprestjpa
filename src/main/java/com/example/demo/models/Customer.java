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
@SequenceGenerator(name="cust_seq",allocationSize = 1,initialValue = 1)
@Table(name="tbl_customer")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor 
public class Customer {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator = "cust_seq")
	private Long customer_id;
	
	private String cust_first_name;
	
	private String cust_last_name;
	
	private String cust_email;
	
	private Long cust_contact;
	
	private String state_name;

	private String city_name;
	
	private Long pincode;
	
	private String cust_gst;
	
	private String cust_country;
	
	private String cust_address;
	
}