package com.example.demo.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="invoice_seq", allocationSize = 1, initialValue = 1)
@Table(name="tbl_invoice")
public class Invoice {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO ,generator = "invoice_seq")
		private Long invoice_id;
		
		private Long order_id;
		
		private Long invoice_no;
		
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

}