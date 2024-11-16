package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@SequenceGenerator(name="party_seq",initialValue = 1,allocationSize = 1)
@Table(name="tbl_party")
public class Party {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "party_seq")
	private Integer party_id;
	
	private String party_name;

	public Integer getParty_id() {
		return party_id;
	}

	public void setParty_id(Integer party_id) {
		this.party_id = party_id;
	}

	public String getParty_name() {
		return party_name;
	}

	public void setParty_name(String party_name) {
		this.party_name = party_name;
	}

	/**
	 * @param party_id
	 * @param party_name
	 */
	public Party(Integer party_id, String party_name) {
		super();
		this.party_id = party_id;
		this.party_name = party_name;
	}

	/**
	 * 
	 */
	public Party() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}