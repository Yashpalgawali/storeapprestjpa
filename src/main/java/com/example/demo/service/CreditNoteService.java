package com.example.demo.service;

import java.util.List;

import com.example.demo.models.CreditNote;

public interface CreditNoteService {

	public void saveCreditNote(CreditNote creditnote);
	
	public List<CreditNote> getAllCreditNotes();
	
	public CreditNote getCreditNotebyId(Integer id);
	
	public CreditNote getCreditNotebyOrderId(Integer orderid);
}
