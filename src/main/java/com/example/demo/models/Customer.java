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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="cust_seq",allocationSize = 1,initialValue = 1)
@Table(name="tbl_customer")
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