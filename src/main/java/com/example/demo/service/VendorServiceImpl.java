package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.globalconfig.Global;
import com.example.demo.models.Activities;
import com.example.demo.models.Vendor;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.VendorRepo;

@Service("vendorserv")
public class VendorServiceImpl implements VendorService {
	
	private final VendorRepo vendorrepo;
	private final ActivityRepository actrepo;
	
	public VendorServiceImpl(VendorRepo vendorrepo, ActivityRepository actrepo) {
		super();
		this.vendorrepo = vendorrepo;
		this.actrepo = actrepo;
	}

	@Override
	public Vendor saveVendor(Vendor vend) {
		Vendor vendor = vendorrepo.save(vend);
		if(vendor!=null) {
			actrepo.save(new Activities("Vendor "+vendor.getVendor_name()+" is saved successfully", Global.DATE_FORMATTER.format(LocalDateTime.now()) , Global.TIME_FORMATTER.format(LocalDateTime.now())));
			return vendor;
		}
		else {
			actrepo.save(new Activities("Vendor "+vend.getVendor_name()+" is saved successfully", Global.DATE_FORMATTER.format(LocalDateTime.now()) , Global.TIME_FORMATTER.format(LocalDateTime.now())));
			return vendor;
		}
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
		int result = vendorrepo.updateVendor(vend.getVendor_name(), vend.getVendor_email(), vend.getVendor_address(), vend.getVendor_contact(), vend.getState_name(), vend.getCity_name(), vend.getVendor_gst(), vend.getPincode(), vend.getVendor_id());
		if(result>0) {
			actrepo.save(new Activities("Vendor "+vend.getVendor_name()+" is updated successfully", Global.DATE_FORMATTER.format(LocalDateTime.now()) , Global.TIME_FORMATTER.format(LocalDateTime.now())));
			return result;
		}
		else {
			actrepo.save(new Activities("Vendor "+vend.getVendor_name()+" is not updated ", Global.DATE_FORMATTER.format(LocalDateTime.now()) , Global.TIME_FORMATTER.format(LocalDateTime.now())));
			return result;
		}
	}

}