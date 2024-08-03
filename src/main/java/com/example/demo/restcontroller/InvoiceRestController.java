package com.example.demo.restcontroller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

import com.example.demo.models.Customer;
import com.example.demo.models.Invoice;
import com.example.demo.models.Invoice_Product;
import com.example.demo.models.Product;
import com.example.demo.models.Temp_Invoice;
import com.example.demo.service.CustomerService;
import com.example.demo.service.InvoiceProductService;
import com.example.demo.service.InvoiceService;
import com.example.demo.service.ProductService;
import com.example.demo.service.TempInvoiceService;

@RestController
@RequestMapping("invoice")
@CrossOrigin("*")
public class InvoiceRestController { 
	private ProductService prodserv;
	private InvoiceProductService invprodserv;
	private InvoiceService invserv;
	private TempInvoiceService tempinvserv;
	private TempInvoiceService tempinserv;
	private CustomerService custserv;
	
	@Autowired
	public InvoiceRestController(CustomerService custserv , TempInvoiceService tempinserv ,TempInvoiceService tempinvserv,
			InvoiceService invserv,InvoiceProductService invprodserv,ProductService prodserv) {
		this.custserv = custserv;
		this.prodserv=prodserv;
		this.invprodserv = invprodserv;
		this.tempinserv = tempinserv;
		this.tempinvserv = tempinvserv;
		this.invserv = invserv;
	}
	
	@PostMapping("/")
	public ResponseEntity<Invoice> saveInvoice(@RequestBody Invoice invoice,HttpServletRequest request)
	{
		HttpSession sess = request.getSession();
//		Integer temp_id = (Integer) sess.getAttribute("temp_id");
//		List<Temp_Invoice> tmplist = tempinserv.getTempInvById(temp_id);
		
		Integer temp_id = Integer.parseInt(""+invoice.getOrder_id());
		List<Temp_Invoice> tmplist = tempinserv.getTempInvById(temp_id);
		
		System.err.println("Products in temp invoice are \n");
		
		tmplist.stream().forEach(e->System.err.println(e));
		
	 	Float last_total=0.0f,sub_total=0.0f;
	 	
	 	int tid = tempinserv.getMaxTempInvoiceId();
	 	
	 	for(int i=0;i<tmplist.size();i++) {
	 		String pid = String.valueOf(tmplist.get(i).getProduct().getPid());
	 		Product product = prodserv.getProductById(pid);
	 		
	 		Invoice_Product invprod = new Invoice_Product();
	 		
	 		//invprod.setTemp_invoice(tmplist.get(i));
	 		
	 		invprod.setCgst(tmplist.get(i).getCgst());
	 		invprod.setSgst(tmplist.get(i).getSgst());
	 		invprod.setIgst(tmplist.get(i).getIgst());
	 		invprod.setCgst_per((int) tmplist.get(i).getCgst_per());
	 		invprod.setSgst_per((int) tmplist.get(i).getSgst_per());
	 		invprod.setIgst_per((int) tmplist.get(i).getIgst_per());
	 		invprod.setPrice(tmplist.get(i).getUnit_price());
	 		invprod.setQty(tmplist.get(i).getQty());
	 		
	 		invprod.setSubtotal(tmplist.get(i).getQty() * tmplist.get(i).getUnit_price());
	 		invprod.setTotal(tmplist.get(i).getTotal());
	 		invprod.setProduct(product);
	 		
	 		Integer order_id =  tmplist.get(i).getTemp_invoice_id();
	 		
	 		invprod.setOrder_id(String.valueOf(order_id));
	 		
	 		last_total = last_total + tmplist.get(i).getTotal();
	 		
	 		Invoice_Product inprod = invprodserv.saveInvoiceProduct(invprod);
	 		
	 	}
	
	 	Integer max_inv_no =0;
	 	max_inv_no = invserv.getMaxInvoiceNumber();
	 	
	 	if(max_inv_no!= null) {
	 		max_inv_no += 1;
	 	}	
	 	else {
	 		max_inv_no= 1;
	 	}
	 	
	 	Long maxno = Long.valueOf(max_inv_no);
	 	Date today = Date.valueOf(LocalDate.now());
	 	
	 	System.err.println("MAX INVOICE NO. = "+maxno);
	 	
	 	invoice.setDate_added(today);
	 	invoice.setTotal_amount(last_total);
	 	invoice.setInvoice_no(maxno);
	 	
	 	Long tmpid = Long.valueOf(temp_id);
	 	
	 	invoice.setOrder_id(tmpid);
	 	
	 	Invoice final_invoice = invserv.saveInvoice(invoice);
	 	if(final_invoice!=null)
	 	{
	 		sess.removeAttribute("temp_id"); // This will remove the "temporary invoice ID " from the session
	 		return new ResponseEntity<Invoice>(final_invoice ,HttpStatus.OK);
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
	public ResponseEntity<Invoice> getInvoiceByInvoiceId(@PathVariable("id")String id)
	{
		Invoice invoice = invserv.getInvoiceByInvoiceId(id);
		if(invoice!=null)
			return new ResponseEntity<Invoice>(invoice ,HttpStatus.OK);
		else
			return new ResponseEntity<Invoice>(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/order/{id}")
	public ResponseEntity<List<Invoice_Product>> getInvoiceProductsByOrderId(@PathVariable("id")String id)
	{
		List<Invoice_Product> prodlist = invprodserv.getInvoiceProductsByOrderId(id);
		 
		if(prodlist.size()>0)
			return new ResponseEntity<List<Invoice_Product>>(prodlist ,HttpStatus.OK);
		else
			return new ResponseEntity<List<Invoice_Product>>(HttpStatus.NO_CONTENT);
	}
	
//	@RequestMapping("/saveinvoice")
//	public String saveFinalInvoice(@ModelAttribute("Invoice")Invoice invoice,
//									HttpSession sess, RedirectAttributes attr,Model model) {
//		String tmp_id = (String) sess.getAttribute("temp_id");
//		Integer temp_id = Integer.parseInt(tmp_id);
//		List<Temp_Invoice> tmplist = tempinserv.getTempInvById(temp_id);
//		
//	 	Float last_total=0.0f,sub_total=0.0f;
//	 	
//	 	int tid = tempinserv.getMaxTempInvoiceId();
//	 	
//	 	for(int i=0;i<tmplist.size();i++) {
//	 		String pid = String.valueOf(tmplist.get(i).getProduct().getPid());
//	 		Product product = prodserv.getProductById(pid);
//	 		
//	 		Invoice_Product invprod = new Invoice_Product();
//	 		
//	 		//invprod.setTemp_invoice(tmplist.get(i));
//	 		
//	 		invprod.setCgst(tmplist.get(i).getCgst());
//	 		invprod.setSgst(tmplist.get(i).getSgst());
//	 		invprod.setIgst(tmplist.get(i).getIgst());
//	 		invprod.setCgst_per((int) tmplist.get(i).getCgst_per());
//	 		invprod.setSgst_per((int) tmplist.get(i).getSgst_per());
//	 		invprod.setIgst_per((int) tmplist.get(i).getIgst_per());
//	 		invprod.setPrice(tmplist.get(i).getUnit_price());
//	 		invprod.setQty(tmplist.get(i).getQty());
//	 		
//	 		invprod.setSubtotal(tmplist.get(i).getQty() * tmplist.get(i).getUnit_price());
//	 		invprod.setTotal(tmplist.get(i).getTotal());
//	 		invprod.setProduct(product);
//	 		
//	 		Integer order_id =  tmplist.get(i).getTemp_invoice_id();
//	 		
//	 		invprod.setOrder_id(String.valueOf(order_id));
//	 		
//	 		last_total = last_total + tmplist.get(i).getTotal();
//	 		
//	 		Invoice_Product inprod = invprodserv.saveInvoiceProduct(invprod);
//	 		
//	 	}
//	
//	 	Integer max_inv_no =0;
//	 	max_inv_no = invserv.getMaxInvoiceNumber();
//	 	
//	 	if(max_inv_no!= null) {
//	 		max_inv_no += 1;
//	 	}	
//	 	else {
//	 		max_inv_no= 1;
//	 	}
//	 	
//	 	Long maxno = Long.valueOf(max_inv_no);
//	 	Date today = Date.valueOf(LocalDate.now());
//	 	
//	 	invoice.setDate_added(today);
//	 	invoice.setTotal_amount(last_total);
//	 	invoice.setInvoice_no(maxno);
//	 	
//	 	Long tmpid = Long.valueOf(temp_id);
//	 	
//	 	invoice.setOrder_id(tmpid);
//	 	
//	 	Invoice final_invoice = invserv.saveInvoice(invoice);
//	 	if(final_invoice!=null) {
//	 		sess.removeAttribute("temp_id");
//	 		return "redirect:/viewinvoices";
//	 	}
//	 	return "redirect:/viewinvoices";
//	}
//	
//	@GetMapping("/viewinvoices")
//	public String viewInvoices(Model model) {
//		List<Invoice> invlist = invserv.getAllInvoices();
//
//		model.addAttribute("invlist", invlist);
//		return "ViewInvoices";
//	}
//	
//	@RequestMapping("/editinvoicebyid/{id}")
//	public String editInvoiceById(@PathVariable("id") String id, HttpSession sess ,Model model,RedirectAttributes attr)
//	{
//		List<Temp_Invoice> tilist = null;
//		List<Product> plist= prodserv.getAllProducts();
//		List<Customer> clist= custserv.getAllCustomers();
//		
//		Invoice invoice = invserv.getInvoiceByInvoiceId(id);
//		
//		sess.setAttribute("temp_invoice_id", invoice.getOrder_id());
//		sess.setAttribute("temp_id", id);
//		
//		Object tmpid = sess.getAttribute("temp_invoice_id");
//		
//		if(tmpid!=null)
//		{
//			Integer newtmpid = Integer.parseInt(tmpid.toString());
//			
//			System.out.println("\n NewTemp ID is -->> "+newtmpid+"\n\n");
//			
//			if(newtmpid > 0)
//			{
//				tilist = tempinserv.getTempInvById(newtmpid);
//
//				//tilist.stream().forEach(e-> System.out.println(e.getTemp_id()));
//				
//				model.addAttribute("plist", plist);
//				model.addAttribute("clist", clist);
//				model.addAttribute("tilist", tilist);
//				return "EditInvoice";
//			}
//			else
//			{
//				model.addAttribute("plist", plist);
//				model.addAttribute("clist", clist);
//				model.addAttribute("tilist", null);
//				
//				return "EditInvoice";
//			}
//	  }
//		else {
//			model.addAttribute("plist", plist);
//			model.addAttribute("clist", clist);
//			model.addAttribute("tilist", null);
//			
//			return "EditInvoice";
//		}
//	}
	
	
	
}
