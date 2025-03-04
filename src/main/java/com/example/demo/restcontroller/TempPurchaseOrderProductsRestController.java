package com.example.demo.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.PurchaseOrderProducts;
import com.example.demo.service.POPurchaseOrderProdServImpl;

@RestController
@RequestMapping("purchaseorderproduct")
@CrossOrigin("*")
public class TempPurchaseOrderProductsRestController {

	private	POPurchaseOrderProdServImpl popurchaseorderserv;
	
	public TempPurchaseOrderProductsRestController(POPurchaseOrderProdServImpl popurchaseorderserv) {
		this.popurchaseorderserv = popurchaseorderserv;
	}
	
	@PostMapping("/")
	public ResponseEntity<List<PurchaseOrderProducts>> savePurchaseOrderProducts(@RequestBody PurchaseOrderProducts poproducts,HttpServletRequest request)
	{
		 
		PurchaseOrderProducts poprod = popurchaseorderserv.savePurchaseOrderProducts(poproducts, request);
		if(poprod!=null) {
			List<PurchaseOrderProducts> polist = popurchaseorderserv.getPOPurchaseProductsByTempId(poprod.getTemp_id());
			return new ResponseEntity<List<PurchaseOrderProducts>>(polist ,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<List<PurchaseOrderProducts>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@PostMapping("/")
//	public ResponseEntity<PurchaseOrderProducts> savePurchaseOrderProducts(@RequestBody PurchaseOrderProducts poproducts,HttpServletRequest request)
//	{
//		System.err.println("INside save purchase products() controller \n "+poproducts.toString());
//		
//		PurchaseOrderProducts poprod = popurchaseorderserv.savePurchaseOrderProducts(poproducts);
//		if(poprod!=null) {
//			HttpSession sess = request.getSession();
//			sess.setAttribute("temp_id", poprod.getTemp_id() );
//			return new ResponseEntity<PurchaseOrderProducts>(poprod ,HttpStatus.OK);
//		}
//		else {
//			return new ResponseEntity<PurchaseOrderProducts>(HttpStatus.OK);
//		}
//	}
	
	@GetMapping("/{tempid}")
	public ResponseEntity<List<PurchaseOrderProducts>> getPurchaseOrderProductsByTempId(@PathVariable Integer tempid)
	{
		List<PurchaseOrderProducts> tempList = popurchaseorderserv.getPOPurchaseProductsByTempId(tempid);
		System.err.println("inside getPOPurchaseProductsByTempId() Temp Id= "+tempid+"\n");
		tempList.stream().forEach(e->System.err.println(e));
		if(tempList.size()>0) {
			return new ResponseEntity<List<PurchaseOrderProducts>>(tempList ,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<PurchaseOrderProducts>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/remove/product/{id}")
	public ResponseEntity<String> removePOProductById(@PathVariable Integer id ) {
		
		PurchaseOrderProducts prod_obj = popurchaseorderserv.getPurchaseorderProductById(id);
		
		//System.err.println("inside delete mapping \n Product is "+prod_obj.toString());
		if(prod_obj!=null) {
			popurchaseorderserv.RemovePoProductById(id);
			return new ResponseEntity<String>("Product is removed",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Product is NOT removed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
