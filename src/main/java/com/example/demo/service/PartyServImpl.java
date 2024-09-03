package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Party;
import com.example.demo.repository.PartyRepo;

@Service("partyserv")
public class PartyServImpl implements PartyService {

	@Autowired
	PartyRepo partyrepo;
	
	@Override
	public Party saveParty(Party party) {
		return partyrepo.save(party);
	}

	@Override
	public List<Party> getAllParties() {
		return partyrepo.findAll();
	}

	@Override
	public Party getpartyById(Integer id) {
		
		try {
			return partyrepo.findById(id).get();
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public int updateParty(Party party) {
		  
		return partyrepo.updateParty(party.getParty_name(),  party.getParty_id());
	}

}
