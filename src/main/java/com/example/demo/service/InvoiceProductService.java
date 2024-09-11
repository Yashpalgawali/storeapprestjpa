package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.models.Invoice_Product;

public interface InvoiceProductService {

	public Invoice_Product saveInvoiceProduct(Invoice_Product invprod,HttpServletRequest request);
	
	public List<Invoice_Product> getInvoiceProductsByOrderId(String orderid);
	
	public boolean deleteInvoiceProductById(String prod_id);
}