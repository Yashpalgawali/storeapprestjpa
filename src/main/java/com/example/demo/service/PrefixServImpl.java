package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.GlobalException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ResourceNotModifiedException;
import com.example.demo.models.Prefix;
import com.example.demo.repository.PrefixRepository;

import lombok.RequiredArgsConstructor;


@Service("prefixserv")
@RequiredArgsConstructor
public class PrefixServImpl implements PrefixService {

	private final PrefixRepository prefixrepo;

	@Override
	public Prefix getPrefixById(Integer id) {
		return prefixrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Prefix", "prefix ", ""+id)) ;		
	}

	@Override
	@Transactional
	public void updatePrefixById(Prefix prefix) {
		 
		int result = prefixrepo.updatePrefix(prefix.getFin_year(), prefix.getSetting_id());
		if(result < 0) 
		 throw new ResourceNotModifiedException("Prefix "+prefix.getPrefix()+"-"+prefix.getFin_year()+" is not updated");
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
		if(savedPrefix == null )
			throw new GlobalException("Prefix "+prefix.getPrefix()+"-"+prefix.getFin_year()+" is not saved");
	}

}
