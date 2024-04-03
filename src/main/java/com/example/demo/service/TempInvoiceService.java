package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Temp_Invoice;

public interface TempInvoiceService {

	public Temp_Invoice saveTempInvoice(Temp_Invoice tin);
	
	public List<Temp_Invoice> getTempInvById(Integer tid);
	
	public Integer getMaxTempInvoiceId();
	
	public boolean deleteSelectedTempInvoice(String temp_id);
	
}
