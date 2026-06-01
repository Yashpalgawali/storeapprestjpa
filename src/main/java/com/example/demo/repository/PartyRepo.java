package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Party;

@Repository("partyrepo")
public interface PartyRepo extends JpaRepository<Party, Integer> {

	@Modifying
	@Query(value = "UPDATE Party p SET p.party_name=:pname WHERE p.party_id=:pid")
	Integer updateParty(String pname, Integer pid);

}
