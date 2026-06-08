package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.GlobalException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.globalconfig.Global;
import com.example.demo.models.CreditNote;
import com.example.demo.models.Customer;
import com.example.demo.repository.CreditNoteRepository;

import lombok.RequiredArgsConstructor;

@Service("creditnoteserv")
@RequiredArgsConstructor
public class CreditNoteServImpl implements CreditNoteService {

	private final CreditNoteRepository crednoterepo;

	private final CustomerService custserv;
	
	@Override
	public void saveCreditNote(CreditNote creditnote) {
		Customer cust = custserv.getCustomerById(creditnote.getCustomer().getCustomer_id());
		creditnote.setCustomer(cust);

		Integer num = crednoterepo.getMaxCreditNoteNumber();		

		if(num == null ) {
			creditnote.setCredit_note_num(1);
		}
		else {
			creditnote.setCredit_note_num(num+1);
		}
		
		creditnote.setDate_added(Global.DATE_FORMATTER.format(LocalDateTime.now()));
		CreditNote crednote = crednoterepo.save(creditnote);
		if(crednote==null) {
			throw new GlobalException("Crdit Note is not created");
		}
	}

	@Override
	public List<CreditNote> getAllCreditNotes() {
 		List<CreditNote> credNoteList = crednoterepo.findAll();
 		if(credNoteList.size() > 0 )
 			return credNoteList;
 		throw new ResourceNotFoundException("Crdit Note", "List", "credit notes");
	}

	@Override
	public CreditNote getCreditNotebyId(Integer id) {
 		return crednoterepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Credit Note", "ID", ""+id));
	}

}
