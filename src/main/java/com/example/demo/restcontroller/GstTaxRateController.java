package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.GstTaxRate;
import com.example.demo.service.GstTaxService;

@RestController
@RequestMapping("gsttaxrate")
@CrossOrigin("*")
public class GstTaxRateController {

	private GstTaxService gsttaxserv;

	public GstTaxRateController(GstTaxService gsttaxserv) {
		this.gsttaxserv = gsttaxserv;
	}
	
	@PostMapping("/")
	public ResponseEntity<GstTaxRate> saveGstTaxRate(@RequestBody GstTaxRate gsttax) {
		GstTaxRate gst = gsttaxserv.saveGstTaxRate(gsttax);
		if(gst!=null) {
			return new ResponseEntity<GstTaxRate>(gst , HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<GstTaxRate>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<GstTaxRate>> getAllGstTaxRates() {
		List<GstTaxRate> gst = gsttaxserv.getAllGstTaxRates();
		if(gst.size()>0) {
			return new ResponseEntity<List<GstTaxRate>>(gst , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<GstTaxRate>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GstTaxRate> getGstTaxRateById(Integer id) {
	
		GstTaxRate gsttax = gsttaxserv.getGstTaxrateById(id);
		
		if(gsttax!=null) {
			return new ResponseEntity<GstTaxRate>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else {
			return new ResponseEntity<GstTaxRate>(HttpStatus.NOT_FOUND);
		}	
	}
	
	@PutMapping("/")
	public ResponseEntity<GstTaxRate> updateGstTaxRate(@RequestBody GstTaxRate gsttax) {
		int res = gsttaxserv.updateGstTaxRate(gsttax);
		if(res > 0 ) {
			return new ResponseEntity<GstTaxRate>(gsttaxserv.getGstTaxrateById(gsttax.getGstid()) , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<GstTaxRate>(HttpStatus.NOT_MODIFIED);
		}
	}
	
}
