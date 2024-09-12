package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "tbl_activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "activity_seq" , allocationSize = 1,initialValue = 1)
public class Activities {

	@Id
	@GeneratedValue(generator = "activity_seq" , strategy = GenerationType.AUTO)
	private Integer activity_id;
	
	private String activity;
	
	private String activity_date;
	
	private String activity_time;

	public Activities(String activity, String activity_date, String activity_time) {
		super();
		this.activity = activity;
		this.activity_date = activity_date;
		this.activity_time = activity_time;
	}
	
	
}
