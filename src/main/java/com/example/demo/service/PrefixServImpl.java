package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.GlobalException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ResourceNotModifiedException;
import com.example.demo.globalconfig.Global;
import com.example.demo.models.Activities;
import com.example.demo.models.Prefix;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.PrefixRepository;

import lombok.RequiredArgsConstructor;


@Service("prefixserv")
@RequiredArgsConstructor
public class PrefixServImpl implements PrefixService {

	private final PrefixRepository prefixrepo;
	private final ActivityRepository actrepo;

	@Override
	public Prefix getPrefixById(Integer id) {
		return prefixrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Prefix", "prefix ", ""+id)) ;		
	}

	@Override
	@Transactional
	public void updatePrefixById(Prefix prefix) {
		 
		int result = prefixrepo.updatePrefix(prefix.getFin_year(), prefix.getSetting_id());
		if(result > 0) {
			Activities act = new Activities();
			act.setActivity("Prefix "+prefix.getPrefix()+"-"+prefix.getFin_year()+" update successfully");
			act.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			act.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			
			actrepo.save(act);
		} 
		else {
			Activities act = new Activities();
			act.setActivity("Prefix "+prefix.getPrefix()+"-"+prefix.getFin_year()+" not updated ");
			act.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			act.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			
			actrepo.save(act);
			throw new ResourceNotModifiedException("Prefix "+prefix.getPrefix()+"-"+prefix.getFin_year()+" is not updated");
		}
		 
	}

	@Override
	public Prefix getAllPrefixes() {
	 
		List<Prefix> prefixList = prefixrepo.findAll();
		if(prefixList.size() > 0 )
			return prefixList.getFirst();
		throw new ResourceNotFoundException("Prefix", "Prefix", "prefixes ");
	}

	@Override
	public void savePrefix(Prefix prefix) {
		
		Prefix savedPrefix = prefixrepo.save(prefix);
		if(savedPrefix != null ) {
			Activities act = new Activities();
			act.setActivity("Prefix "+prefix.getPrefix()+"-"+prefix.getFin_year()+" saved successfully");
			act.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			act.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			
			actrepo.save(act);
		}
		else {
			Activities act = new Activities();
			act.setActivity("Prefix "+prefix.getPrefix()+"-"+prefix.getFin_year()+" is not saved ");
			act.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			act.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			
			actrepo.save(act);
			throw new GlobalException("Prefix "+prefix.getPrefix()+"-"+prefix.getFin_year()+" is not saved");
		}

	}

}
