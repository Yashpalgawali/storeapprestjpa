package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.GlobalException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ResourceNotModifiedException;
import com.example.demo.models.GstTaxRate;
import com.example.demo.repository.GstTaxRateRepository;

import lombok.RequiredArgsConstructor;

@Service("gsttaxserv")
@RequiredArgsConstructor
public class GstTaxRateServiceImpl implements GstTaxService {

	private final GstTaxRateRepository gsttaxrepo;

	@Override
	public void saveGstTaxRate(GstTaxRate gsttax) {

		GstTaxRate savedObj = gsttaxrepo.save(gsttax);
		if (savedObj == null)
			throw new GlobalException("GST tax rate" + gsttax.getTaxrate() + " is not saved");
	}

	@Override
	public List<GstTaxRate> getAllGstTaxRates() {

		List<GstTaxRate> gstTaxList = gsttaxrepo.findAll();
		if (gstTaxList.size() > 0)
			return gstTaxList;
		throw new ResourceNotFoundException("Gst", "tax", "gst tax rate");
	}

	@Override
	public void updateGstTaxRate(GstTaxRate gsttax) {
		// TODO Auto-generated method stub
		int res = gsttaxrepo.updateGstTaxRate(gsttax.getGstid(), gsttax.getType(), gsttax.getTaxrate());
		if (res < 0)
			throw new ResourceNotModifiedException("Gst Tax Rate " + gsttax.getTaxrate() + " is not modified");
	}

	@Override
	public GstTaxRate getGstTaxrateById(int id) {

		return gsttaxrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Gst Tax", "tax rate id ", "" + id));

	}

}
