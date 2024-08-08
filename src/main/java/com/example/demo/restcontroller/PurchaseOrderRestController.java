package com.example.demo.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.PurchaseOrder;
import com.example.demo.service.PurchaseOrderServImpl;

@RestController
@RequestMapping("purchaseorder")
@CrossOrigin("*")
public class PurchaseOrderRestController {

	private PurchaseOrderServImpl porderserv;
	
	@Autowired
	public PurchaseOrderRestController(PurchaseOrderServImpl porderserv)
	{
		this.porderserv = porderserv;
	}
	
	@PostMapping("/")
	public ResponseEntity<PurchaseOrder> savePurchaseOrder(@RequestBody PurchaseOrder porder,HttpServletRequest request)
	{
		HttpSession sess = request.getSession();
		PurchaseOrder pord = porderserv.savePurchaseOrder(porder,request);
		if(pord!=null)
		{
			sess.removeAttribute("temp_po_id");
			return new ResponseEntity<PurchaseOrder>(pord, HttpStatus.CREATED);
		
		}
		else
			return new ResponseEntity<PurchaseOrder>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders()
	{
		List<PurchaseOrder> plist = porderserv.getAllPurchaseOrders();
		if(plist.size()>0) {
			return new ResponseEntity<List<PurchaseOrder>>(plist , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<PurchaseOrder>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PurchaseOrder> getPurchaseOrderById(@PathVariable("id")Integer id) {
		PurchaseOrder porder = porderserv.getPurchaseOrderById(id);
		if(porder!=null) {
			return new ResponseEntity<PurchaseOrder>(porder , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<PurchaseOrder>( HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<PurchaseOrder> updatePurchaseOrder(@RequestBody PurchaseOrder porder)
	{
		int result = porderserv.updatePurchaseOrder(porder);
		if(result>0)
			return new ResponseEntity<PurchaseOrder>(porderserv.getPurchaseOrderById(porder.getPo_id()), HttpStatus.OK);
		else
			return new ResponseEntity<PurchaseOrder>(HttpStatus.NOT_MODIFIED);
	}
}
