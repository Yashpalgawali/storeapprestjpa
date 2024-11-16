package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_prefix")
@SequenceGenerator(name = "prefix_seq", allocationSize = 1,initialValue = 1)
public class Prefix {
	
	@Id
	@GeneratedValue(generator = "prefix_seq",strategy = GenerationType.AUTO)
	private Integer setting_id;
	
	private String fin_year;
	
	private String prefix;

	public Integer getSetting_id() {
		return setting_id;
	}

	public void setSetting_id(Integer setting_id) {
		this.setting_id = setting_id;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @param setting_id
	 * @param fin_year
	 * @param prefix
	 */
	public Prefix(Integer setting_id, String fin_year, String prefix) {
		super();
		this.setting_id = setting_id;
		this.fin_year = fin_year;
		this.prefix = prefix;
	}

	/**
	 * 
	 */
	public Prefix() {
		super();
	}

	@Override
	public String toString() {
		return "Prefix [setting_id=" + setting_id + ", fin_year=" + fin_year + ", prefix=" + prefix + "]";
	}

	
}
