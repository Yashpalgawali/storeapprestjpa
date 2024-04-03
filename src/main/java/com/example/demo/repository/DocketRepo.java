package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Docket;

@Repository("dockrepo")
public interface DocketRepo extends JpaRepository<Docket, Integer> {

	@Modifying
	@Transactional
	@Query(value="update tbl_docket set order_id=?1,cust_name=?2,docket_num=?3,party_id=?4 where docket_id=?5",nativeQuery = true)
	Integer updateDocket(Long oid,String cname,Long dnum,Integer pid,Integer did);
	
	@Query(value="select * from tbl_docket join tbl_party on tbl_party.party_id=tbl_docket.party_id" ,nativeQuery = true)
	List<Docket> getAllDockets();
	
}