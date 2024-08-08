package com.example.demo.service;

import java.util.List;

import com.example.demo.models.GstTaxRate;

public interface GstTaxService {

	public GstTaxRate saveGstTaxRate(GstTaxRate gsttax);
	
	public List<GstTaxRate> getAllGstTaxRates();
	
	public int updateGstTaxRate(GstTaxRate gsttax);
	
	public GstTaxRate getGstTaxrateById(int id);
}
