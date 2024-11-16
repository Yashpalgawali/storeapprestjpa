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
import lombok.ToString;


@Entity
@SequenceGenerator(name="vendor_seq",allocationSize = 1 , initialValue = 1)
@Table(name="tbl_vendor")
public class Vendor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "vendor_seq" )
	private Integer vendor_id;
	
	private String vendor_name;
	
	private String vendor_email;
	
	private Long vendor_contact;
	
	private String state_name;

	private String city_name;
	
	private Long pincode;
	
	private String vendor_gst;
	
	private String vendor_address;

	public Integer getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(Integer vendor_id) {
		this.vendor_id = vendor_id;
	}

	public String getVendor_name() {
		return vendor_name;
	}

	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}

	public String getVendor_email() {
		return vendor_email;
	}

	public void setVendor_email(String vendor_email) {
		this.vendor_email = vendor_email;
	}

	public Long getVendor_contact() {
		return vendor_contact;
	}

	public void setVendor_contact(Long vendor_contact) {
		this.vendor_contact = vendor_contact;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public String getVendor_gst() {
		return vendor_gst;
	}

	public void setVendor_gst(String vendor_gst) {
		this.vendor_gst = vendor_gst;
	}

	public String getVendor_address() {
		return vendor_address;
	}

	public void setVendor_address(String vendor_address) {
		this.vendor_address = vendor_address;
	}

	/**
	 * @param vendor_id
	 * @param vendor_name
	 * @param vendor_email
	 * @param vendor_contact
	 * @param state_name
	 * @param city_name
	 * @param pincode
	 * @param vendor_gst
	 * @param vendor_address
	 */
	public Vendor(Integer vendor_id, String vendor_name, String vendor_email, Long vendor_contact, String state_name,
			String city_name, Long pincode, String vendor_gst, String vendor_address) {
		this.vendor_id = vendor_id;
		this.vendor_name = vendor_name;
		this.vendor_email = vendor_email;
		this.vendor_contact = vendor_contact;
		this.state_name = state_name;
		this.city_name = city_name;
		this.pincode = pincode;
		this.vendor_gst = vendor_gst;
		this.vendor_address = vendor_address;
	}

	@Override
	public String toString() {
		return "Vendor [vendor_id=" + vendor_id + ", vendor_name=" + vendor_name + ", vendor_email=" + vendor_email
				+ ", vendor_contact=" + vendor_contact + ", state_name=" + state_name + ", city_name=" + city_name
				+ ", pincode=" + pincode + ", vendor_gst=" + vendor_gst + ", vendor_address=" + vendor_address + "]";
	}

	/**
	 * 
	 */
	public Vendor() {
		super();
	}

	
}