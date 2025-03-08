package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@SequenceGenerator(name="vendor_seq",allocationSize = 1 , initialValue = 1)
@Table(name="tbl_vendor")

@Schema(name = "Vendor", description = "Schema to hold the Vendor Information")

public class Vendor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "vendor_seq" )
	@Schema(description = "This is id of the Vendor" ,example = "1")
	private Integer vendor_id;
	
	@Schema(description = "This is the Vendor name" ,example = "ABC")
	@NotEmpty(message = "Name cant be blank")
	@Size(min = 5,max = 30,message = "The name must have at least 5 letters or maximum 30 letters")
	private String vendor_name;
	
	@Email(message = "Email address should be valid ")
	@NotEmpty(message = "Email cant be blank")
	@Schema(description = "This is email of the Vendor" ,example = "abc@gmail.com")
	private String vendor_email;
	
	@Pattern(regexp = "(^|[0-9]{10})",message = "Mobile nuber must have 10 digits")
	@Schema(description = "This is Mobile Number of the Vendor" ,example = "1234567897")
	private Long vendor_contact;
	
	
	@Schema(description = "This is State to which Vendor belongs" ,example = "Maharashtra Or Punjab")
	private String state_name;

	@Schema(description = "This is City of the Vendor" ,example = "Chh. SambhajiNagar")
	private String city_name;
	
	@Schema(description = "This is pincod of the city of the Vendor" ,example = "431001")
	private Long pincode;
	
	@Schema(description = "This is GST of the Vendor" ,example = "ZA45616154654")
	private String vendor_gst;
	
	@Schema(description = "This is the Vendor Address" ,example = "CIDCO N-7")
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

	
	public Vendor(
			@NotEmpty(message = "Name cant be blank") @Size(min = 5, max = 30, message = "The name must have at least 5 letters or maximum 30 letters") String vendor_name,
			@Email(message = "Email address should be valid ") @NotEmpty(message = "Email cant be blank") String vendor_email,
			@Pattern(regexp = "(^|[0-9]{10})", message = "Mobile nuber must have 10 digits") Long vendor_contact,
			String state_name, String city_name, Long pincode, String vendor_gst, String vendor_address) {
		super();
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