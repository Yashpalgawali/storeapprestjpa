package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Party;

public interface PartyService {

	public Party saveParty(Party party);
	
	public List<Party> getAllParties();
	
	public Party getpartyById(String id);
	
	public int updateParty(Party part);
	
}
