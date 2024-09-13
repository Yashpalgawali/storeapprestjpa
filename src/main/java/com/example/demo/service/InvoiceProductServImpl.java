package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.demo.models.Invoice_Product;
import com.example.demo.models.Product;
import com.example.demo.repository.InvoiceProductRepo;
import com.example.demo.repository.ProductRepository;

@Service("invprodserv")
public class InvoiceProductServImpl implements InvoiceProductService {

	private InvoiceProductRepo invprodrepo;
	
	private ProductRepository prodrepo;
	
	public InvoiceProductServImpl(InvoiceProductRepo invprodrepo, ProductRepository prodrepo) {
		this.invprodrepo=invprodrepo;
		this.prodrepo=prodrepo;
	}

	@Override
	public Invoice_Product saveInvoiceProduct(Invoice_Product invprod, HttpServletRequest request) {
		
		HttpSession sess = request.getSession();
		Integer temp_id = (Integer) sess.getAttribute("temp_id");
		
		Product prod = prodrepo.findById(invprod.getProduct().getPid()).get();
		
		float cgst=0.0f,igst=0.0f,sgst=0.0f,unit_price=0.0f,sub_tot=0.0f,p_cust_price=0.0f,igst_per=0.0f,cgst_per=0.0f,sgst_per=0.0f,total=0.0f;
		
		String state = invprod.getStoption();
		p_cust_price = invprod.getCustom_price();
		
 		if(p_cust_price> 0) {
			unit_price =  (float) (p_cust_price / 1.18);
		}
		else {
			
			unit_price = (float) (Float.parseFloat(prod.getProd_price()) / 1.18); 
		}
 		
 		sub_tot = unit_price * invprod.getQty();
		invprod.setPrice(p_cust_price);
		invprod.setSubtotal(sub_tot);
		
		if(state.equals("mh")) {
		
			sgst_per = prod.getSgst_per();
			cgst_per = sgst_per;
			igst_per = 0;
			
			cgst = (sub_tot/100) * cgst_per;
			sgst = (sub_tot/100) * cgst_per;
			igst = 0;
		}
		
		if(state.equals("ot")) {
			igst_per = prod.getIgst_per();
			sgst_per = cgst = 0;
			
			cgst = sgst = 0.0f;
			igst = (sub_tot/100) * igst_per;
		}
		
		total = sub_tot+cgst+sgst+igst;
		
		invprod.setCgst(cgst);
		invprod.setSgst(sgst);
		invprod.setIgst(igst);
		
		invprod.setCgst_per(cgst_per);
		invprod.setSgst_per(cgst_per);
		invprod.setIgst_per(igst_per);
		
		invprod.setSubtotal(sub_tot);
		invprod.setTotal(total);
		
		invprod.setOrder_id(temp_id);
		invprod.setInvoice(null);
		System.err.println("Inside save invprod service () \n "+invprod.toString());
		
		return invprodrepo.save(invprod);
	}

	@Override
	public List<Invoice_Product> getInvoiceProductsByOrderId(Integer orderid) {
		return invprodrepo.findInvoiceProductsByOrderId(orderid);
	}

	@Override
	public boolean deleteInvoiceProductById(String prod_id) {
		Optional<Invoice_Product> invprod = invprodrepo.findById(Integer.parseInt(prod_id));
		if(!invprod.isEmpty()) {
			invprodrepo.deleteById(Integer.parseInt(prod_id));
			return true;
		}
		else {
			return false;
		}
	}
	
	 
}