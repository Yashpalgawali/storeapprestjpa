package com.example.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_purchase_order") 
@SequenceGenerator(name="po_seq",allocationSize = 1 , initialValue = 1)
public class PurchaseOrder {

	@Id
	@GeneratedValue(generator="po_seq" , strategy = GenerationType.AUTO)
	private Integer po_id; 
	
	private String po_date;
	
	private String prefix;
 
	private int order_id; 
	
	@OneToOne
	@JoinColumn(name="vendor_id")
	private Vendor vendor;
	
	private String prepared_by;
	
	private float packing_charge;
	
	private float transport_charge;
	
	private float total_amount;
 	
	@OneToMany(mappedBy = "po_id")
	private List<PurchaseOrderProducts> po_ord_products;

	public Integer getPo_id() {
		return po_id;
	}

	public void setPo_id(Integer po_id) {
		this.po_id = po_id;
	}

	public String getPo_date() {
		return po_date;
	}

	public void setPo_date(String po_date) {
		this.po_date = po_date;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public String getPrepared_by() {
		return prepared_by;
	}

	public void setPrepared_by(String prepared_by) {
		this.prepared_by = prepared_by;
	}

	public float getPacking_charge() {
		return packing_charge;
	}

	public void setPacking_charge(float packing_charge) {
		this.packing_charge = packing_charge;
	}

	public float getTransport_charge() {
		return transport_charge;
	}

	public void setTransport_charge(float transport_charge) {
		this.transport_charge = transport_charge;
	}

	public float getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(float total_amount) {
		this.total_amount = total_amount;
	}

	public List<PurchaseOrderProducts> getPo_ord_products() {
		return po_ord_products;
	}

	public void setPo_ord_products(List<PurchaseOrderProducts> po_ord_products) {
		this.po_ord_products = po_ord_products;
	}

 
	public PurchaseOrder(Integer po_id, String po_date, String prefix, int order_id, Vendor vendor, String prepared_by,
			float packing_charge, float transport_charge, float total_amount,
			List<PurchaseOrderProducts> po_ord_products) {
		super();
		this.po_id = po_id;
		this.po_date = po_date;
		this.prefix = prefix;
		this.order_id = order_id;
		this.vendor = vendor;
		this.prepared_by = prepared_by;
		this.packing_charge = packing_charge;
		this.transport_charge = transport_charge;
		this.total_amount = total_amount;
		this.po_ord_products = po_ord_products;
	}

	public PurchaseOrder() {
		super();
	}

	@Override
	public String toString() {
		return "PurchaseOrder [po_id=" + po_id + ", po_date=" + po_date + ", prefix=" + prefix + ", order_id="
				+ order_id + ", vendor=" + vendor + ", prepared_by=" + prepared_by + ", packing_charge="
				+ packing_charge + ", transport_charge=" + transport_charge + ", total_amount=" + total_amount
				+ ", po_ord_products=" + po_ord_products + "]";
	}

	
}
