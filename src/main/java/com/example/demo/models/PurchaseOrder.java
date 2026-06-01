package com.example.demo.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tbl_purchase_order") 
@SequenceGenerator(name="po_seq",allocationSize = 1 , initialValue = 1)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor 
public class PurchaseOrder {

	@Id
	@GeneratedValue(generator="po_seq" , strategy = GenerationType.AUTO)
	private Integer po_id; 
	
	private String po_date;
	
	private String prefix;
 
	private int order_id; 
	
	@OneToOne
	@JoinColumn(name="vendor_id")
	private Vendor vendor;
	
	private String prepared_by;
	
	private float packing_charge;
	
	private float transport_charge;
	
	private float total_amount;
 	
	@OneToMany(mappedBy = "po_id")
	private List<PurchaseOrderProducts> po_ord_products;	
}
