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
@SequenceGenerator(name = "party_seq", initialValue = 1, allocationSize = 1)
@Table(name = "tbl_party")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "party_seq")
	private Integer party_id;

	private String party_name;

}