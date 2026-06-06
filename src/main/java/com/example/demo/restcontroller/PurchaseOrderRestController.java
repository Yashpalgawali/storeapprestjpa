package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;
import com.example.demo.models.PurchaseOrder;
import com.example.demo.service.PoProductsService;
import com.example.demo.service.PurchaseOrderServImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("purchaseorder")
@RequiredArgsConstructor
public class PurchaseOrderRestController {

	private final PurchaseOrderServImpl porderserv;
	private final PoProductsService poprodserv;

	@PostMapping("/")
	public ResponseEntity<PurchaseOrder> savePurchaseOrder(@RequestBody PurchaseOrder porder,HttpServletRequest request)
	{
//		HttpSession sess = request.getSession();
//		 
//		porderserv.savePurchaseOrder(porder,request);
//		sess.removeAttribute("temp_po_id");
//		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("Purchase Order is created successfully", HttpStatus.CREATED));

		HttpSession sess = request.getSession();
		 
		PurchaseOrder purchaseOrder = porderserv.savePurchaseOrder(porder,request);
		sess.removeAttribute("temp_po_id");
		return new ResponseEntity<PurchaseOrder>(purchaseOrder , HttpStatus.CREATED) ;

	}
	
	@GetMapping("/")
	public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders()
	{
		List<PurchaseOrder> plist = porderserv.getAllPurchaseOrders();
		return new ResponseEntity<List<PurchaseOrder>>(plist , HttpStatus.OK);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PurchaseOrder> getPurchaseOrderById(@PathVariable Integer id) {
		PurchaseOrder porder = porderserv.getPurchaseOrderById(id);
		 
		return new ResponseEntity<PurchaseOrder>(porder , HttpStatus.OK);
		 
	}
	
	@PutMapping("/")
	public ResponseEntity<ResponseDto> updatePurchaseOrder(@RequestBody PurchaseOrder porder)
	{
		porderserv.updatePurchaseOrder(porder);
		return  ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("Purchase Order is updated Successfully",  HttpStatus.OK));
		
	}
}
