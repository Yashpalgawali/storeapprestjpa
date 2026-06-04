package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@SequenceGenerator(name="temp_invoice_seq",allocationSize = 1, initialValue = 1)
@Table(name="tbl_temp_invoice")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Temp_Invoice {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO , generator = "temp_invoice_seq")
	private Integer temp_id;
	
	private Integer temp_invoice_id;
	
	private Integer qty;
	
	private float unit_price;
	
	private String unit;
	
	private float total;
	
	private float vat_per;
	
	private String hsn;
	
	private float cgst;
	
	private float sgst;
	
	private float igst;
	
	private float cgst_per;
		
	private float sgst_per;
	
	private float igst_per;
	
	@Transient
	private float custom_price;
	
	@Transient
	private String stoption;
	
	@ManyToOne
	@JoinColumn(name="prod_id")
	private Product product;

}
