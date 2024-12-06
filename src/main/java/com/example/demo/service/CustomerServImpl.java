package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.globalconfig.Global;
import com.example.demo.models.Activities;
import com.example.demo.models.Customer;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.CustomerRepo;

@Service("custserv")
public class CustomerServImpl implements CustomerService {

 
	private final CustomerRepo custrepo;
	
	private final ActivityRepository actrepo;
	
	public CustomerServImpl(CustomerRepo custrepo,ActivityRepository actrepo) {
		super();
		this.custrepo = custrepo;
		this.actrepo = actrepo;
	}

	@Override
	public Customer saveCustomer(Customer cust) {
		
		Customer customer = custrepo.save(cust);
		if(customer!=null)
		{	
			actrepo.save(new Activities(cust.getCust_first_name()+" "+cust.getCust_last_name() +" is saved successfully",  Global.DATE_FORMATTER.format(LocalDateTime.now()) , Global.TIME_FORMATTER.format(LocalDateTime.now())));
		}
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		
		return custrepo.findAll();
	}

	@Override
	public Customer getCustomerById(Long cid) {
		
		try {
			return custrepo.findById(cid).get();
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public int updateCustomer(Customer cust) {
		int val = custrepo.updateCustomer(cust.getCust_first_name(), cust.getCust_last_name(), cust.getCust_address(), cust.getCust_email(), cust.getCust_contact(), cust.getCust_country(),cust.getState_name(), cust.getCity_name(), cust.getCust_gst(), cust.getPincode(), cust.getCustomer_id());
		
		if(val > 0) {
			actrepo.save(new Activities(cust.getCust_first_name()+" "+cust.getCust_last_name() +" is updated successfully",  Global.DATE_FORMATTER.format(LocalDateTime.now()) , Global.TIME_FORMATTER.format(LocalDateTime.now())));
		}
		return val;
	}

}
