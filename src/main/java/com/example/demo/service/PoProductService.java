package com.example.demo.service;

import java.util.List;

import com.example.demo.models.PoProducts;

public interface PoProductService {

	public void savePoProductsList(PoProducts poprod);
	
	public List<PoProducts> getAllPoProductList();
	
	public PoProducts getPoProductById(Integer pid);
	
	public void updatePoProductsList(PoProducts poprod);
}
