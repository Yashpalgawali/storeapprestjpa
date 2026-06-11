package com.example.demo.dto;

import com.example.demo.models.Customer;
import com.example.demo.models.Invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditNoteDto {

	private Integer credit_note_id;

	private Integer credit_note_num;

	private Integer order_id;

	private String prefix;

	private String date_added;

	private Customer customer;

	private Invoice invoice;
}
