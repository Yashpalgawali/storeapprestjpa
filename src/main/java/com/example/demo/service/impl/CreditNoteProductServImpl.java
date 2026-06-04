package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.CreditNoteProduct;
import com.example.demo.repository.CreditNoteProductRepository;
import com.example.demo.service.ICreditNoteProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service("creditnoteprodserv")
@RequiredArgsConstructor	
public class CreditNoteProductServImpl implements ICreditNoteProductService {

	private final CreditNoteProductRepository creditnoteprodrepo;
	
	@Override
	public void saveCreditNoteProduct(CreditNoteProduct creditNoteProduct,HttpServletRequest request) {
		HttpSession sess = request.getSession();
		
		Integer sessid = (Integer) sess.getAttribute("temp_id");
		System.err.println(
				"Inside saveTempInvoice() session ID is " + sess.getId() + "\n temp_id in the session is " + sessid);
 
		Integer chk_tmp_id = 0;
		if (sessid == null) {
//			chk_tmp_id = creditnoteprodrepo
					
			if (chk_tmp_id == null) {
				System.err.println("MAX temp_id is  NULL \n");
				chk_tmp_id = 1;

			} else {
				System.err.println("MAX temp_id is = " + chk_tmp_id);
				chk_tmp_id = chk_tmp_id + 1;
			}
			sess.setAttribute("temp_id", chk_tmp_id);
			sessid = chk_tmp_id;
		}

	}

	@Override
	public List<CreditNoteProduct> getCreditNoteProductsByOrderId(Integer order_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
