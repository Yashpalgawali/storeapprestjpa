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
@Table(name = "tbl_credit_note_product")
public class CreditNoteProduct {

	@Id
	@SequenceGenerator(name = "credit_note_prod_seq",initialValue = 1,allocationSize = 1)
	@GeneratedValue(generator = "credit_note_prod_seq", strategy = GenerationType.AUTO )
	private Integer credit_note_prod_id;

	private String credit_note_date;

	private float cgst;

	private float sgst;

	private float igst;

	private float cgst_per;

	private float sgst_per;

	private float igst_per;

	private float subtotal;

	private float total;

	private Integer qty;

	@OneToOne
	@JoinColumn(name="prod_id")
	private Product product;
	
	@OneToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	public Integer getCredit_note_prod_id() {
		return credit_note_prod_id;
	}

	public void setCredit_note_prod_id(Integer credit_note_prod_id) {
		this.credit_note_prod_id = credit_note_prod_id;
	}

	public String getCredit_note_date() {
		return credit_note_date;
	}

	public void setCredit_note_date(String credit_note_date) {
		this.credit_note_date = credit_note_date;
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

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CreditNoteProduct(Integer credit_note_prod_id, String credit_note_date, float cgst, float sgst, float igst,
			float cgst_per, float sgst_per, float igst_per, float subtotal, float total, Integer qty, Product product,
			Customer customer) {
		super();
		this.credit_note_prod_id = credit_note_prod_id;
		this.credit_note_date = credit_note_date;
		this.cgst = cgst;
		this.sgst = sgst;
		this.igst = igst;
		this.cgst_per = cgst_per;
		this.sgst_per = sgst_per;
		this.igst_per = igst_per;
		this.subtotal = subtotal;
		this.total = total;
		this.qty = qty;
		this.product = product;
		this.customer = customer;
	}

	public CreditNoteProduct() {
		super();
	}

	@Override
	public String toString() {
		return "CreditNoteProduct [credit_note_prod_id=" + credit_note_prod_id + ", credit_note_date="
				+ credit_note_date + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst + ", cgst_per=" + cgst_per
				+ ", sgst_per=" + sgst_per + ", igst_per=" + igst_per + ", subtotal=" + subtotal + ", total=" + total
				+ ", qty=" + qty + ", product=" + product + ", customer=" + customer + "]";
	}

}
