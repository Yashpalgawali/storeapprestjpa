package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */
@Entity
@Table(name="tbl_po_products_list")
@SequenceGenerator(name="po_prod_list_seq" , initialValue = 1, allocationSize = 1)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor 
public class PoProductsList {

	@Id
	@GeneratedValue(generator = "po_prod_list_seq")
	private Integer prod_id;
	
	private String prod_name;
	
	private String prod_model;
	
	private String prod_unit;
	
	private String prod_hsn;
	
	private Float prod_price;
	
	@Transient
	private int gst_rate;
	
	private int cgst_per;
	
	private int sgst_per;
	
	private int igst_per;
	
}
