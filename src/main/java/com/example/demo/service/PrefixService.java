package com.example.demo.service;

import com.example.demo.models.Prefix;

public interface PrefixService {

	public Prefix getPrefixById(Integer id);
	
	public void updatePrefixById(Prefix prefix);
	
	public Prefix getAllPrefixes();
	
	public void savePrefix(Prefix prefix);
}
