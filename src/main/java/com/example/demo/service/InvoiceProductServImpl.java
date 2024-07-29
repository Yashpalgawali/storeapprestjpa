package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Invoice_Product;
import com.example.demo.repository.InvoiceProductRepo;

@Service("invprodserv")
public class InvoiceProductServImpl implements InvoiceProductService {

	
	private InvoiceProductRepo invprodrepo;
	
	@Autowired
	public InvoiceProductServImpl(InvoiceProductRepo invprodrepo) {
		this.invprodrepo=invprodrepo;
	}
	
	@Override
	public Invoice_Product saveInvoiceProduct(Invoice_Product invprod) {
		return invprodrepo.save(invprod);
	}
}