package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.PurchaseOrderProducts;
import com.example.demo.repository.PurchaseOrderProductsRepo;

@Service("popurchaseorderserv")
public class POPurchaseOrderProdServImpl implements PoProductsService {

	private PurchaseOrderProductsRepo po_prod_repo; 
	
	@Autowired
	public POPurchaseOrderProdServImpl(PurchaseOrderProductsRepo po_prod_repo) {
		this.po_prod_repo = po_prod_repo;
	} 
	
	@Override
	public PurchaseOrderProducts savePurchaseOrderProducts(PurchaseOrderProducts poprod) {
		
		Integer tid = 0;
		
		if(po_prod_repo.getMaxTempId()!=null) {
			tid = po_prod_repo.getMaxTempId();
		}
		else {
			tid=1;
		}
		
		poprod.setTemp_id(tid);
		
		String stoption = poprod.getStoption();
		Integer cgst=0,sgst=0,igst=0;
		Integer cgst_per=0,sgst_per=0,igst_per=0;
		
		Float subtotal=0.0f,total=0.0f,unit_price =0.0f;

		unit_price = poprod.getProduct().getProd_price();
		subtotal = unit_price * poprod.getQty();
		
		poprod.setUnit_price( unit_price);
		if(stoption.equals("mh"))
		{
			cgst_per =  poprod.getProduct().getGst_rate() /2 ;
			sgst_per =  poprod.getProduct().getGst_rate() /2 ;
			igst_per = 0;
			
			cgst=Math.round( (subtotal/ 100) * cgst_per);
			sgst=Math.round( (subtotal/ 100) * sgst_per);
			igst=Math.round( (subtotal/ 100) * igst_per);
		}
		
		if(stoption.equals("ot"))
		{
			cgst_per = 0;
			sgst_per = 0;
			igst_per = (int) poprod.getProduct().getSgst_per();
			
			cgst=Math.round( (subtotal/ 100) * cgst_per);
			sgst=Math.round( (subtotal/ 100) * sgst_per);
			igst=Math.round( (subtotal/ 100) * igst_per);
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
