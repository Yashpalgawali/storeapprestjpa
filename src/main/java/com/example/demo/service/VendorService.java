package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Vendor;

public interface VendorService {

	public Vendor saveVendor(Vendor vend);
	
	public List<Vendor> getAllVendors();
	
	public Vendor getVendorById(String vid);
	
	public int updateVendorById(Vendor vend);
	
}
