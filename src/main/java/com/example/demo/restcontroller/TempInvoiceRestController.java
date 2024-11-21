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

import com.example.demo.models.Product;
import com.example.demo.models.Temp_Invoice;
import com.example.demo.service.InvoiceProductService;
import com.example.demo.service.ProductService;
import com.example.demo.service.TempInvoiceService;

@RestController
@RequestMapping("tempinvoice")
@CrossOrigin("*")
public class TempInvoiceRestController {
	
//	private TempInvoiceService tempinvserv;
//	private HttpSession sess;
	private ProductService prodserv;
	private TempInvoiceService tempinserv;
    
	private InvoiceProductService invprodserv;
	
	public TempInvoiceRestController(ProductService prodserv, TempInvoiceService tempinserv) {
		super();
		this.prodserv = prodserv;
		this.tempinserv = tempinserv;
	}
//    TempInvoiceRestController(HttpSession sess,TempInvoiceService tempinserv,TempInvoiceService tempinvserv,ProductService prodserv) {
//    	this.sess=sess;
//    	this.tempinserv=tempinserv;
//    	this.tempinvserv=tempinvserv;
//    	this.prodserv=prodserv;
//    }
	
	@PostMapping("/")
	public ResponseEntity<List<Temp_Invoice>> saveTempInvoice(@RequestBody Temp_Invoice teinv,HttpServletRequest request)
	{
		HttpSession sess = request.getSession();
		Integer sessid = (Integer) sess.getAttribute("temp_id"); 
		System.err.println("Inside saveTempInvoice() session ID is "+sess.getId()+"\n temp_id in the session is "+sessid);
		
	    Integer chk_tmp_id = 0; 
	    if (sessid == null) {
	        chk_tmp_id = tempinserv.getMaxTempInvoiceId();
	        if (chk_tmp_id == null) {
	        	System.err.println("MAX temp_id is  NULL \n");
	            chk_tmp_id = 1;
	             
	        } else {
	        	System.err.println("MAX temp_id is = "+chk_tmp_id);
	            chk_tmp_id = chk_tmp_id + 1;
	        }
	        sess.setAttribute("temp_id", chk_tmp_id);
	        sessid=chk_tmp_id;
	    }
	    
	    System.err.println("inside saveTempInvoice() sessid = "+sessid);
	    		
		Long 	prod_id 	= teinv.getProduct().getPid();
		Long 	p_hsn 		= teinv.getProduct().getProd_hsn();
		Integer p_qty   	= teinv.getQty();
		Float   p_cust_price= teinv.getCustom_price();
		Float unit_price=0.0f;
		
		float sub_tot,cgst,sgst,igst,total;
		
		Product tem = prodserv.getProductById((String.valueOf(prod_id)));
 		if(p_cust_price> 0){
			unit_price = (float) (p_cust_price / 1.18);
		}
		else{
			unit_price = (float) (Float.parseFloat(tem.getProd_price())/(1.18));
		}
		
		sub_tot = unit_price * teinv.getQty();
		
		if(teinv.getStoption().equals("mh")) {
			teinv.setCgst_per(tem.getCgst_per());
			teinv.setSgst_per(tem.getSgst_per());
			teinv.setIgst(0);

			cgst = Math.round((sub_tot/100) * tem.getCgst_per());
			sgst = Math.round((sub_tot/100) * tem.getSgst_per());
			igst = Math.round((sub_tot/100) * teinv.getIgst_per());
		}
		else {	
			teinv.setIgst_per(tem.getIgst_per());
			teinv.setCgst_per(0);
			teinv.setSgst_per(0);
			
			cgst = Math.round((sub_tot/100) * teinv.getCgst_per());
			sgst = Math.round((sub_tot/100) * teinv.getSgst_per());
			igst = Math.round((sub_tot/100) * tem.getIgst_per());
		}
		
		
		teinv.setTemp_invoice_id(sessid);
		teinv.setCgst(cgst);
		teinv.setSgst(sgst);
		teinv.setIgst(igst);
		
		Long phsn = tem.getProd_hsn();
		String nhsn = String.valueOf(phsn);
		teinv.setHsn(nhsn);
		teinv.setUnit(tem.getProd_unit());
		teinv.setUnit_price(unit_price);
		teinv.setTotal(sub_tot+cgst+sgst+igst);
		
		Temp_Invoice tmpinv = tempinserv.saveTempInvoice(teinv);
		if(tmpinv!=null) {
			return new ResponseEntity<List<Temp_Invoice>>(tempinserv.getTempInvByTempInvoiceId(sessid) , HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<List<Temp_Invoice>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@GetMapping("/{temp_id}")
	public ResponseEntity<List<Temp_Invoice>> getAllTempInvoiceByTempInvoiceID(@PathVariable Integer temp_id) {
		List<Temp_Invoice> tempinvoice = tempinserv.getTempInvByTempInvoiceId(temp_id);
		  
		if(tempinvoice.size()>0)
			return new ResponseEntity<List<Temp_Invoice>>(tempinvoice, HttpStatus.OK);
		else
			return new ResponseEntity<List<Temp_Invoice>>( HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/removeitem/{id}")
	public ResponseEntity<String> removeTemp_invoice(@PathVariable String id) {
		boolean res = tempinserv.deleteSelectedTempInvoice(id);
		if(res) {
			return new ResponseEntity<String>("true",HttpStatus.OK);	
		}
		else {
			return new ResponseEntity<String>("false",HttpStatus.NOT_MODIFIED);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<String> updateTempInvoice(@RequestBody Temp_Invoice teinv,HttpServletRequest request)
	{
		HttpSession sess = request.getSession();
		int result = tempinserv.updateTempInvoice(teinv, request);
		if(result>0) {
			sess.setAttribute("temp_id", teinv.getTemp_id());
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
		}
	}
	
//	@PutMapping("/")
//	public String updateTempInvoice(@RequestBody Temp_Invoice teinv,HttpServletRequest request)
//	{
//		HttpSession sess = request.getSession();
//		String sessid = (String) sess.getAttribute(""+teinv.getTemp_id());
//		
//		Long prod_id = teinv.getProduct().getPid();
//		
//		Float unit_price=0.0f;
//		
//		float sub_tot,cgst,sgst,igst;
//		
//		Product tem = prodserv.getProductById((String.valueOf(prod_id)));
//		
//		if(teinv.getUnit_price() > 0) {
//			unit_price = teinv.getUnit_price();
//		}
//		else {
//			unit_price = Float.parseFloat(tem.getProd_price());
//		}
//		
//		sub_tot = unit_price * teinv.getQty();
//		
//		if(teinv.getStoption().equals("mh")) {
//			teinv.setCgst_per(tem.getCgst_per());
//			teinv.setSgst_per(tem.getSgst_per());
//			teinv.setIgst(0);
//		
//			cgst = Math.round((sub_tot/100) * tem.getCgst_per());
//			sgst = Math.round((sub_tot/100) * tem.getSgst_per());
//			igst = Math.round((sub_tot/100) * teinv.getIgst_per());
//		
//			System.out.println("Maharashtra state is selected \n Product PRICE=>> "+unit_price+"Product ID is---> "+teinv.getProduct().getPid()+"\n CGST-->> "+cgst+"\nSGST-->> "+sgst+"\nIGST--"+ ">> "+igst);
//		
//		}
//		else {	
//			teinv.setIgst_per(tem.getIgst_per());
//			teinv.setCgst_per(0);
//			teinv.setSgst_per(0);
//			
//			cgst = Math.round((sub_tot/100) * teinv.getCgst_per());
//			sgst = Math.round((sub_tot/100) * teinv.getSgst_per());
//			igst = Math.round((sub_tot/100) * tem.getIgst_per());
//			
//			System.out.println("Other state is selected \n Product PRICE=>> "+teinv.getUnit_price() +"Product ID is---> "+teinv.getProduct().getPid()+"\n CGST-->> "+cgst+"\nSGST-->> "+sgst+"\nIGST--"
//					+ ">> "+igst);
//		}
//		
//		String sid = (String) sess.getAttribute("temp_invoice_id");
//		
//		Integer tid = Integer.parseInt(sid);
//		
//		teinv.setTemp_invoice_id(tid);
//		teinv.setCgst(cgst);
//		teinv.setSgst(sgst);
//		teinv.setIgst(igst);
//		
//		 
//		String nhsn = String.valueOf(tem.getProd_hsn());
//		teinv.setHsn(nhsn);
//		teinv.setUnit(tem.getProd_unit());
//		
//		teinv.setTotal(sub_tot+cgst+sgst+igst);
//		
//		tempinserv.saveTempInvoice(teinv);
//		return "redirect:/addinvoice";
//	
//	}
}
