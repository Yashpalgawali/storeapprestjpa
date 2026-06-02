package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Party;

public interface PartyService {

	public void saveParty(Party party);
	
	public List<Party> getAllParties();
	
	public Party getpartyById(Integer id);
	
	public void updateParty(Party part);
	
}
