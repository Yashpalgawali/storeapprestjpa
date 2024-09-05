package com.example.demo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_purchase_order")
@SequenceGenerator(name="po_seq",allocationSize = 1 , initialValue = 1)
@ToString
public class PurchaseOrder {

	@Id
	@GeneratedValue(generator="po_seq" , strategy = GenerationType.AUTO)
	private Integer po_id;
	
	private Integer po_no;
	
	private String po_date;
	
	private String prefix;
 
	@OneToOne
	@JoinColumn(name="vendor_id")
	private Vendor vendor;
	
	private String prepared_by;
	
	private float packing_charge;
	
	private float transport_charge;
	
	private float total_amount;
}
