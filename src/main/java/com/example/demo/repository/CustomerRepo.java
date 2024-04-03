package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Customer;


@Repository("custrepo")
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	
	@Modifying
	@Transactional
	@Query(value="update tbl_customer set cust_first_name=?1,cust_last_name=?2,cust_address=?3,cust_email=?4,cust_contact=?5,cust_country=?6,state_name=?7,city_name=?8,cust_gst=?9,pincode=?10 where customer_id=?11",nativeQuery =  true)
	Integer updateCustomer(String cfname,String clname,String addr,String email,Long cont,String country,String state,String city,
				String gst,Long pin,Long cid);
}
