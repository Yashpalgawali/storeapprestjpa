package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_activity")
@SequenceGenerator(name = "activity_seq", allocationSize = 1, initialValue = 1)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor @Builder
public class Activities {

	public Activities(String string, String format, String format2) {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(generator = "activity_seq", strategy = GenerationType.AUTO)
	private Integer activity_id;

	private String activity;

	private String activity_date;

	private String activity_time;

}
