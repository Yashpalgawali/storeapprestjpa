package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "tbl_activity")
@Getter
@Setter
public class Activities {

	@Id
	@GeneratedValue(generator = "" , strategy = GenerationType.AUTO)
	private Integer activity_id;
	
	private String activity;
	
	private String activity_date;
	
	private String activity_time;
	
}
