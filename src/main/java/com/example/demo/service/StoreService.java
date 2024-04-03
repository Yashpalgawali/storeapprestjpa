package com.example.demo.service;

import com.example.demo.models.StoreDetails;

public interface StoreService {

	public StoreDetails saveStore(StoreDetails store);
	
	public StoreDetails getStoreById(String id);
	
	public int updateStore(StoreDetails store);
	
}