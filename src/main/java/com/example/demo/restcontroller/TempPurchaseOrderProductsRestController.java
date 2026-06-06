package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;
import com.example.demo.models.PurchaseOrderProducts;
import com.example.demo.service.POPurchaseOrderProdServImpl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("purchaseorderproduct")
@RequiredArgsConstructor
public class TempPurchaseOrderProductsRestController {

	private final POPurchaseOrderProdServImpl popurchaseorderserv;

	@PostMapping("/")
	public ResponseEntity<PurchaseOrderProducts> savePurchaseOrderProducts(@RequestBody PurchaseOrderProducts poproducts,
			HttpServletRequest request) {

//		popurchaseorderserv.savePurchaseOrderProducts(poproducts, request);
//		return ResponseEntity.status(HttpStatus.CREATED)
//				.body(new ResponseDto("Purchase Order created successfully", HttpStatus.CREATED));
		
		PurchaseOrderProducts poprods =  popurchaseorderserv.savePurchaseOrderProducts(poproducts, request);
		return new ResponseEntity<PurchaseOrderProducts>(poprods, HttpStatus.CREATED);
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
	public ResponseEntity<List<PurchaseOrderProducts>> getPurchaseOrderProductsByTempId(@PathVariable String tempid) {
		
		List<PurchaseOrderProducts> tempList = popurchaseorderserv.getPOPurchaseProductsByTempId(Integer.parseInt(tempid));
		System.err.println("inside getPOPurchaseProductsByTempId() Temp Id= " + tempid + "\n");

		if (tempList.size() > 0) {
			return new ResponseEntity<List<PurchaseOrderProducts>>(tempList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<PurchaseOrderProducts>>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/remove/product/{id}")
	public ResponseEntity<ResponseDto> removePOProductById(@PathVariable Integer id) {

		popurchaseorderserv.getPurchaseorderProductById(id);

		popurchaseorderserv.RemovePoProductById(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("Product is removed", HttpStatus.OK));

	}
}
