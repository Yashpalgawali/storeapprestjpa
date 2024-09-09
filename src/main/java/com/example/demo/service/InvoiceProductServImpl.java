package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Invoice_Product;
import com.example.demo.repository.InvoiceProductRepo;

@Service("invprodserv")
public class InvoiceProductServImpl implements InvoiceProductService {

	private InvoiceProductRepo invprodrepo;
	
	public InvoiceProductServImpl(InvoiceProductRepo invprodrepo) {
		this.invprodrepo=invprodrepo;
	}
	
	@Override
	public Invoice_Product saveInvoiceProduct(Invoice_Product invprod) {
		return invprodrepo.save(invprod);
	}

	@Override
	public List<Invoice_Product> getInvoiceProductsByOrderId(String orderid) {
		 
		return invprodrepo.findInvoiceProductsByOrderId(orderid);
	}

	@Override
	public boolean deleteInvoiceProductById(String prod_id) {
		 
		Optional<Invoice_Product> invprod = invprodrepo.findById(Integer.parseInt(prod_id));
		if(!invprod.isEmpty()) {
			invprodrepo.deleteById(Integer.parseInt(prod_id));
			return true;
		}
		else {
			return false;
		}
		
	}
}