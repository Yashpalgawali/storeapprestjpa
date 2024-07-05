package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_po_products_list")
@SequenceGenerator(name="po_prod_list_seq" , initialValue = 1, allocationSize = 1)
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
