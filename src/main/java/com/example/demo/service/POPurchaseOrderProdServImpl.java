package com.example.demo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.demo.models.PoProductsList;
import com.example.demo.models.PurchaseOrderProducts;
import com.example.demo.repository.PoProductListRepository;
import com.example.demo.repository.PurchaseOrderProductsRepo;

@Service("popurchaseorderserv")
public class POPurchaseOrderProdServImpl implements PoProductsService {

	HttpServletRequest request;

	private PurchaseOrderProductsRepo po_prod_repo; 

	private PoProductListRepository poprodlistrepo;

	public POPurchaseOrderProdServImpl(PurchaseOrderProductsRepo po_prod_repo,PoProductListRepository poprodlistrepo) {
		this.po_prod_repo = po_prod_repo;
		this.poprodlistrepo = poprodlistrepo;
	} 

	@Override
	public PurchaseOrderProducts savePurchaseOrderProducts(PurchaseOrderProducts poprod , HttpServletRequest request) {

		HttpSession sess = request.getSession();
		Integer tid = 0;

		Integer sessid = (Integer) sess.getAttribute("temp_po_id");
		System.err.println("temp po id in session is "+sessid+"\n");
//	    if (sessid == null) {
//	    	tid = po_prod_repo.getMaxTempId();
//	        if (tid == null) {
//	        	tid = 1;
//
//	        } else {
//	        	tid += 1;
//	        }
//	        sess.setAttribute("temp_po_id", tid);
//	    }
//	    else {
//	    	tid = sessid;
//	    }
	    
	    Integer chk_tmp_id = 0; 
	    if (sessid == null) {
	        chk_tmp_id = po_prod_repo.getMaxTempId();
	        if (chk_tmp_id == null) {
	        	System.err.println("MAX temp_id is  NULL \n");
	            chk_tmp_id = 1;
	             
	        } else {
	        	System.err.println("MAX temp_id is = "+chk_tmp_id);
	            chk_tmp_id = chk_tmp_id + 1;
	        }
	        sess.setAttribute("temp_po_id", chk_tmp_id);
	        sessid=chk_tmp_id;
	    }

		poprod.setTemp_id(sessid);

		String stoption = poprod.getStoption();
		float cgst=0,sgst=0,igst=0;
		Integer cgst_per=0,sgst_per=0,igst_per=0;

		Float subtotal=0.0f,total=0.0f,unit_price =0.0f;

		unit_price = poprod.getProduct().getProd_price();
		subtotal = unit_price * poprod.getQty();

		poprod.setUnit_price( unit_price);
		System.err.println("Check state option \n Is this MH = "+stoption.equals("mh")+"\n Is this Other = "+stoption.equals("ot"));

		int gst_rate = poprod.getProduct().getIgst_per();

		if(stoption.equals("mh"))
		{
			cgst_per =  gst_rate /2 ;
			sgst_per =  gst_rate /2 ;
			igst_per = 0;

			cgst=( (subtotal/ 100) * cgst_per);
			sgst=( (subtotal/ 100) * sgst_per);
			igst=( (subtotal/ 100) * igst_per);

		    // Convert to BigDecimal, round, and convert back to float
	        BigDecimal bd = new BigDecimal(Float.toString(cgst));
	        bd = bd.setScale(2, RoundingMode.HALF_UP);
			cgst = bd.floatValue();
			sgst = cgst;
		}

		if(stoption.equals("ot"))
		{
			cgst_per = 0;
			sgst_per = 0;
			igst_per = gst_rate ;

			cgst=( (subtotal/ 100) * cgst_per);
			sgst=( (subtotal/ 100) * sgst_per);
			igst=( (subtotal/ 100) * igst_per);

			  // Convert to BigDecimal, round, and convert back to float
	        BigDecimal bd = new BigDecimal(Float.toString(igst));
	        bd = bd.setScale(2, RoundingMode.HALF_UP);
			cgst = bd.floatValue();
		}

		total = subtotal+cgst+sgst+igst;

		poprod.setCgst(cgst);
		poprod.setSgst(sgst);
		poprod.setIgst(igst);

		poprod.setCgst_per(cgst_per);
		poprod.setIgst_per(igst_per);
		poprod.setSgst_per(sgst_per);

		poprod.setTotal(total);

		Optional<PoProductsList> popd = poprodlistrepo.findById(poprod.getProduct().getProd_id());
		poprod.setProduct(popd.get());

		poprod.setPo_id(null);
		
		System.err.println("Inside savePOpurchaseorder service "+poprod.toString());
		
		return po_prod_repo.save(poprod);
	}

	@Override
	public List<PurchaseOrderProducts> getPOPurchaseProductsByTempId(Integer tempid) {
		return po_prod_repo.getPurchaseOrderProductsByTempId(tempid);
	}

	@Override
	public Integer getMaxtempId() {

		return po_prod_repo.getMaxTempId();
	}

	@Override
	public void RemovePoProductById(Integer id) {
		
		po_prod_repo.removeProductById(id);
	}

	@Override
	public PurchaseOrderProducts getPurchaseorderProductById(Integer id) {
		
		Optional<PurchaseOrderProducts> prod_obj = po_prod_repo.findById(id);
		if(!prod_obj.isEmpty()){
			return null;
		}
		else {
			return prod_obj.get();
		}
	}

}
