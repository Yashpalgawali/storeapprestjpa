package com.example.demo.dto;

import com.example.demo.models.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor @NoArgsConstructor @ToString
public class CreditNoteProductDto {
	
	private Integer credit_note_prod_id;

	private String credit_note_date;

	private float price;
	
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
	
	private float custom_price;

	private String stoption;
	
	private Product product;
}
