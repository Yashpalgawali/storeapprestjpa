package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_temp_po_products")
@SequenceGenerator(name = "po_temp_products_seq", initialValue = 1, allocationSize = 1)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor 
public class PurchaseOrderTempProducts {

	@Id
	@GeneratedValue(generator = "po_temp_products_seq", strategy = GenerationType.AUTO)
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

	@OneToOne()
//		@JsonIgnore
	@JoinColumn(name = "prod_id")
	private PoProducts product;

}
