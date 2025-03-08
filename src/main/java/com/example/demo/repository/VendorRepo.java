package com.example.demo.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Vendor;


@Repository("vendorrepo") @Transactional 
public interface VendorRepo extends JpaRepository<Vendor , Integer> {
	
	
	@Modifying
	@Query(value="update tbl_vendor  set vendor_name=:vname, vendor_email=:email,vendor_address=:address,vendor_contact=:contact,state_name=:state,city_name=:city,vendor_gst=:gst,pincode=:pin where vendor_id=:vid",nativeQuery =  true)
	int updateVendor(String vname,String email,String address,Long contact,String state,String city,String gst,Long pin,Integer vid );
	
}