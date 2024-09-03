package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Prefix;

public interface PrefixService {

	public Prefix getPrefixById(Integer id);
	
	public int updatePrefixById(Prefix prefix);
	
	public List<Prefix> getAllPrefixes();
}
