package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Party;

@Repository("partyrepo")
public interface PartyRepo extends JpaRepository<Party, Integer> {

	
	@Transactional
	@Modifying
	@Query(value="update tbl_party set party_name=?1 where party_id=?2",nativeQuery = true)
	Integer updateParty(String pname,Integer pid);
	
}
