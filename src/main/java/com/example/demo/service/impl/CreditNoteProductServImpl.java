package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CreditNoteProductDto;
import com.example.demo.globalconfig.Global;
import com.example.demo.mapper.CreditNoteProductMapper;
import com.example.demo.models.CreditNoteProduct;
import com.example.demo.models.Product;
import com.example.demo.repository.CreditNoteProductRepository;
import com.example.demo.service.ICreditNoteProductService;
import com.example.demo.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service("creditnoteprodserv")
@RequiredArgsConstructor	
public class CreditNoteProductServImpl implements ICreditNoteProductService {

	private final CreditNoteProductRepository creditnoteprodrepo;
	private final ProductService prodserv;
	
	@Override
	public void saveCreditNoteProduct(CreditNoteProductDto creditNoteProduct,HttpServletRequest request) {
		System.err.println("Credit note Product Object is "+creditNoteProduct.toString());
		
		HttpSession sess = request.getSession();
		Integer temp_id = (Integer) sess.getAttribute("temp_id");
		
		System.err.println(
				"Inside saveCreditNoteProduct() session ID is " + sess.getId() + "\n temp_id in the session is " + temp_id);
	

		Integer chk_tmp_id = 0;
		if (temp_id == null) {
			chk_tmp_id = creditnoteprodrepo.getMaxTempCreditNoteNumber();
					
			if (chk_tmp_id == null) {
				System.err.println("MAX temp_id is  NULL \n");
				chk_tmp_id = 1;

			} else {
				System.err.println("MAX temp_id is = " + chk_tmp_id);
				chk_tmp_id = chk_tmp_id + 1;
			}
			sess.setAttribute("temp_id", chk_tmp_id);
			temp_id = chk_tmp_id;
		}

		
		float final_price=0.0f,sub_tot=0.0f,p_cust_price=0.0f,unit_price=0.0f,cgst=0.0f,sgst=0.0f,igst=0.0f,cgst_per=0.0f,sgst_per=0.0f,igst_per=0.0f,total=0.0f,custom_price=0.0f,total_gst=0.0f;
		
		Product product = prodserv.getProductById(creditNoteProduct.getProduct().getPid());
		
		String state = creditNoteProduct.getStoption();
		p_cust_price = creditNoteProduct.getCustom_price();
				
		System.err.println("User entered the custom price "+p_cust_price);
				
 		if(p_cust_price <= 0 ) {	
			unit_price =  (float) (p_cust_price / 1.18);
		}
		else {			
			unit_price = (float) (Float.parseFloat(product.getProd_price()) / 1.18); 
		}
 		
 		sub_tot = unit_price * creditNoteProduct.getQty();
 		
 		System.err.println("Unit price "+unit_price+" \n SUbtotal = "+sub_tot );
 		
 		creditNoteProduct.setPrice(p_cust_price);
 		creditNoteProduct.setSubtotal(sub_tot);
 		
		System.err.println("State Option "+state+"\n SUbtotal is "+sub_tot);
		
		if(state.equals("mh")) {
		
			sgst_per = product.getSgst_per();
			cgst_per = sgst_per;
			igst_per = 0;
			
			cgst = (sub_tot/100) * cgst_per;
			sgst = (sub_tot/100) * cgst_per;
			igst = 0;
		}
		
		if(state.equals("ot")) {
			igst_per = product.getIgst_per();
			sgst_per = cgst = 0;
			
			cgst = sgst = 0.0f;
			igst = (sub_tot/100) * igst_per;
		}
		
		total = sub_tot+cgst+sgst+igst;
		
		creditNoteProduct.setCgst(cgst);
		creditNoteProduct.setSgst(sgst);
		creditNoteProduct.setIgst(igst);
		
		creditNoteProduct.setCgst_per(cgst_per);
		creditNoteProduct.setSgst_per(cgst_per);
		creditNoteProduct.setIgst_per(igst_per);
		
		creditNoteProduct.setSubtotal(sub_tot);
		creditNoteProduct.setTotal(total);
		
		creditNoteProduct.setOrder_id(temp_id);
		creditNoteProduct.setProduct(product);
		creditNoteProduct.setCredit_note_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
		System.err.println("Inside save credit note prod service () \n "+creditNoteProduct.toString());
		creditNoteProduct.setCredit_note_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
		
	 	CreditNoteProduct mappedCreditNoteProduct = CreditNoteProductMapper.mapToCreditNoteProduct(creditNoteProduct, new CreditNoteProduct() );
	 	
		creditnoteprodrepo.save(mappedCreditNoteProduct);
	}

	@Override
	public List<CreditNoteProduct> getCreditNoteProductsByOrderId(Integer order_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
