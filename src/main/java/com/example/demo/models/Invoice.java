package com.example.demo.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@SequenceGenerator(name="invoice_seq", allocationSize = 1, initialValue = 1)
@Table(name="tbl_invoice")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor 
@ToString
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
				
}