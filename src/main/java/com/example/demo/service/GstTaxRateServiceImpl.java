package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.GlobalException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ResourceNotModifiedException;
import com.example.demo.globalconfig.Global;
import com.example.demo.models.Activities;
import com.example.demo.models.GstTaxRate;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.GstTaxRateRepository;

import lombok.RequiredArgsConstructor;

@Service("gsttaxserv")
@RequiredArgsConstructor
public class GstTaxRateServiceImpl implements GstTaxService {

	private final GstTaxRateRepository gsttaxrepo;
	private final ActivityRepository actrepo;

	@Override
	public void saveGstTaxRate(GstTaxRate gsttax) {

		GstTaxRate savedObj = gsttaxrepo.save(gsttax);
		if (savedObj != null) {
			Activities act = new Activities();
			act.setActivity("GST tax rate "+gsttax.getTaxrate() + " is saved successfully");
			act.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			act.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			
			actrepo.save(act);
		}
		else {
			Activities act = new Activities();
			act.setActivity("GST tax rate "+gsttax.getTaxrate() + " is not saved");
			act.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			act.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			
			actrepo.save(act);
			throw new GlobalException("GST tax rate" + gsttax.getTaxrate() + " is not saved");
		}
	
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
		if (res > 0)
		{
			Activities act = new Activities();
			act.setActivity("GST tax rate "+gsttax.getTaxrate() + " is updated successfully");
			act.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			act.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			
			actrepo.save(act);
		}
		else {
			Activities act = new Activities();
			act.setActivity("GST tax rate "+gsttax.getTaxrate() + " is not updated successfully");
			act.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			act.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			
			actrepo.save(act);
			throw new ResourceNotModifiedException("Gst Tax Rate " + gsttax.getTaxrate() + " is not modified");
		}			
	}

	@Override
	public GstTaxRate getGstTaxrateById(int id) {

		return gsttaxrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Gst Tax", "tax rate id ", "" + id));

	}

}
