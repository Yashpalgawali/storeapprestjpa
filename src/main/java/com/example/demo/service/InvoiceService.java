package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.models.Invoice;

public interface InvoiceService {

	public Invoice saveInvoice(Invoice inv,HttpServletRequest request);
	
	public Integer getMaxInvoiceNumber();
	
	public List<Invoice> getAllInvoices();
	
	public Invoice getInvoiceByInvoiceId(Integer id);
	
	public int updateInvoiceById(Invoice invoice,HttpServletRequest request);
	
	

}
