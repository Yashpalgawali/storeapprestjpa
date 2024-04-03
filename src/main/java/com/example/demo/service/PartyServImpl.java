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
	public Party getpartyById(String id) {
		
		Integer pid = Integer.parseInt(id);
		try {
			return partyrepo.findById(pid).get();
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public int updateParty(Party part) {
		Integer pid = part.getParty_id();
		String pname= part.getParty_name();
		
		return partyrepo.updateParty(pname, pid);
	}

}
