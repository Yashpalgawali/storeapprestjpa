package com.example.demo.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Invoice;
import com.example.demo.models.Invoice_Product;
import com.example.demo.models.Product;
import com.example.demo.models.Temp_Invoice;
import com.example.demo.repository.InvoiceProductRepo;
import com.example.demo.repository.InvoiceRepo;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.TempInvoiceRepo;

@Service("invserv")
public class InvoiceServImpl implements InvoiceService {

	private InvoiceRepo invrepo;
	
	private TempInvoiceRepo tempinvrepo;
	
	private ProductRepository prodrepo;
	
	private InvoiceProductRepo invprodrepo;
	
	public InvoiceServImpl(InvoiceRepo invrepo, TempInvoiceRepo tempinvrepo,ProductRepository prodrepo,InvoiceProductRepo invprodrepo) {
		super();
		this.invrepo = invrepo;
		this.tempinvrepo = tempinvrepo;
		this.prodrepo = prodrepo;
		this.invprodrepo = invprodrepo;
	}

	@Override
	public Invoice saveInvoice(Invoice invoice, HttpServletRequest request) {
		
		HttpSession sess = request.getSession();
//		Integer temp_id = (Integer) sess.getAttribute("temp_id");
//		List<Temp_Invoice> tmplist = tempinserv.getTempInvById(temp_id);
		
		Integer temp_id = Integer.parseInt(""+invoice.getOrder_id());
		tempinvrepo.findById(temp_id).get();
		
		List<Temp_Invoice> tmplist = tempinvrepo.getTempInvById(temp_id);
		
	 	Float last_total=0.0f,sub_total=0.0f;
	 	
	 	int tid = tempinvrepo.getMaxTempInvoiceNum();
	 	
	 	for(int i=0;i<tmplist.size();i++) {
	 		//String pid = String.valueOf(tmplist.get(i).getProduct().getPid());
	 		
	 		Product product = prodrepo.findById(tmplist.get(i).getProduct().getPid()).get();
	 		//Product product = prodserv.getProductById(pid);
	 		
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
	 		Invoice_Product inprod = invprodrepo.save(invprod);
	 		//Invoice_Product inprod = invprodserv.saveInvoiceProduct(invprod);
	 	}
	
	 	Integer max_inv_no = 0;
	 	
	 	max_inv_no = this.getMaxInvoiceNumber();
	 	
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
	 	
	 	Invoice final_invoice = invrepo.save(invoice);
		return invrepo.save(final_invoice);
	}

	@Override
	public Integer getMaxInvoiceNumber() {
		return invrepo.getMaxInvoiceId();
	}

	@Override
	public List<Invoice> getAllInvoices() {
		return invrepo.getAllInvoices();
	}

	@Override
	public Invoice getInvoiceByInvoiceId(String id) {
		return invrepo.getInvoiceByInvoiceId(id);
	}

	@Override
	public int updateInvoiceById(Invoice invoice, HttpServletRequest request) {
		
		HttpSession sess = request.getSession();
//		Integer temp_id = (Integer) sess.getAttribute("temp_id");
//		List<Temp_Invoice> tmplist = tempinserv.getTempInvById(temp_id);
		
		Integer temp_id = Integer.parseInt(""+invoice.getOrder_id());
		tempinvrepo.findById(temp_id).get();
		
		List<Temp_Invoice> tmplist = tempinvrepo.getTempInvById(temp_id);
		
	 	Float last_total=0.0f,sub_total=0.0f;
	 	
	 //	int tid = tempinvrepo.getMaxTempInvoiceNum();
	 	
	 	for(int i=0;i<tmplist.size();i++) {
	 		//String pid = String.valueOf(tmplist.get(i).getProduct().getPid());
	 		
	 		Product product = prodrepo.findById(tmplist.get(i).getProduct().getPid()).get();
	 		//Product product = prodserv.getProductById(pid);
	 		
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
	 		Invoice_Product inprod = invprodrepo.save(invprod);
	 		//Invoice_Product inprod = invprodserv.saveInvoiceProduct(invprod);
	 	}

	 	invoice.setTotal_amount(last_total);
	 	
		
		return 0;
	}
}
