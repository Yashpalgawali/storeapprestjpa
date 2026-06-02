package com.example.demo.restcontroller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;
import com.example.demo.models.Customer;
import com.example.demo.service.ActivityService;
import com.example.demo.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerRestController {

	private final CustomerService custserv;

	@PostMapping("/")
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
		Customer cust = custserv.saveCustomer(customer);
		if (cust != null)
			return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
		else
			return new ResponseEntity<Customer>(customer, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<List<Customer>>(custserv.getAllCustomers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {

		Customer cust = custserv.getCustomerById(id);

		return new ResponseEntity<Customer>(cust, HttpStatus.OK);

	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateCustomer(@RequestBody Customer customer) {
		int res = custserv.updateCustomer(customer);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("Customer "+customer.getCust_first_name()+" is saved successfully",HttpStatus.OK));
		
	}
}
