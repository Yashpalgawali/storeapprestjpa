package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
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
@SequenceGenerator(name="prod_seq", initialValue = 1,allocationSize = 1)
@JsonIgnoreProperties
@Table(name="tbl_product")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor 
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "prod_seq")
	private Long pid;

	@Column(unique = true)
	private String prod_name ;
	
	private String prod_price;
	 
	private String prod_model_no;
	
	private Long prod_hsn;
	
	private String prod_unit;
	
	private float gsttax;
	
	private float cgst_per;
	
	private float sgst_per;
	
	private float igst_per;

}
