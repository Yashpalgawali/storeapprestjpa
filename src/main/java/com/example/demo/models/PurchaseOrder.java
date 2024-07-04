package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
//@Entity
//@Table(name="tbl_purchase_order")
//@SequenceGenerator(name="po_seq",allocationSize = 1 , initialValue = 1)
public class PurchaseOrder {

//	@Id
//	@GeneratedValue(generator="po_seq" , strategy = GenerationType.AUTO)
//	private Integer po_id;
//	
//	private Integer po_no;
//	
//	private Integer po_date;
//	
//	@OneToMany
//	@JoinColumn(name="prod_id")
//	private Product product;
//	
//	@OneToOne
//	@JoinColumn(name="vendor_id")
//	private Vendor vendor;
}
