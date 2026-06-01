package com.example.demo.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="tbl_vendor")
@Schema(name = "Vendor", description = "Schema to hold the Vendor Information")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Vendor {
	
	@Id
	@SequenceGenerator(name="vendor_seq",allocationSize = 1 , initialValue = 1)
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
	
}