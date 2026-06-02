package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ResourceNotModifiedException;
import com.example.demo.globalconfig.Global;
import com.example.demo.models.Activities;
import com.example.demo.models.Vendor;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.VendorRepo;

import lombok.RequiredArgsConstructor;

@Service("vendorserv")
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

	private final VendorRepo vendorrepo;
	private final ActivityRepository actrepo;

	@Override
	public Vendor saveVendor(Vendor vend) {

		String trimmedVendorName = vend.getVendor_name();
		Optional<Vendor> foundVendor = vendorrepo.findByVendor_name(trimmedVendorName);

		if (foundVendor.isPresent()) {
			throw new ResourceAlreadyExistsException("name", "Vendor Name", vend.getVendor_name());
		}
		Vendor vendor = vendorrepo.save(vend);

		vendorrepo.findById(vend.getVendor_id());
		if (vendor != null) {
			actrepo.save(new Activities("Vendor " + trimmedVendorName + " is saved successfully",
					Global.DATE_FORMATTER.format(LocalDateTime.now()),
					Global.TIME_FORMATTER.format(LocalDateTime.now())));
			return vendor;
		} else {
			actrepo.save(new Activities("Vendor " + trimmedVendorName + " is saved successfully",
					Global.DATE_FORMATTER.format(LocalDateTime.now()),
					Global.TIME_FORMATTER.format(LocalDateTime.now())));
			return vendor;
		}
	}

	@Override
	public List<Vendor> getAllVendors() {
		List<Vendor> vendorList = vendorrepo.findAll();
		if (vendorList.size() > 0)
			return vendorList;
		throw new ResourceNotFoundException("Vendor", "vendor", "vendors");
	}

	@Override
	public Vendor getVendorById(Integer vid) {

		return vendorrepo.findById(vid)
				.orElseThrow(() -> new ResourceNotFoundException("Vendor", "ID", ""+vid));
	}

	@Override
	@Transactional
	public void updateVendorById(Vendor vend) {

		int result = vendorrepo.updateVendor(vend.getVendor_name().trim(), vend.getVendor_email().trim(),
				vend.getVendor_address().trim(), vend.getVendor_contact(), vend.getState_name().trim(),
				vend.getCity_name().trim(), vend.getVendor_gst(), vend.getPincode(), vend.getVendor_id());

		if (result > 0) {
			actrepo.save(new Activities("Vendor " + vend.getVendor_name() + " is updated successfully",
					Global.DATE_FORMATTER.format(LocalDateTime.now()),
					Global.TIME_FORMATTER.format(LocalDateTime.now())));
		} else {
			actrepo.save(new Activities("Vendor " + vend.getVendor_name() + " is not updated ",
					Global.DATE_FORMATTER.format(LocalDateTime.now()),
					Global.TIME_FORMATTER.format(LocalDateTime.now())));
			throw new ResourceNotModifiedException("Vendor " + vend.getVendor_name() + " is not updated");

		}
	}

}