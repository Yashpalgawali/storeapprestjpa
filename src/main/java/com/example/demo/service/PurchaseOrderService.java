package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.models.PurchaseOrder;

public interface PurchaseOrderService {

	public PurchaseOrder savePurchaseOrder(PurchaseOrder porder,HttpServletRequest request);
	
	public List<PurchaseOrder> getAllPurchaseOrders();
	
	public PurchaseOrder getPurchaseOrderById(Integer pid);
	
	public int updatePurchaseOrder(PurchaseOrder porder);
	
	
}
