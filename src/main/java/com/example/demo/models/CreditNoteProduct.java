package com.example.demo.models;

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
import lombok.ToString;

@Entity
@Table(name = "tbl_credit_note_product")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
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

	private Integer order_id;
	
	private Integer credit_note_id;
	
	private float price;
//	@Transient
//	private float custom_price;
//
//	@Transient
//	private String stoption;
//	
	@ManyToOne
	@JoinColumn(name="prod_id")
	private Product product;
	
 
}
