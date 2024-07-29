package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.PurchaseOrderProducts;
import com.example.demo.repository.PurchaseOrderProductsRepo;

@Service("popurchaseorderserv")
public class POPurchaseOrderProdServImpl implements PoProductsService {

	HttpServletRequest request;
	
	private PurchaseOrderProductsRepo po_prod_repo; 
	
	@Autowired
	public POPurchaseOrderProdServImpl(PurchaseOrderProductsRepo po_prod_repo) {
		this.po_prod_repo = po_prod_repo;
	} 
	
	@Override
	public PurchaseOrderProducts savePurchaseOrderProducts(PurchaseOrderProducts poprod , HttpSession sess ) {
		
		Integer tid = 0;
		
		Integer sessid = (Integer) sess.getAttribute("temp_po_id");
	   
	    if (sessid == null) {
	    	tid = po_prod_repo.getMaxTempId();
	        if (tid == null) {
	        	tid = 1;
	             
	        } else {
	        	tid += 1;
	        }
	        sess.setAttribute("temp_po_id", tid);
	    }
	    else {
	    	tid = sessid;
	    }
		
		poprod.setTemp_id(tid);
		
		String stoption = poprod.getStoption();
		Integer cgst=0,sgst=0,igst=0;
		Integer cgst_per=0,sgst_per=0,igst_per=0;
		
		Float subtotal=0.0f,total=0.0f,unit_price =0.0f;

		unit_price = poprod.getProduct().getProd_price();
		subtotal = unit_price * poprod.getQty();
		
		poprod.setUnit_price( unit_price);
		System.err.println("Check state option \n Is this MH = "+stoption.equals("mh")+"\n Is this Other = "+stoption.equals("ot"));
		
		int gst_rate = poprod.getProduct().getIgst_per();
		
		if(stoption.equals("mh"))
		{
			System.err.println("state is MH \n");
			cgst_per =  gst_rate /2 ;
			sgst_per =  gst_rate /2 ;
			igst_per = 0;
			
			cgst=Math.round( (subtotal/ 100) * cgst_per);
			sgst=Math.round( (subtotal/ 100) * sgst_per);
			igst=Math.round( (subtotal/ 100) * igst_per);
			
			System.err.println("cgst_per = "+cgst_per);
		}
		
		if(stoption.equals("ot"))
		{
			System.err.println("state is OTher \n");
			cgst_per = 0;
			sgst_per = 0;
			igst_per = gst_rate ;
			
			cgst=Math.round( (subtotal/ 100) * cgst_per);
			sgst=Math.round( (subtotal/ 100) * sgst_per);
			igst=Math.round( (subtotal/ 100) * igst_per);
			
			System.err.println("igst_per = "+igst_per+" \n CGST = "+cgst_per);
		}
		
		total = subtotal+cgst+sgst+igst;
		
		poprod.setCgst(cgst);
		poprod.setSgst(sgst);
		poprod.setIgst(igst);
		
		poprod.setCgst_per(cgst_per);
		poprod.setIgst_per(igst_per);
		poprod.setSgst_per(sgst_per);
		
		poprod.setTotal(total);
		System.err.println("Purchase order product OBject = "+poprod.toString());
		return po_prod_repo.save(poprod);
	}

	@Override
	public List<PurchaseOrderProducts> getPOPurchaseProductsByTempId(Integer tempid) {
		// TODO Auto-generated method stub
		return po_prod_repo.getPurchaseOrderProductsByTempId(tempid);
	}

	@Override
	public Integer getMaxtempId() {
		
		return po_prod_repo.getMaxTempId();
	}

}
