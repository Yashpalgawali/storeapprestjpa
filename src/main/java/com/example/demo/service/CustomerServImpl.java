package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Customer;
import com.example.demo.repository.CustomerRepo;

@Service("custserv")
public class CustomerServImpl implements CustomerService {

	@Autowired
	CustomerRepo custrepo;
	
	@Override
	public Customer saveCustomer(Customer cust) {
		return custrepo.save(cust);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return custrepo.findAll();
	}

	@Override
	public Customer getCustomerById(String cid) {
		
		Long cuid = Long.parseLong(cid);
		try {
			return custrepo.findById(cuid).get();
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public int updateCustomer(Customer cust) {
		int val = custrepo.updateCustomer(cust.getCust_first_name(), cust.getCust_last_name(), cust.getCust_address(), cust.getCust_email(), cust.getCust_contact(), cust.getCust_country(),cust.getState_name(), cust.getCity_name(), cust.getCust_gst(), cust.getPincode(), cust.getCustomer_id());
		
		if(val > 0) {
			return val;
		}
		return val;
	}

}
