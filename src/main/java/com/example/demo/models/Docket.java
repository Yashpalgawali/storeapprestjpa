package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SequenceGenerator(name="docket_seq",initialValue = 1,allocationSize = 1)
@Table(name="tbl_docket")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor 
public class Docket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "docket_seq")
	private Integer docket_id;
	
	private Long docket_num;
	
	private Long order_id;
	
	private String cust_name;
	
	@ManyToOne
	@JoinColumn(name="party_id")
	private Party party;	
	
}