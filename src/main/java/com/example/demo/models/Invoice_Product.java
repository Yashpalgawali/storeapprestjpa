package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="invoice_prod_seq" , initialValue = 1, allocationSize = 1)
@Table(name="tbl_invoice_product")
public class Invoice_Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "invoice_prod_seq")
	private int inv_prod_id;
	 
	private int qty;
	
	private float price;
	
	private float subtotal;
	
	private float total;
	
	private float cgst;
	
	private float sgst;
	
	private float igst;
	
	private int cgst_per;
	
	private int sgst_per;
	
	private int igst_per;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="invoice_id")
	private Invoice invoice;

//	@Transient
//	private Invoice invoice;
	
	private String order_id;
	
	@OneToOne
	@JoinColumn(name="prod_id")
	private Product product;
}
