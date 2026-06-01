package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tbl_example_po_temp_products")
@SequenceGenerator(name="example_po_temp_products_seq",initialValue = 1, allocationSize = 1)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor 
public class ExamplePOTempProducts {

	@Id
	@GeneratedValue(generator = "example_po_temp_products_seq",strategy = GenerationType.AUTO)
	private Integer purchase_prod_order_id;
	
	private Integer qty;
	
	private Integer temp_id;
	@OneToOne
	@JoinColumn(name="prod_id")
	private PoProductsList product;
	
}
