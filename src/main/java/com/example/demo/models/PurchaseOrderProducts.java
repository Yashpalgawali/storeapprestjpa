package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="tbl_po_products")
@ToString
@SequenceGenerator(name="po_products_seq",initialValue = 1, allocationSize = 1)
public class PurchaseOrderProducts {
 
	@Id
	@GeneratedValue(generator = "po_products_seq",strategy = GenerationType.AUTO)
	private Integer purchase_prod_order_id;
	
	private Integer qty;
	
	private Integer temp_id;
	
	private float cgst_per;
	
	private float sgst_per;
	
	private float igst_per;
	
	private float cgst;
	 
	private float sgst;
	
	private float igst;
	
	private float unit_price;
	
	private float total;
	
	@Transient
	private String stoption;
	
	@OneToOne(fetch = FetchType.EAGER)
//	@JsonIgnore
	@JoinColumn(name="prod_id")
	private PoProductsList product;
	
	
}
