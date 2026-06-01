package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SequenceGenerator(name = "taxrate_seq", allocationSize = 1, initialValue = 1)
@Table(name = "tbl_gsttaxrate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GstTaxRate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "taxrate_seq")
	private Integer gstid;

	private Float taxrate;

	private String type;

}