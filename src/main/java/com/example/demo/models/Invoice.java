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

import org.springframework.beans.factory.annotation.Value;

@Entity
@SequenceGenerator(name="invoice_seq", allocationSize = 1, initialValue = 1)
@Table(name="tbl_invoice")
public class Invoice {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO ,generator = "invoice_seq")
		private Integer invoice_id;
		
		private Integer order_id;
		
		private Integer invoice_no;
		
		private String prefix;
		
		@Value("tidystore")
		private String store_name;
		
		@Value("tidystore.com")
		private String store_url;
		
		@ManyToOne
		@JoinColumn(name="customer_id")
		private Customer customer;
		
		private Float total_amount;
		
//		private Date date_added;
//		private Date updated_date;
		
		private String date_added;
		
		private String updated_date;
		
		private String vehicle;
		
		private String batch_no;

		private String orderponumber;
		
		@OneToMany(mappedBy = "invoice")
		private List<Invoice_Product> invoiceproduct;

		/**
		 * @param invoice_id
		 * @param order_id
		 * @param invoice_no
		 * @param prefix
		 * @param store_name
		 * @param store_url
		 * @param customer
		 * @param total_amount
		 * @param date_added
		 * @param updated_date
		 * @param vehicle
		 * @param batch_no
		 * @param orderponumber
		 * @param invoiceproduct
		 */
		public Invoice(Integer invoice_id, Integer order_id, Integer invoice_no, String prefix, String store_name,
				String store_url, Customer customer, Float total_amount, String date_added, String updated_date,
				String vehicle, String batch_no, String orderponumber, List<Invoice_Product> invoiceproduct) {
			super();
			this.invoice_id = invoice_id;
			this.order_id = order_id;
			this.invoice_no = invoice_no;
			this.prefix = prefix;
			this.store_name = store_name;
			this.store_url = store_url;
			this.customer = customer;
			this.total_amount = total_amount;
			this.date_added = date_added;
			this.updated_date = updated_date;
			this.vehicle = vehicle;
			this.batch_no = batch_no;
			this.orderponumber = orderponumber;
			this.invoiceproduct = invoiceproduct;
		}

		public Integer getInvoice_id() {
			return invoice_id;
		}

		public void setInvoice_id(Integer invoice_id) {
			this.invoice_id = invoice_id;
		}

		public Integer getOrder_id() {
			return order_id;
		}

		public void setOrder_id(Integer order_id) {
			this.order_id = order_id;
		}

		public Integer getInvoice_no() {
			return invoice_no;
		}

		public void setInvoice_no(Integer invoice_no) {
			this.invoice_no = invoice_no;
		}

		public String getPrefix() {
			return prefix;
		}

		public void setPrefix(String prefix) {
			this.prefix = prefix;
		}

		public String getStore_name() {
			return store_name;
		}

		public void setStore_name(String store_name) {
			this.store_name = store_name;
		}

		public String getStore_url() {
			return store_url;
		}

		public void setStore_url(String store_url) {
			this.store_url = store_url;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public Float getTotal_amount() {
			return total_amount;
		}

		public void setTotal_amount(Float total_amount) {
			this.total_amount = total_amount;
		}

		public String getDate_added() {
			return date_added;
		}

		public void setDate_added(String date_added) {
			this.date_added = date_added;
		}

		public String getUpdated_date() {
			return updated_date;
		}

		public void setUpdated_date(String updated_date) {
			this.updated_date = updated_date;
		}

		public String getVehicle() {
			return vehicle;
		}

		public void setVehicle(String vehicle) {
			this.vehicle = vehicle;
		}

		public String getBatch_no() {
			return batch_no;
		}

		public void setBatch_no(String batch_no) {
			this.batch_no = batch_no;
		}

		public String getOrderponumber() {
			return orderponumber;
		}

		public void setOrderponumber(String orderponumber) {
			this.orderponumber = orderponumber;
		}

		public List<Invoice_Product> getInvoiceproduct() {
			return invoiceproduct;
		}

		public void setInvoiceproduct(List<Invoice_Product> invoiceproduct) {
			this.invoiceproduct = invoiceproduct;
		}

		/**
		 * 
		 */
		public Invoice() {
			super();
			// TODO Auto-generated constructor stub
		}

		
}