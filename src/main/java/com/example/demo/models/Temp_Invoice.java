package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@SequenceGenerator(name="temp_invoice_seq",allocationSize = 1, initialValue = 1)
@Table(name="tbl_temp_invoice")
public class Temp_Invoice {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO , generator = "temp_invoice_seq")
	private Integer temp_id;
	
	private Integer temp_invoice_id;
	
	private Integer qty;
	
	private float unit_price;
	
	private String unit;
	
	private float total;
	
	private float vat_per;
	
	private String hsn;
	
	private float cgst;
	
	private float sgst;
	
	private float igst;
	
	private float cgst_per;
		
	private float sgst_per;
	
	private float igst_per;
	
	@Transient
	private float custom_price;
	
	@Transient
	private String stoption;
	
	@ManyToOne
	@JoinColumn(name="prod_id")
	private Product product;

	public Integer getTemp_id() {
		return temp_id;
	}

	public void setTemp_id(Integer temp_id) {
		this.temp_id = temp_id;
	}

	public Integer getTemp_invoice_id() {
		return temp_invoice_id;
	}

	public void setTemp_invoice_id(Integer temp_invoice_id) {
		this.temp_invoice_id = temp_invoice_id;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public float getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(float unit_price) {
		this.unit_price = unit_price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getVat_per() {
		return vat_per;
	}

	public void setVat_per(float vat_per) {
		this.vat_per = vat_per;
	}

	public String getHsn() {
		return hsn;
	}

	public void setHsn(String hsn) {
		this.hsn = hsn;
	}

	public float getCgst() {
		return cgst;
	}

	public void setCgst(float cgst) {
		this.cgst = cgst;
	}

	public float getSgst() {
		return sgst;
	}

	public void setSgst(float sgst) {
		this.sgst = sgst;
	}

	public float getIgst() {
		return igst;
	}

	public void setIgst(float igst) {
		this.igst = igst;
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

	public float getCustom_price() {
		return custom_price;
	}

	public void setCustom_price(float custom_price) {
		this.custom_price = custom_price;
	}

	public String getStoption() {
		return stoption;
	}

	public void setStoption(String stoption) {
		this.stoption = stoption;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @param temp_id
	 * @param temp_invoice_id
	 * @param qty
	 * @param unit_price
	 * @param unit
	 * @param total
	 * @param vat_per
	 * @param hsn
	 * @param cgst
	 * @param sgst
	 * @param igst
	 * @param cgst_per
	 * @param sgst_per
	 * @param igst_per
	 * @param custom_price
	 * @param stoption
	 * @param product
	 */
	public Temp_Invoice(Integer temp_id, Integer temp_invoice_id, Integer qty, float unit_price, String unit,
			float total, float vat_per, String hsn, float cgst, float sgst, float igst, float cgst_per, float sgst_per,
			float igst_per, float custom_price, String stoption, Product product) {
		super();
		this.temp_id = temp_id;
		this.temp_invoice_id = temp_invoice_id;
		this.qty = qty;
		this.unit_price = unit_price;
		this.unit = unit;
		this.total = total;
		this.vat_per = vat_per;
		this.hsn = hsn;
		this.cgst = cgst;
		this.sgst = sgst;
		this.igst = igst;
		this.cgst_per = cgst_per;
		this.sgst_per = sgst_per;
		this.igst_per = igst_per;
		this.custom_price = custom_price;
		this.stoption = stoption;
		this.product = product;
	}

	/**
	 * 
	 */
	public Temp_Invoice() {
		super();
	}

	@Override
	public String toString() {
		return "Temp_Invoice [temp_id=" + temp_id + ", temp_invoice_id=" + temp_invoice_id + ", qty=" + qty
				+ ", unit_price=" + unit_price + ", unit=" + unit + ", total=" + total + ", vat_per=" + vat_per
				+ ", hsn=" + hsn + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst + ", cgst_per=" + cgst_per
				+ ", sgst_per=" + sgst_per + ", igst_per=" + igst_per + ", custom_price=" + custom_price + ", stoption="
				+ stoption + ", product=" + product + "]";
	}

}
