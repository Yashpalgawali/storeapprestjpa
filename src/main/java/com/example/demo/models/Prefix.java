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

@Entity
@Table(name="tbl_prefix")
@SequenceGenerator(name = "prefix_seq", allocationSize = 1,initialValue = 1)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Prefix {
	
	@Id
	@GeneratedValue(generator = "prefix_seq",strategy = GenerationType.AUTO)
	private Integer setting_id;
	
	private String fin_year;
	
	private String prefix;
}
