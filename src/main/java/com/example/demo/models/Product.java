package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="prod_seq", initialValue = 1,allocationSize = 1)
@ToString
@JsonIgnoreProperties
@Table(name="tbl_product")

public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "prod_seq")
	private Long pid;

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
