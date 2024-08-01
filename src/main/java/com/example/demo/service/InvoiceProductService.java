package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Invoice_Product;

public interface InvoiceProductService {

	public Invoice_Product saveInvoiceProduct(Invoice_Product invprod);
	
	public List<Invoice_Product> getInvoiceProductsByOrderId(String orderid);
	
}