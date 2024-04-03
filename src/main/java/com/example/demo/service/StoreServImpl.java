package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.StoreDetails;
import com.example.demo.repository.StoreRepository;

@Service("storeserv")
public class StoreServImpl implements StoreService {

	@Autowired
	StoreRepository storerepo;
	
	@Override
	public StoreDetails saveStore(StoreDetails store) {
		return storerepo.save(store);
	}

	@Override
	public StoreDetails getStoreById(String id) {
		
		Long sid = Long.parseLong(id);
		return storerepo.getById(sid);
	}

	@Override
	public int updateStore(StoreDetails store) {
		return storerepo.updateStoreDetails(store.getStore_name(), store.getStore_address(), store.getStore_contact(), store.getToll_free_no(), store.getStore_id());
	}
}
