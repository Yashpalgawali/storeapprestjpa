package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Temp_Invoice;

import jakarta.servlet.http.HttpServletRequest;

public interface TempInvoiceService {

	public Temp_Invoice saveTempInvoice(Temp_Invoice tin);
	
	public List<Temp_Invoice> getTempInvById(Integer tid);
	
	public Integer getMaxTempInvoiceId();
	
	public boolean deleteSelectedTempInvoice(String temp_id);
	
	public List<Temp_Invoice> getTempInvByTempInvoiceId(Integer tid);
	
	public int updateTempInvoice(Temp_Invoice tempinv, HttpServletRequest request);
}
