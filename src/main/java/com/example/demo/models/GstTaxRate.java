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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name="taxrate_seq",allocationSize = 1,initialValue = 1)
@Table(name="tbl_gsttaxrate")
public class GstTaxRate {

	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator = "taxrate_seq")
	private Integer gstid;
	
	private Float taxrate;
	
	private String type;
}