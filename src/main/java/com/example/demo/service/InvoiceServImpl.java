package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Invoice;
import com.example.demo.repository.InvoiceRepo;

@Service("invserv")
public class InvoiceServImpl implements InvoiceService {

	@Autowired
	InvoiceRepo invrepo;
	
	@Override
	public Invoice saveInvoice(Invoice inv) {
		return invrepo.save(inv);
	}

	@Override
	public Integer getMaxInvoiceNumber() {
		return invrepo.getMaxInvoiceId();
	}

	@Override
	public List<Invoice> getAllInvoices() {
		return invrepo.getAllInvoices();
	}

	@Override
	public Invoice getInvoiceByInvoiceId(String id) {
		return invrepo.getInvoiceByInvoiceId(id);
	}
}
