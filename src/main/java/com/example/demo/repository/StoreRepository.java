package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.StoreDetails;
 

@Repository("storerepo")
public interface StoreRepository extends JpaRepository<StoreDetails, Long> {

	@Transactional
	@Modifying
	@Query(value="update tbl_store_details set store_name=?1,store_address=?2,store_contact=?3,toll_free_no=?4 where store_id=?5",nativeQuery = true)
	public int updateStoreDetails(String sname, String saddress,String scontact,String toll,Long sid);
	
}
