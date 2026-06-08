package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CreditNoteProductDto;
import com.example.demo.models.CreditNoteProduct;

import jakarta.servlet.http.HttpServletRequest;

public interface ICreditNoteProductService {

	public List<CreditNoteProduct> getCreditNoteProductsByOrderId(Integer order_id);

	public CreditNoteProductDto saveCreditNoteProduct(CreditNoteProductDto creditNoteProduct,
			HttpServletRequest request);
	
	public void deleteCreditNoteProductByCreditNoteProductId(Integer id);
}
