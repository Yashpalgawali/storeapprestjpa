package com.example.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="tbl_po_products")
@SequenceGenerator(name="po_products_seq",initialValue = 1, allocationSize = 1)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
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
	
	@OneToOne
	@JoinColumn(name="po_prod_id")
	private PoProducts product;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="po_id")
	private PurchaseOrder po_id;

}
