package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Invoice_Product;

import jakarta.servlet.http.HttpServletRequest;

public interface InvoiceProductService {

	public Invoice_Product saveInvoiceProduct(Invoice_Product invprod,HttpServletRequest request);
	
	public List<Invoice_Product> getInvoiceProductsByOrderId(Integer orderid);
	
	public boolean deleteInvoiceProductById(String prod_id);
}