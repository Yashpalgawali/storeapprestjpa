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
@Table(name="tbl_store_details")
public class StoreDetails {

	@Id
	@SequenceGenerator(name="store_seq", initialValue = 1 ,allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO ,generator = "store_seq")
	private Long store_id;
	
	private String store_name;
	
	private String store_contact;
	
	private String toll_free_no;

	private String store_address;

	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getStore_contact() {
		return store_contact;
	}

	public void setStore_contact(String store_contact) {
		this.store_contact = store_contact;
	}

	public String getToll_free_no() {
		return toll_free_no;
	}

	public void setToll_free_no(String toll_free_no) {
		this.toll_free_no = toll_free_no;
	}

	public String getStore_address() {
		return store_address;
	}

	public void setStore_address(String store_address) {
		this.store_address = store_address;
	}

	/**
	 * @param store_id
	 * @param store_name
	 * @param store_contact
	 * @param toll_free_no
	 * @param store_address
	 */
	public StoreDetails(Long store_id, String store_name, String store_contact, String toll_free_no,
			String store_address) {
		super();
		this.store_id = store_id;
		this.store_name = store_name;
		this.store_contact = store_contact;
		this.toll_free_no = toll_free_no;
		this.store_address = store_address;
	}

	/**
	 * 
	 */
	public StoreDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "StoreDetails [store_id=" + store_id + ", store_name=" + store_name + ", store_contact=" + store_contact
				+ ", toll_free_no=" + toll_free_no + ", store_address=" + store_address + "]";
	}

}
