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
	@Query(value="UPDATE Party p SET p.party_name=:pname WHERE p.party_id=:pid")
	Integer updateParty(String pname,Integer pid);
	
}
