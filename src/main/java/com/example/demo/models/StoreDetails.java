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
@Table(name="tbl_store_details")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
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
