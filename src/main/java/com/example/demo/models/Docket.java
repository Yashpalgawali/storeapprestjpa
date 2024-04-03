package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(name="docket_seq",initialValue = 1,allocationSize = 1)
@Table(name="tbl_docket")
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