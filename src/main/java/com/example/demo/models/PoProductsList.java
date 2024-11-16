package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 */
@Entity
@Table(name="tbl_po_products_list")
@SequenceGenerator(name="po_prod_list_seq" , initialValue = 1, allocationSize = 1)
public class PoProductsList {

	@Id
	@GeneratedValue(generator = "po_prod_list_seq")
	private Integer prod_id;
	
	private String prod_name;
	
	private String prod_model;
	
	private String prod_unit;
	
	private String prod_hsn;
	
	private Float prod_price;
	
	@Transient
	private int gst_rate;
	
	private int cgst_per;
	
	private int sgst_per;
	
	private int igst_per;

	public Integer getProd_id() {
		return prod_id;
	}

	public void setProd_id(Integer prod_id) {
		this.prod_id = prod_id;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public String getProd_model() {
		return prod_model;
	}

	public void setProd_model(String prod_model) {
		this.prod_model = prod_model;
	}

	public String getProd_unit() {
		return prod_unit;
	}

	public void setProd_unit(String prod_unit) {
		this.prod_unit = prod_unit;
	}

	public String getProd_hsn() {
		return prod_hsn;
	}

	public void setProd_hsn(String prod_hsn) {
		this.prod_hsn = prod_hsn;
	}

	public Float getProd_price() {
		return prod_price;
	}

	public void setProd_price(Float prod_price) {
		this.prod_price = prod_price;
	}

	public int getGst_rate() {
		return gst_rate;
	}

	public void setGst_rate(int gst_rate) {
		this.gst_rate = gst_rate;
	}

	public int getCgst_per() {
		return cgst_per;
	}

	public void setCgst_per(int cgst_per) {
		this.cgst_per = cgst_per;
	}

	public int getSgst_per() {
		return sgst_per;
	}

	public void setSgst_per(int sgst_per) {
		this.sgst_per = sgst_per;
	}

	public int getIgst_per() {
		return igst_per;
	}

	public void setIgst_per(int igst_per) {
		this.igst_per = igst_per;
	}

	/**
	 * @param prod_id
	 * @param prod_name
	 * @param prod_model
	 * @param prod_unit
	 * @param prod_hsn
	 * @param prod_price
	 * @param gst_rate
	 * @param cgst_per
	 * @param sgst_per
	 * @param igst_per
	 */
	public PoProductsList(Integer prod_id, String prod_name, String prod_model, String prod_unit, String prod_hsn,
			Float prod_price, int gst_rate, int cgst_per, int sgst_per, int igst_per) {
		super();
		this.prod_id = prod_id;
		this.prod_name = prod_name;
		this.prod_model = prod_model;
		this.prod_unit = prod_unit;
		this.prod_hsn = prod_hsn;
		this.prod_price = prod_price;
		this.gst_rate = gst_rate;
		this.cgst_per = cgst_per;
		this.sgst_per = sgst_per;
		this.igst_per = igst_per;
	}

	/**
	 * 
	 */
	public PoProductsList() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PoProductsList [prod_id=" + prod_id + ", prod_name=" + prod_name + ", prod_model=" + prod_model
				+ ", prod_unit=" + prod_unit + ", prod_hsn=" + prod_hsn + ", prod_price=" + prod_price + ", gst_rate="
				+ gst_rate + ", cgst_per=" + cgst_per + ", sgst_per=" + sgst_per + ", igst_per=" + igst_per + "]";
	}
	
	
}
