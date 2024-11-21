package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_example_po_temp_products")
@SequenceGenerator(name="example_po_temp_products_seq",initialValue = 1, allocationSize = 1)
public class ExamplePOTempProducts {

	@Id
	@GeneratedValue(generator = "example_po_temp_products_seq",strategy = GenerationType.AUTO)
	private Integer purchase_prod_order_id;
	
	private Integer qty;
	
	private Integer temp_id;
	@OneToOne
	@JoinColumn(name="prod_id")
	private PoProductsList product;
	 
	public Integer getPurchase_prod_order_id() {
		return purchase_prod_order_id;
	}

	public void setPurchase_prod_order_id(Integer purchase_prod_order_id) {
		this.purchase_prod_order_id = purchase_prod_order_id;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getTemp_id() {
		return temp_id;
	}
 
	public void setTemp_id(Integer temp_id) {
		this.temp_id = temp_id;
	}
 
	public PoProductsList getProduct() {
		return product;
	}
 
	public void setProduct(PoProductsList product) {
		this.product = product;
	}
 
	public ExamplePOTempProducts(Integer purchase_prod_order_id, Integer qty, Integer temp_id, PoProductsList product) {
		this.purchase_prod_order_id = purchase_prod_order_id;
		this.qty = qty;
		this.temp_id = temp_id;
		this.product = product;
	}

	public ExamplePOTempProducts() {
		super();
	}

	@Override
	public String toString() {
		return "ExamplePOTempProducts [purchase_prod_order_id=" + purchase_prod_order_id + ", qty=" + qty + ", temp_id="
				+ temp_id + ", product=" + product + "]";
	} 
	
}
