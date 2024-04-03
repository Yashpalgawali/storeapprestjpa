package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_store_details")
public class StoreDetails {

	@Id
	@SequenceGenerator(name="store_seq", initialValue = 1 ,allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO ,generator = "store_seq")
	private Long store_id;
	
	private String store_name;
	
	private String store_contact;
	
	private String toll_free_no;

	private String store_address;
}
