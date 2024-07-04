package com.example.demo.restcontroller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Product;
import com.example.demo.models.Temp_Invoice;
import com.example.demo.service.ProductService;
import com.example.demo.service.TempInvoiceService;

@RestController
@RequestMapping("tempinvoice")
@CrossOrigin("*")
public class TempInvoiceRestController {
	@Autowired
	ProductService prodserv;
	
	@Autowired
	TempInvoiceService tempinvserv;

	@Autowired
	TempInvoiceService tempinserv;
	
    @Autowired
    HttpSession sess;

	@PostMapping("/")
	public ResponseEntity<List<Temp_Invoice>> saveTempInvoice(@RequestBody Temp_Invoice teinv, 
														  HttpServletRequest request)
	{ 
		
		HttpSession sess = request.getSession();
	   
		Integer sessid = (Integer) sess.getAttribute("temp_id");
		    
	    Integer chk_tmp_id = 0;
		
	    if (sessid == null) {
	        chk_tmp_id = tempinserv.getMaxTempInvoiceId();
	        if (chk_tmp_id == null) {
	            chk_tmp_id = 1;
	             
	        } else {
	            chk_tmp_id = chk_tmp_id + 1;
	        }
	        sess.setAttribute("temp_id", chk_tmp_id);
	    }

		
		Long 	prod_id 	= teinv.getProduct().getPid();
		Long 	p_hsn 		= teinv.getProduct().getProd_hsn();
		Integer p_qty   	= teinv.getQty();
		Float   p_cust_price= teinv.getCustom_price(),unit_price=0.0f;
		
		float sub_tot,cgst,sgst,igst,total;
		
		Product tem = prodserv.getProductById((String.valueOf(prod_id)));
		
		if(teinv.getUnit_price() > 0){
			unit_price = teinv.getUnit_price();
		}
		else{
			unit_price = Float.parseFloat(tem.getProd_price());
		}
		
		sub_tot = unit_price * teinv.getQty();
		
		if(teinv.getStoption().equals("mh")) {
			teinv.setCgst_per(tem.getCgst_per());
			teinv.setSgst_per(tem.getSgst_per());
			teinv.setIgst(0);

			cgst = Math.round((sub_tot/100) * tem.getCgst_per());
			sgst = Math.round((sub_tot/100) * tem.getSgst_per());
			igst = Math.round((sub_tot/100) * teinv.getIgst_per());
		
			System.out.println("Maharashtra state is selected \n Product PRICE=>> "+unit_price+"\nProduct ID is---> "+teinv.getProduct().getPid()+"\n CGST-->> "+cgst+"\nSGST-->> "+sgst+"\nIGST--"+ ">> "+igst);
		
		}
		else {	
			teinv.setIgst_per(tem.getIgst_per());
			teinv.setCgst_per(0);
			teinv.setSgst_per(0);
			
			cgst = Math.round((sub_tot/100) * teinv.getCgst_per());
			sgst = Math.round((sub_tot/100) * teinv.getSgst_per());
			igst = Math.round((sub_tot/100) * tem.getIgst_per());
			
			System.out.println("Other state is selected \n Product PRICE=>> "+teinv.getUnit_price() +"\nProduct ID is---> "+teinv.getProduct().getPid()+"\n CGST-->> "+cgst+"\nSGST-->> "+sgst+"\nIGST--"
					+ ">> "+igst);
		}
		
		Integer tid = (Integer) sess.getAttribute("temp_id");
		teinv.setTemp_invoice_id(tid);
		teinv.setCgst(cgst);
		teinv.setSgst(sgst);
		teinv.setIgst(igst);
		
		Long phsn = tem.getProd_hsn();
		String nhsn = String.valueOf(phsn);
		teinv.setHsn(nhsn);
		teinv.setUnit(tem.getProd_unit());
		
		teinv.setTotal(sub_tot+cgst+sgst+igst);
		
		Temp_Invoice tmpinv = tempinserv.saveTempInvoice(teinv);
		if(tmpinv!=null)
			return new ResponseEntity<List<Temp_Invoice>>(tempinserv.getTempInvByTempInvoiceId(tid) , HttpStatus.OK);
		else
			return new ResponseEntity<List<Temp_Invoice>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	
	@GetMapping("/{temp_id}")
	public ResponseEntity<List<Temp_Invoice>> getAllTempInvoiceByTempInvoiceID(@PathVariable("temp_id")Integer temp_id) {
		List<Temp_Invoice> tempinvoice = tempinserv.getTempInvByTempInvoiceId(temp_id);
		if(tempinvoice.size()>0)
			return new ResponseEntity<List<Temp_Invoice>>(tempinvoice, HttpStatus.OK);
		else
			return new ResponseEntity<List<Temp_Invoice>>( HttpStatus.NO_CONTENT);
	}
	
//	@RequestMapping("/savetempinvoice")
//	public String saveTempInvoice(@ModelAttribute("Temp_Invoice")Temp_Invoice teinv,BindingResult br , HttpSession sess,RedirectAttributes attr)
//	{
//		Integer chk_tmp_id=0;
//				
//		String sessid = (String) sess.getAttribute("temp_id");
//		
//		Object obj = sess.getAttribute("temp_id");
//		
//		if(sessid!=null)  {
//			sess.setAttribute("temp_id", sess.getAttribute("temp_id") );
//			System.out.println("Session Id is not null or empty Max Temp ID is ->> "+chk_tmp_id);
//		}
//		else {
//			chk_tmp_id  = tempinserv.getMaxTempInvoiceId();
//			if(chk_tmp_id==null)
//			{
//				chk_tmp_id=1;
//				sess.setAttribute("temp_id", ""+chk_tmp_id);
//			}
//			else {
//				chk_tmp_id  = chk_tmp_id +1;
//				sess.setAttribute("temp_id", ""+chk_tmp_id);
//			}
//		}
//			
//		Long 	prod_id 	= teinv.getProduct().getPid();
//		Long 	p_hsn 		= teinv.getProduct().getProd_hsn();
//		Integer p_qty   	= teinv.getQty();
//		Float   p_cust_price= teinv.getCustom_price(),unit_price=0.0f;
//		
//		float sub_tot,cgst,sgst,igst,total;
//		
//		Product tem = prodserv.getProductById((String.valueOf(prod_id)));
//		
//		if(teinv.getUnit_price() > 0){
//			unit_price = teinv.getUnit_price();
//		}
//		else{
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
//		String sid = (String) sess.getAttribute("temp_id");
//		
//		Integer tid = Integer.parseInt(sid);
//		
//		teinv.setTemp_invoice_id(tid);
//		teinv.setCgst(cgst);
//		teinv.setSgst(sgst);
//		teinv.setIgst(igst);
//		
//		Long phsn = tem.getProd_hsn();
//		String nhsn = String.valueOf(phsn);
//		teinv.setHsn(nhsn);
//		teinv.setUnit(tem.getProd_unit());
//		
//		teinv.setTotal(sub_tot+cgst+sgst+igst);
//		
//		tempinserv.saveTempInvoice(teinv);
//	
//		return "redirect:/addinvoice";
//	}	
	
	
	@RequestMapping("/removeitem/{id}")@ResponseBody
	public boolean removeTemp_invoice(@PathVariable("id") String id) {
		boolean res = tempinserv.deleteSelectedTempInvoice(id);
		 
		if(res) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@RequestMapping("/updatetempinvoice")
	public String updateTempInvoice(@ModelAttribute("Temp_Invoice")Temp_Invoice teinv,HttpSession sess ,RedirectAttributes attr)
	{
		String sessid = (String) sess.getAttribute("temp_id");
		sess.getAttribute("temp_invoice_id");
		
		Long prod_id = teinv.getProduct().getPid();
		
		Float unit_price=0.0f;
		
		float sub_tot,cgst,sgst,igst;
		
		Product tem = prodserv.getProductById((String.valueOf(prod_id)));
		
		if(teinv.getUnit_price() > 0) {
			unit_price = teinv.getUnit_price();
		}
		else {
			unit_price = Float.parseFloat(tem.getProd_price());
		}
		
		sub_tot = unit_price * teinv.getQty();
		
		if(teinv.getStoption().equals("mh")) {
			teinv.setCgst_per(tem.getCgst_per());
			teinv.setSgst_per(tem.getSgst_per());
			teinv.setIgst(0);
		
			cgst = Math.round((sub_tot/100) * tem.getCgst_per());
			sgst = Math.round((sub_tot/100) * tem.getSgst_per());
			igst = Math.round((sub_tot/100) * teinv.getIgst_per());
		
			System.out.println("Maharashtra state is selected \n Product PRICE=>> "+unit_price+"Product ID is---> "+teinv.getProduct().getPid()+"\n CGST-->> "+cgst+"\nSGST-->> "+sgst+"\nIGST--"+ ">> "+igst);
		
		}
		else {	
			teinv.setIgst_per(tem.getIgst_per());
			teinv.setCgst_per(0);
			teinv.setSgst_per(0);
			
			cgst = Math.round((sub_tot/100) * teinv.getCgst_per());
			sgst = Math.round((sub_tot/100) * teinv.getSgst_per());
			igst = Math.round((sub_tot/100) * tem.getIgst_per());
			
			System.out.println("Other state is selected \n Product PRICE=>> "+teinv.getUnit_price() +"Product ID is---> "+teinv.getProduct().getPid()+"\n CGST-->> "+cgst+"\nSGST-->> "+sgst+"\nIGST--"
					+ ">> "+igst);
		}
		
		String sid = (String) sess.getAttribute("temp_invoice_id");
		
		Integer tid = Integer.parseInt(sid);
		
		teinv.setTemp_invoice_id(tid);
		teinv.setCgst(cgst);
		teinv.setSgst(sgst);
		teinv.setIgst(igst);
		
		Long phsn = tem.getProd_hsn();
		String nhsn = String.valueOf(phsn);
		teinv.setHsn(nhsn);
		teinv.setUnit(tem.getProd_unit());
		
		teinv.setTotal(sub_tot+cgst+sgst+igst);
		
		tempinserv.saveTempInvoice(teinv);
		return "redirect:/addinvoice";
	
	}
}
