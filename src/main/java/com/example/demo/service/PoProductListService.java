package com.example.demo.service;

import java.util.List;

import com.example.demo.models.PoProductsList;

public interface PoProductListService {

	public PoProductsList savePoProductsList(PoProductsList poprod);
	
	public List<PoProductsList> getAllPoProductList();
	
	public PoProductsList getPoProductById(Integer pid);
	
	public int updatePoProductsList(PoProductsList poprod);
}
