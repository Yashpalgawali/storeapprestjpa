package com.example.demo.restcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.PurchaseOrderProducts;
import com.example.demo.service.POPurchaseOrderProdServImpl;


@RestController
@RequestMapping("purchaseorderproduct")
@CrossOrigin("*")
public class PurchaseOrderProductsRestController {

	private	POPurchaseOrderProdServImpl popurchaseorderserv;
	
	@Autowired
	public PurchaseOrderProductsRestController(POPurchaseOrderProdServImpl popurchaseorderserv) {
		this.popurchaseorderserv = popurchaseorderserv;
	}
	
	@PostMapping("/")
	public ResponseEntity<PurchaseOrderProducts> savePurchaseOrderProducts(@RequestBody PurchaseOrderProducts poproducts,HttpServletRequest request)
	{
		Integer tid = 0;
		if(popurchaseorderserv.getMaxtempId()!=null)
		{
			tid = popurchaseorderserv.getMaxtempId();
		}
		else {
			tid=1;
		}
		
		poproducts.setTemp_id(tid);
		
		PurchaseOrderProducts poprod = popurchaseorderserv.savePurchaseOrderProducts(poproducts);
		if(poprod!=null) {
			HttpSession sess = request.getSession();
			sess.setAttribute("temp_id", tid);
			return new ResponseEntity<PurchaseOrderProducts>(poprod ,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<PurchaseOrderProducts>(HttpStatus.OK);
		}
		
	}
	
}
