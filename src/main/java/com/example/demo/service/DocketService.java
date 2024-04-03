package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Docket;

public interface DocketService {

	public Docket saveDocket(Docket dock);
	
	public List<Docket> getAllDockets();
	
	public Docket getDocketById(String id);
	
	public int updateDocket(Docket dock);
	
	public List<Docket> getAllDocketsWithJoin();
	
}
