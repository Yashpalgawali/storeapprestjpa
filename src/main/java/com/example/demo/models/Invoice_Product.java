package com.example.demo.models;

import javax.persistence.CascadeType;
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
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
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
	
	private float cgst_per;
	
	private float sgst_per;
	
	private float igst_per;
	
	@ManyToOne(cascade = CascadeType.ALL )
	@JoinColumn(name="invoice_id")
	private Invoice invoice;

	private Integer order_id;
	
	@Transient
	private float custom_price;
	
	@Transient
	private String stoption;
	
	@OneToOne
	@JoinColumn(name="prod_id")
	private Product product;

	public int getInv_prod_id() {
		return inv_prod_id;
	}

	public void setInv_prod_id(int inv_prod_id) {
		this.inv_prod_id = inv_prod_id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
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

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public float getCustom_price() {
		return custom_price;
	}

	public void setCustom_price(float custom_price) {
		this.custom_price = custom_price;
	}

	public String getStoption() {
		return stoption;
	}

	public void setStoption(String stoption) {
		this.stoption = stoption;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @param inv_prod_id
	 * @param qty
	 * @param price
	 * @param subtotal
	 * @param total
	 * @param cgst
	 * @param sgst
	 * @param igst
	 * @param cgst_per
	 * @param sgst_per
	 * @param igst_per
	 * @param invoice
	 * @param order_id
	 * @param custom_price
	 * @param stoption
	 * @param product
	 */
	public Invoice_Product(int inv_prod_id, int qty, float price, float subtotal, float total, float cgst, float sgst,
			float igst, float cgst_per, float sgst_per, float igst_per, Invoice invoice, Integer order_id,
			float custom_price, String stoption, Product product) {
		super();
		this.inv_prod_id = inv_prod_id;
		this.qty = qty;
		this.price = price;
		this.subtotal = subtotal;
		this.total = total;
		this.cgst = cgst;
		this.sgst = sgst;
		this.igst = igst;
		this.cgst_per = cgst_per;
		this.sgst_per = sgst_per;
		this.igst_per = igst_per;
		this.invoice = invoice;
		this.order_id = order_id;
		this.custom_price = custom_price;
		this.stoption = stoption;
		this.product = product;
	}

	/**
	 * 
	 */
	public Invoice_Product() {
		super();
	}

	@Override
	public String toString() {
		return "Invoice_Product [inv_prod_id=" + inv_prod_id + ", qty=" + qty + ", price=" + price + ", subtotal="
				+ subtotal + ", total=" + total + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst + ", cgst_per="
				+ cgst_per + ", sgst_per=" + sgst_per + ", igst_per=" + igst_per + ", invoice=" + invoice
				+ ", order_id=" + order_id + ", custom_price=" + custom_price + ", stoption=" + stoption + ", product="
				+ product + "]";
	}
}
