package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.GlobalException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ResourceNotModifiedException;
import com.example.demo.globalconfig.Global;
import com.example.demo.models.Activities;
import com.example.demo.models.Customer;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.CustomerRepo;

import lombok.RequiredArgsConstructor;

@Service("custserv")
@RequiredArgsConstructor
public class CustomerServImpl implements CustomerService {

	private final CustomerRepo custrepo;

	private final ActivityRepository actrepo;

	@Override
	public Customer saveCustomer(Customer cust) {

		Customer customer = custrepo.save(cust);
		if (customer != null) {
			actrepo.save(new Activities(
					cust.getCust_first_name() + " " + cust.getCust_last_name() + " is saved successfully",
					Global.DATE_FORMATTER.format(LocalDateTime.now()),
					Global.TIME_FORMATTER.format(LocalDateTime.now())));
			return customer;
		} else {
			throw new GlobalException("Customer " + cust.getCust_first_name() + " is not saved");
		}

	}

	@Override
	public List<Customer> getAllCustomers() {

		return custrepo.findAll();
	}

	@Override
	public Customer getCustomerById(Long cid) {

		return custrepo.findById(cid)
				.orElseThrow(() -> new ResourceNotFoundException("CustomerID", "Customer ID", "" + cid));
	}

	@Override
	@Transactional
	public int updateCustomer(Customer cust) {
		int val = custrepo.updateCustomer(cust.getCust_first_name(), cust.getCust_last_name(), cust.getCust_address(),
				cust.getCust_email(), cust.getCust_contact(), cust.getCust_country(), cust.getState_name(),
				cust.getCity_name(), cust.getCust_gst(), cust.getPincode(), cust.getCustomer_id());

		if (val > 0) {
			actrepo.save(new Activities(
					cust.getCust_first_name() + " " + cust.getCust_last_name() + " is updated successfully",
					Global.DATE_FORMATTER.format(LocalDateTime.now()),
					Global.TIME_FORMATTER.format(LocalDateTime.now())));
			return val;
		} else {
			throw new ResourceNotModifiedException("Customer " + cust.getCust_first_name() + " is not updated");
		}

	}

}
