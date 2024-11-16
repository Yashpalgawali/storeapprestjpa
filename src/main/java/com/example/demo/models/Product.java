package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SequenceGenerator(name="prod_seq", initialValue = 1,allocationSize = 1)
@JsonIgnoreProperties
@Table(name="tbl_product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "prod_seq")
	private Long pid;

	private String prod_name ;
	
	private String prod_price;
	 
	private String prod_model_no;
	
	private Long prod_hsn;
	
	private String prod_unit;
	
	private float gsttax;
	
	private float cgst_per;
	
	private float sgst_per;
	
	private float igst_per;

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public String getProd_price() {
		return prod_price;
	}

	public void setProd_price(String prod_price) {
		this.prod_price = prod_price;
	}

	public String getProd_model_no() {
		return prod_model_no;
	}

	public void setProd_model_no(String prod_model_no) {
		this.prod_model_no = prod_model_no;
	}

	public Long getProd_hsn() {
		return prod_hsn;
	}

	public void setProd_hsn(Long prod_hsn) {
		this.prod_hsn = prod_hsn;
	}

	public String getProd_unit() {
		return prod_unit;
	}

	public void setProd_unit(String prod_unit) {
		this.prod_unit = prod_unit;
	}

	public float getGsttax() {
		return gsttax;
	}

	public void setGsttax(float gsttax) {
		this.gsttax = gsttax;
	}

	public float getCgst_per() {
		return cgst_per;
	}

	public void setCgst_per(float cgst_per) {
		this.cgst_per = cgst_per;
	}

	public float getSgst_per() {
		return sgst_per;
	}

	public void setSgst_per(float sgst_per) {
		this.sgst_per = sgst_per;
	}

	public float getIgst_per() {
		return igst_per;
	}

	public void setIgst_per(float igst_per) {
		this.igst_per = igst_per;
	}

	/**
	 * @param pid
	 * @param prod_name
	 * @param prod_price
	 * @param prod_model_no
	 * @param prod_hsn
	 * @param prod_unit
	 * @param gsttax
	 * @param cgst_per
	 * @param sgst_per
	 * @param igst_per
	 */
	public Product(Long pid, String prod_name, String prod_price, String prod_model_no, Long prod_hsn, String prod_unit,
			float gsttax, float cgst_per, float sgst_per, float igst_per) {
		super();
		this.pid = pid;
		this.prod_name = prod_name;
		this.prod_price = prod_price;
		this.prod_model_no = prod_model_no;
		this.prod_hsn = prod_hsn;
		this.prod_unit = prod_unit;
		this.gsttax = gsttax;
		this.cgst_per = cgst_per;
		this.sgst_per = sgst_per;
		this.igst_per = igst_per;
	}

	/**
	 * 
	 */
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", prod_name=" + prod_name + ", prod_price=" + prod_price + ", prod_model_no="
				+ prod_model_no + ", prod_hsn=" + prod_hsn + ", prod_unit=" + prod_unit + ", gsttax=" + gsttax
				+ ", cgst_per=" + cgst_per + ", sgst_per=" + sgst_per + ", igst_per=" + igst_per + "]";
	}

}
