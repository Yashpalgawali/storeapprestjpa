package com.example.demo.restcontroller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.models.Customer;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("customer")
@CrossOrigin("*")
public class CustomerRestController {

	
	private CustomerService custserv;
	
	@Autowired
	public CustomerRestController(CustomerService custserv) {
		this.custserv=custserv;
	}
	
	
	@PostMapping("/")
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
		Customer cust = custserv.saveCustomer(customer);
		if(cust!=null)
			return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
		else
			return new ResponseEntity<Customer>(customer, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return  new ResponseEntity<List<Customer>>(custserv.getAllCustomers(),HttpStatus.OK); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
		Customer cust = custserv.getCustomerById(""+id);
		if(cust!=null)
			return new ResponseEntity<Customer>(cust,HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		int res = custserv.updateCustomer(customer);
		if(res>0)
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
}
