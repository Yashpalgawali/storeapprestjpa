package com.example.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tbl_po_products")
@SequenceGenerator(name="po_products_seq",initialValue = 1, allocationSize = 1)
public class PurchaseOrderProducts {

	@Id
	@GeneratedValue(generator = "po_products_seq",strategy = GenerationType.AUTO)
	private Integer purchase_prod_order_id;
	
	private Integer qty;
	
	private Integer temp_id;
	
	private Integer cgst_per;
	
	private Integer sgst_per;
	
	private Integer igst_per;
	
	private Integer cgst;
	
	private Integer sgst;
	
	private Integer igst;
	
	private float unit_price;
	
	private float total;
	
	@OneToMany
	@JoinColumn(name="prod_id")
	private List<Product> product;
	
	
}
