package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tbl_prefix")
@SequenceGenerator(name = "prefix_seq", allocationSize = 1,initialValue = 1)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor 
public class Prefix {
	
	@Id
	@GeneratedValue(generator = "prefix_seq",strategy = GenerationType.AUTO)
	private Integer setting_id;
	
	private String fin_year;
	
	private String prefix;	
}
