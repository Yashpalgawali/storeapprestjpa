package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.models.PurchaseOrderProducts;

public interface PoProductsService {

	public PurchaseOrderProducts savePurchaseOrderProducts(PurchaseOrderProducts poprod, HttpServletRequest request);
	
	public List<PurchaseOrderProducts> getPOPurchaseProductsByTempId(Integer tempid);
	
	public Integer getMaxtempId();
	
	public void RemovePoProductById(Integer id);
	
	public PurchaseOrderProducts getPurchaseorderProductById(Integer id);
}
