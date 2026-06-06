package com.example.demo.service;

import java.util.List;

import com.example.demo.models.PurchaseOrderProducts;

import jakarta.servlet.http.HttpServletRequest;

public interface PoProductsService {

	public PurchaseOrderProducts savePurchaseOrderProducts(PurchaseOrderProducts poprod, HttpServletRequest request);
	
	public List<PurchaseOrderProducts> getPOPurchaseProductsByTempId(Integer tempid);
	
	public Integer getMaxtempId();
	
	public void RemovePoProductById(Integer id);
	
	public PurchaseOrderProducts getPurchaseorderProductById(Integer id);
	
	public PurchaseOrderProducts updatePurchaseOrderProducts(PurchaseOrderProducts poprod, HttpServletRequest request);
}
