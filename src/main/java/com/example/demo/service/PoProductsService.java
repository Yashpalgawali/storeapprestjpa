package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.models.PurchaseOrderProducts;

public interface PoProductsService {

	public PurchaseOrderProducts savePurchaseOrderProducts(PurchaseOrderProducts poprod, HttpSession sess);
	
	public List<PurchaseOrderProducts> getPOPurchaseProductsByTempId(Integer tempid);
	
	
	public Integer getMaxtempId();
}
