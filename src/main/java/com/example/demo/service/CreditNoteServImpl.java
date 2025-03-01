package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.CreditNote;
import com.example.demo.repository.CreditNoteRepository;

@Service("creditnoteserv")
public class CreditNoteServImpl implements CreditNoteService {

	private final CreditNoteRepository crednoterepo;
	
	public CreditNoteServImpl(CreditNoteRepository crednoterepo) {
		super();
		this.crednoterepo = crednoterepo;
	}

	@Override
	public CreditNote saveCreditNote(CreditNote creditnote) {
 		return null;
	}

	@Override
	public List<CreditNote> getAllCreditNotes() {
 		return crednoterepo.findAll();
	}

	@Override
	public CreditNote getCreditNotebyId(Integer id) {
 		return null;
	}

}
