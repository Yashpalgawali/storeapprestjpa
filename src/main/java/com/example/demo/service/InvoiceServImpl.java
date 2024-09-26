package com.example.demo.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.demo.globalconfig.Global;
import com.example.demo.models.Invoice;
import com.example.demo.models.Invoice_Product;
import com.example.demo.models.Prefix;
import com.example.demo.models.Product;
import com.example.demo.models.Temp_Invoice;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.repository.InvoiceProductRepo;
import com.example.demo.repository.InvoiceRepo;
import com.example.demo.repository.PrefixRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.TempInvoiceRepo;

@Service("invserv")
public class InvoiceServImpl implements InvoiceService {

	private InvoiceRepo invrepo;
	
	private TempInvoiceRepo tempinvrepo;
	
	private ProductRepository prodrepo;
	
	private InvoiceProductRepo invprodrepo;
	
	private CustomerRepo customerrepo;
	private PrefixRepository prefixrepo;
	
	public InvoiceServImpl(InvoiceRepo invrepo, TempInvoiceRepo tempinvrepo, ProductRepository prodrepo,
			InvoiceProductRepo invprodrepo, CustomerRepo customerrepo,PrefixRepository prefixrepo) {
		super();
		this.invrepo = invrepo;
		this.tempinvrepo = tempinvrepo;
		this.prodrepo = prodrepo;
		this.invprodrepo = invprodrepo;
		this.prefixrepo = prefixrepo;
		this.customerrepo = customerrepo;
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
	 		
	 		invprod.setOrder_id(order_id);
	 		
	 		last_total = last_total + tmplist.get(i).getTotal();
	 		invprodrepo.save(invprod);
	 		
	 	}
	
	 	Integer max_inv_no = 0;
	 	
	 	max_inv_no = this.getMaxInvoiceNumber();
	 	
	 	if(max_inv_no!= null) {
	 		max_inv_no += 1;
	 	}	
	 	else {
	 		max_inv_no= 1;
	 	}
	 	 	
	 	System.err.println("MAX INVOICE NO. = "+max_inv_no);
	 	
	 	invoice.setDate_added(LocalDate.now().format(Global.DATE_FORMATTER));
	 	invoice.setTotal_amount(last_total);
	 	invoice.setInvoice_no(max_inv_no);
	 	
	 	//Long tmpid = Long.valueOf(temp_id);
	 	
	 	invoice.setOrder_id(temp_id);
	 	Prefix prefix = prefixrepo.findById(1).get();
	 	invoice.setPrefix(prefix.getPrefix()+""+prefix.getFin_year());
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
	public Invoice getInvoiceByInvoiceId(Integer id) {
		Invoice invoice = invrepo.getInvoiceByInvoiceId(id);
		return invoice;
	}

	@Override
	public int updateInvoiceById(Invoice invoice, HttpServletRequest request) {
		
		HttpSession sess = request.getSession();
		Integer temp_id = (Integer) sess.getAttribute("temp_id");
		
		Invoice old_invoice = invrepo.findById(invoice.getInvoice_id()).get();
		
		invoice.setOrder_id(temp_id);
	 	invoice.setInvoice_no((Integer)sess.getAttribute("invoice_num"));
	 	
		Float last_total=0.0f,sub_total=0.0f,cgst=0.0f,sgst=0.0f,igst=0.0f;
	 	
		List<Invoice_Product> prodlist = invprodrepo.findInvoiceProductsByOrderId(invoice.getOrder_id());

		last_total = (float) prodlist.stream().flatMapToDouble(entity -> Arrays.stream(new double[] {
												entity.getTotal()
											} )).sum(); 
		
		invoice.setTotal_amount(last_total);
		
		invoice.setUpdated_date((LocalDate.now().format(Global.DATE_FORMATTER)));
		 
		invoice.setTotal_amount(last_total);
	 	
		String today = LocalDate.now().format(Global.DATE_FORMATTER);
		
	 	int res = invrepo.updateInvoiceById(invoice.getInvoice_id(), invoice.getOrder_id(), invoice.getInvoice_no(),
	 										last_total,invoice.getCustomer().getCustomer_id(),old_invoice.getDate_added(),
	 										today,invoice.getVehicle(),invoice.getBatch_no(),invoice.getOrderponumber());
		 
		return res;
	}
}
