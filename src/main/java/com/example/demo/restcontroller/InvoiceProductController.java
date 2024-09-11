package com.example.demo.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Invoice_Product;
import com.example.demo.service.InvoiceProductService;

@RestController
@RequestMapping("invoiceproduct")
@CrossOrigin("*")
public class InvoiceProductController {

	private InvoiceProductService invprodserv;

	public InvoiceProductController(InvoiceProductService invprodserv) {
		super();
		this.invprodserv = invprodserv;
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteInvoiceProductById(@PathVariable("id") String id){
		
		boolean res = invprodserv.deleteInvoiceProductById(id);
		if(res) {
			return new  ResponseEntity<String>(HttpStatus.OK);
		}
		else {
			return new  ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
		}
	}
	
	@GetMapping("/{order_id}")
	public ResponseEntity<List<Invoice_Product>> getAllInvoiceProductsByOrderId(@PathVariable("order_id") String order_id){
		
		List<Invoice_Product> prodlist = invprodserv.getInvoiceProductsByOrderId(order_id);
		
		if(prodlist.size()>0 ) {
			return new ResponseEntity<List<Invoice_Product>>(prodlist ,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Invoice_Product>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<String> addInvoiceProducts(@RequestBody Invoice_Product invprod,HttpServletRequest request) {
		
		HttpSession sess = request.getSession();
		
		System.err.println("temp _ID is = "+ sess.getAttribute("temp_id"));
		
	
		System.err.println("Inside saveinvproduct \n "+invprod.toString());
		invprodserv.saveInvoiceProduct(invprod,request);
		return null;
	}
}
