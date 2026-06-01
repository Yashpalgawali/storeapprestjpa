package com.example.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SequenceGenerator(name = "invoice_prod_seq", initialValue = 1, allocationSize = 1)
@Table(name = "tbl_invoice_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_id")
	private Invoice invoice;

	private Integer order_id;

	@Transient
	private float custom_price;

	@Transient
	private String stoption;

	@OneToOne
	@JoinColumn(name = "prod_id")
	private Product product;

}
