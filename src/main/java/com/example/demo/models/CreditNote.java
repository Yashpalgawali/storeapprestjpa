package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_credit_note")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditNote {

	@Id
	@SequenceGenerator(name = "credit_note_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "credit_note_seq", strategy = GenerationType.AUTO)
	private Integer credit_note_id;

	private String prefix;

	private String date_added;

	@OneToOne
	private Customer customer;

	@OneToOne
	private Invoice invoice;

}
