package com.example.demo.mapper;

import com.example.demo.dto.CreditNoteDto;
import com.example.demo.models.CreditNote;

public class CreditNoteMapper {

	public static CreditNote maptToCreditNote(CreditNote creditnote, CreditNoteDto creditnotedto) {

		creditnote.setCredit_note_id(creditnotedto.getCredit_note_id());
		creditnote.setCredit_note_num(creditnotedto.getCredit_note_num());
		creditnote.setDate_added(creditnotedto.getDate_added());
		creditnote.setPrefix(creditnotedto.getPrefix());
		creditnote.setOrder_id(creditnotedto.getOrder_id());
		creditnote.setCustomer(creditnotedto.getCustomer());
		creditnote.setInvoice(creditnotedto.getInvoice());

		return creditnote;
	}

	public static CreditNoteDto maptToCreditNoteDto(CreditNoteDto creditnotedto, CreditNote creditnote) {

		creditnotedto.setCredit_note_id(creditnote.getCredit_note_id());
		creditnotedto.setCredit_note_num(creditnote.getCredit_note_num());
		creditnotedto.setDate_added(creditnote.getDate_added());
		creditnotedto.setPrefix(creditnote.getPrefix());
		creditnotedto.setOrder_id(creditnote.getOrder_id());
		creditnotedto.setCustomer(creditnote.getCustomer());
		creditnotedto.setInvoice(creditnote.getInvoice());

		return creditnotedto;
	}
}
