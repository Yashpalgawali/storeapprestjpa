package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_credit_note")
public class CreditNote {

	@Id
	@SequenceGenerator(name = "credit_note_seq",allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "credit_note_seq",strategy = GenerationType.AUTO)
	private Integer credit_note_id;
	
	private String prefix;

	private String date_added;

	@OneToOne
	private Customer customer;
	
	@OneToOne
	private Invoice invoice;

	public Integer getCredit_note_id() {
		return credit_note_id;
	}

	public void setCredit_note_id(Integer credit_note_id) {
		this.credit_note_id = credit_note_id;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getDate_added() {
		return date_added;
	}

	public void setDate_added(String date_added) {
		this.date_added = date_added;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public CreditNote(Integer credit_note_id, String prefix, String date_added, Customer customer, Invoice invoice) {
		super();
		this.credit_note_id = credit_note_id;
		this.prefix = prefix;
		this.date_added = date_added;
		this.customer = customer;
		this.invoice = invoice;
	}

	public CreditNote() {
		super();
	}

	@Override
	public String toString() {
		return "CreditNote [credit_note_id=" + credit_note_id + ", prefix=" + prefix + ", date_added=" + date_added
				+ ", customer=" + customer + ", invoice=" + invoice + "]";
	} 
	 
}
