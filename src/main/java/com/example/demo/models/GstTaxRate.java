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
@SequenceGenerator(name="taxrate_seq",allocationSize = 1,initialValue = 1)
@Table(name="tbl_gsttaxrate")
public class GstTaxRate {

	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator = "taxrate_seq")
	private Integer gstid;
	
	private Float taxrate;
	
	private String type;

	public Integer getGstid() {
		return gstid;
	}

	public void setGstid(Integer gstid) {
		this.gstid = gstid;
	}

	public Float getTaxrate() {
		return taxrate;
	}

	public void setTaxrate(Float taxrate) {
		this.taxrate = taxrate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param gstid
	 * @param taxrate
	 * @param type
	 */
	public GstTaxRate(Integer gstid, Float taxrate, String type) {
		super();
		this.gstid = gstid;
		this.taxrate = taxrate;
		this.type = type;
	}

	/**
	 * 
	 */
	public GstTaxRate() {
		super();
	}
	
}