package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


	@Getter
	@Setter
//	@Entity
//	@Table(name="tbl_temp_po_products")
	@ToString
	@SequenceGenerator(name="po_temp_products_seq",initialValue = 1, allocationSize = 1)
	public class PurchaseOrderTempProducts {

		@Id
		@GeneratedValue(generator = "po_temp_products_seq",strategy = GenerationType.AUTO)
		private Integer purchase_prod_order_id;
		
		private Integer qty;
		
		private Integer temp_id;
		
		private Integer cgst_per;
		
		private Integer sgst_per;
		
		private Integer igst_per;
		
		private Integer cgst;
		 
		private Integer sgst;
		
		private Integer igst;
		
		private float unit_price;
		
		private float total;
		
		@Transient
		private String stoption;
		
		@OneToOne(fetch = FetchType.EAGER)
//		@JsonIgnore
		@JoinColumn(name="prod_id")
		private PoProductsList product;
		
		
	}
