package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;
import com.example.demo.models.GstTaxRate;
import com.example.demo.service.GstTaxService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("gsttaxrate")
@RequiredArgsConstructor
public class GstTaxRateController {

	private final GstTaxService gsttaxserv;
	
	@PostMapping("/")
	public ResponseEntity<ResponseDto> saveGstTaxRate(@RequestBody GstTaxRate gsttax) {
		gsttaxserv.saveGstTaxRate(gsttax);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("Gst Tax Rate "+gsttax.getTaxrate()+" is created successfully", HttpStatus.OK));
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<GstTaxRate>> getAllGstTaxRates() {
		List<GstTaxRate> gst = gsttaxserv.getAllGstTaxRates();
		return new ResponseEntity<List<GstTaxRate>>(gst , HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GstTaxRate> getGstTaxRateById(Integer id) {
	
		GstTaxRate gsttax = gsttaxserv.getGstTaxrateById(id);
		return ResponseEntity.status(HttpStatus.OK).body(gsttax);
			
	}
	
	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateGstTaxRate(@RequestBody GstTaxRate gsttax) {
		gsttaxserv.updateGstTaxRate(gsttax);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("Gst Tax Rate "+gsttax.getTaxrate()+" is updated successfully", HttpStatus.OK));
		
	}
	
}
