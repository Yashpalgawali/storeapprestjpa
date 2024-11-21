package com.example.demo.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tbl_po_products")
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
	
	@OneToOne
	@JoinColumn(name="po_prod_id")
	private PoProductsList product;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="po_id")
	private PurchaseOrder po_id;

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

	public float getCgst_per() {
		return cgst_per;
	}

	public void setCgst_per(float cgst_per) {
		this.cgst_per = cgst_per;
	}

	public float getSgst_per() {
		return sgst_per;
	}

	public void setSgst_per(float sgst_per) {
		this.sgst_per = sgst_per;
	}

	public float getIgst_per() {
		return igst_per;
	}

	public void setIgst_per(float igst_per) {
		this.igst_per = igst_per;
	}

	public float getCgst() {
		return cgst;
	}

	public void setCgst(float cgst) {
		this.cgst = cgst;
	}

	public float getSgst() {
		return sgst;
	}

	public void setSgst(float sgst) {
		this.sgst = sgst;
	}

	public float getIgst() {
		return igst;
	}

	public void setIgst(float igst) {
		this.igst = igst;
	}

	public float getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(float unit_price) {
		this.unit_price = unit_price;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getStoption() {
		return stoption;
	}

	public void setStoption(String stoption) {
		this.stoption = stoption;
	}

	public PoProductsList getProduct() {
		return product;
	}

	public void setProduct(PoProductsList product) {
		this.product = product;
	}

	public PurchaseOrder getPo_id() {
		return po_id;
	}

	public void setPo_id(PurchaseOrder po_id) {
		this.po_id = po_id;
	}
 
	public PurchaseOrderProducts() {
		super();
	}

 
	public PurchaseOrderProducts(Integer purchase_prod_order_id, Integer qty, Integer temp_id, float cgst_per,
			float sgst_per, float igst_per, float cgst, float sgst, float igst, float unit_price, float total,
			String stoption, PoProductsList product, PurchaseOrder po_id) {
 
		super();
		this.purchase_prod_order_id = purchase_prod_order_id;
		this.qty = qty;
		this.temp_id = temp_id;
		this.cgst_per = cgst_per;
		this.sgst_per = sgst_per;
		this.igst_per = igst_per;
		this.cgst = cgst;
		this.sgst = sgst;
		this.igst = igst;
		this.unit_price = unit_price;
		this.total = total;
		this.stoption = stoption;
		this.product = product;
		this.po_id = po_id;
	}

 
	@Override
	public String toString() {
		return "PurchaseOrderProducts [purchase_prod_order_id=" + purchase_prod_order_id + ", qty=" + qty + ", temp_id="
				+ temp_id + ", cgst_per=" + cgst_per + ", sgst_per=" + sgst_per + ", igst_per=" + igst_per + ", cgst="
				+ cgst + ", sgst=" + sgst + ", igst=" + igst + ", unit_price=" + unit_price + ", total=" + total
				+ ", stoption=" + stoption + ", product=" + product + ", po_id=" + po_id + "]";
	}
	
}
