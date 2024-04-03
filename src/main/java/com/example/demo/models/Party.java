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
@SequenceGenerator(name="party_seq",initialValue = 1,allocationSize = 1)
@Table(name="tbl_party")
public class Party {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "party_seq")
	private Integer party_id;
	
	private String party_name;

}