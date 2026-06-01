package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Vendor;
import com.example.demo.service.VendorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("vendor")
@Validated
@Tag(name = "CRUD REST API for Vendor in the application", description = "CRUD REST APIs in storeapplication to CREATE,GET,UPDATE,DELETE Vendor details")
@RequiredArgsConstructor
public class VendorRestController {

	private VendorService vendorserv;

	@Operation(summary = "Create Vendor REST API", description = "REST API to create the vendor")
	@ApiResponse
	@PostMapping("/")
	public ResponseEntity<Vendor> saveVendor(@Valid @RequestBody Vendor vendor) {
		Vendor vend = vendorserv.saveVendor(vendor);

		if (vend != null) {
			return new ResponseEntity<Vendor>(vend, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Vendor>> viewVendors(HttpServletRequest request) {
		HttpSession sess = request.getSession();
		System.err.println("Inside viewvendors() \n session ID is " + sess.getId());
		return new ResponseEntity<List<Vendor>>(vendorserv.getAllVendors(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Vendor> getVendorById(@PathVariable Integer id) {
		Vendor vendor = vendorserv.getVendorById("" + id);
		if (vendor != null) {
			return new ResponseEntity<Vendor>(vendor, HttpStatus.OK);
		} else {
			return new ResponseEntity<Vendor>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/")
	public ResponseEntity<Vendor> updateVendor(@RequestBody Vendor vend) {
		vendorserv.updateVendorById(vend);

		return new ResponseEntity<Vendor>(vend, HttpStatus.OK);

	}
}
