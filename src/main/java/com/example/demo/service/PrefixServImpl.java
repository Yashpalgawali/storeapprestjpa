package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Prefix;
import com.example.demo.repository.PrefixRepository;

@Service("prefixserv")
public class PrefixServImpl implements PrefixService {

	private PrefixRepository prefixrepo;
	
	public PrefixServImpl(PrefixRepository prefixrepo) {
		super();
		this.prefixrepo = prefixrepo;
	}

	@Override
	public Prefix getPrefixById(Integer id) {
		Optional<Prefix> prefixObject = prefixrepo.findById(id);
		if(!prefixObject.isEmpty()) {
			return prefixObject.get();
		}
		else {
			return null;
		}
	}

	@Override
	public int updatePrefixById(Prefix prefix) {
		 
		int result = prefixrepo.updatePrefix(prefix.getFin_year(), prefix.getSetting_id());
		return result;
	}

	@Override
	public List<Prefix> getAllPrefixes() {
	 
		return prefixrepo.findAll();
	}

}
