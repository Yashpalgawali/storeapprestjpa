package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Vendor;
import com.example.demo.repository.VendorRepo;

@Service("vendorserv")
public class VendorServiceImpl implements VendorService {

	@Autowired
	VendorRepo vendorrepo;
	
	@Override
	public Vendor saveVendor(Vendor vend) {
		return vendorrepo.save(vend);
	}

	@Override
	public List<Vendor> getAllVendors() {
		return vendorrepo.findAll();
	}

	@Override
	public Vendor getVendorById(String vid) {
		
		Integer venid = Integer.parseInt(vid);
		return vendorrepo.findById(venid).get();
	}

	@Override
	public int updateVendorById(Vendor vend) {
		return vendorrepo.updateVendor(vend.getVendor_name(), vend.getVendor_email(), vend.getVendor_address(), vend.getVendor_contact(), vend.getState_name(), vend.getCity_name(), vend.getVendor_gst(), vend.getPincode(), vend.getVendor_id());
	}

}