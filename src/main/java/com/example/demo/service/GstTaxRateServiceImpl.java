package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.GstTaxRate;
import com.example.demo.repository.GstTaxRateRepository;

@Service("gsttaxserv")
public class GstTaxRateServiceImpl implements GstTaxService {

	private GstTaxRateRepository gsttaxrepo;
	
	@Autowired
	public GstTaxRateServiceImpl(GstTaxRateRepository gsttaxrepo) {
		this.gsttaxrepo = gsttaxrepo;
	}

	@Override
	public GstTaxRate saveGstTaxRate(GstTaxRate gsttax) {

		return gsttaxrepo.save(gsttax);
	}

	@Override
	public List<GstTaxRate> getAllGstTaxRates() {

		return gsttaxrepo.findAll();
	}

	@Override
	public int updateGstTaxRate(GstTaxRate gsttax) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GstTaxRate getGstTaxrateById(int id) {

		Optional<GstTaxRate> gsttax = gsttaxrepo.findById(id);
		if(gsttax.isEmpty()) {
			return gsttax.get();
		}
		else {
			return null;
		}
	}

}
