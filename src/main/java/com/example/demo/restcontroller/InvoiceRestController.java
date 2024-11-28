package com.example.demo.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Invoice;
import com.example.demo.models.Invoice_Product;
import com.example.demo.service.InvoiceProductService;
import com.example.demo.service.InvoiceService;

@RestController
@RequestMapping("invoice")
public class InvoiceRestController { 
	 
	private InvoiceProductService invprodserv;
	private InvoiceService invserv;
	 
	public InvoiceRestController(InvoiceProductService invprodserv, InvoiceService invserv) {
		this.invprodserv = invprodserv;
		this.invserv = invserv;
	}


	@PostMapping("/")
	public ResponseEntity<Invoice> saveInvoice(@RequestBody Invoice invoice,HttpServletRequest request)
	{
		HttpSession sess = request.getSession();
	 	Invoice final_invoice = invserv.saveInvoice(invoice, request);
	 	if(final_invoice!=null)
	 	{
	 		sess.removeAttribute("temp_id"); // This will remove the "temporary invoice ID " from the session
	 		return new ResponseEntity<Invoice>(final_invoice ,HttpStatus.CREATED);
	 	}
	 	else
	 		return new ResponseEntity<Invoice>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	@GetMapping("/")
	public ResponseEntity<List<Invoice>> getAllInvoices() {
		List<Invoice> invlist = invserv.getAllInvoices();
		if(invlist.size()>0)
			return new  ResponseEntity<List<Invoice>>(invlist , HttpStatus.OK) ;
		else
			return new  ResponseEntity<List<Invoice>>( HttpStatus.NO_CONTENT) ;
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<Invoice> getInvoiceByInvoiceId(@PathVariable Integer id,HttpServletRequest request)
	{
		Invoice invoice = invserv.getInvoiceByInvoiceId(id);
		if(invoice!=null)
		{
			HttpSession sess = request.getSession();
			if(sess.getAttribute("temp_id")!=null)
			{
				return new ResponseEntity<Invoice>(invoice ,HttpStatus.OK);
			}
			else {
				sess.setAttribute("temp_id", invoice.getOrder_id());
				sess.setAttribute("invoice_num", invoice.getInvoice_no());
				return new ResponseEntity<Invoice>(invoice ,HttpStatus.OK);
			}
		}
		else
		{
			return new ResponseEntity<Invoice>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/order/{id}")
	public ResponseEntity<List<Invoice_Product>> getInvoiceProductsByOrderId(@PathVariable Integer id)
	{
		List<Invoice_Product> prodlist = invprodserv.getInvoiceProductsByOrderId(id);
		 
		if(prodlist.size()>0)
			return new ResponseEntity<List<Invoice_Product>>(prodlist ,HttpStatus.OK);
		else
			return new ResponseEntity<List<Invoice_Product>>(HttpStatus.NO_CONTENT);
	}
	 
	@PutMapping("/")
	public ResponseEntity<String> updateInvoice(@RequestBody Invoice invoice,HttpServletRequest request)
	{
		int result = invserv.updateInvoiceById(invoice, request);
		if(result>0) {
			return new ResponseEntity<String>(HttpStatus.OK);		
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED); 
		}
		 
	}
}
