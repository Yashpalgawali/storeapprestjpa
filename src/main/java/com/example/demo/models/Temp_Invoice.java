package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="temp_invoice_seq",allocationSize = 1, initialValue = 1)
@ToString
@Table(name="tbl_temp_invoice")
public class Temp_Invoice {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO , generator = "temp_invoice_seq")
			private Integer temp_id;
			
			private Integer temp_invoice_id;
			
			private Integer qty;
			
			private float unit_price;
			
			private String unit;
			
			private float total;
			
			private float vat_per;
			
			private String hsn;
			
			private float cgst;
			
			private float sgst;
			
			private float igst;
			
			private float cgst_per;
				
			private float sgst_per;
			
			private float igst_per;
			
			@Transient
			private float custom_price;
			
			@Transient
			private String stoption;
			
			@ManyToOne
			@JoinColumn(name="prod_id")
			private Product product;
	
}
