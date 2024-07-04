package com.example.demo.service;

import java.util.List;

import com.example.demo.models.PurchaseOrderProducts;

public interface PoProductsService {

	public PurchaseOrderProducts savePurchaseOrderProducts(PurchaseOrderProducts poprod);
	
	public List<PurchaseOrderProducts> getPOPurchaseProductsByTempId(Integer tempid);
	
	
	public Integer getMaxtempId();
}
