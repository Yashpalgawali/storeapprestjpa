package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.GlobalException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.globalconfig.Global;
import com.example.demo.models.Activities;
import com.example.demo.models.Party;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.PartyRepo;

@Service("partyserv")
public class PartyServImpl implements PartyService {

	private final PartyRepo partyrepo;
	private final ActivityRepository actrepo;
	
	public PartyServImpl(PartyRepo partyrepo, ActivityRepository actrepo) {
		super();
		this.partyrepo = partyrepo;
		this.actrepo = actrepo;
	}

	@Override
	public Party saveParty(Party party) {
		Party part = partyrepo.save(party);
		if(part!=null) {
			Activities activity = new Activities();
			activity.setActivity("Party "+part.getParty_name()+" is saved successfully");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
			return part;
		}
		else {
			Activities activity = new Activities();
			activity.setActivity("Party  is saved successfully");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
			
			throw new GlobalException("Party "+party.getParty_name()+" is not saved");
		}
	
	}

	@Override
	public List<Party> getAllParties() {
		return partyrepo.findAll();
	}

	@Override
	public Party getpartyById(Integer id) {
		
		return partyrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Party", "Patry ID", ""+id));
		 
	}

	@Override
	public int updateParty(Party party) {
		  
		Integer res = partyrepo.updateParty(party.getParty_name(),  party.getParty_id());
		if(res >0) {
			Activities activity = new Activities();
			activity.setActivity("Party "+party.getParty_name()+" is updated successfully");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
			return res;
		}
		else {
			Activities activity = new Activities();
			activity.setActivity("Party "+party.getParty_name()+" is not updated ");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
			
			throw new GlobalException("Party "+party.getParty_name()+" is not updated");
		}
	
	}

}
