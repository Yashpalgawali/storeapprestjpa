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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SequenceGenerator(name="vendor_seq",allocationSize = 1 , initialValue = 1)
@Table(name="tbl_vendor")
public class Vendor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "vendor_seq" )
	private Integer vendor_id;
	
	private String vendor_name;
	
	private String vendor_email;
	
	private Long vendor_contact;
	
	private String state_name;

	private String city_name;
	
	private Long pincode;
	
	private String vendor_gst;
	
	private String vendor_address;
	
}