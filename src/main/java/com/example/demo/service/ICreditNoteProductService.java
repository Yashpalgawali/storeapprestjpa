package com.example.demo.service;

import java.util.List;

import com.example.demo.models.CreditNoteProduct;

import jakarta.servlet.http.HttpServletRequest;

public interface ICreditNoteProductService {

	public void saveCreditNoteProduct(CreditNoteProduct creditNoteProduct,HttpServletRequest request);
	
	public List<CreditNoteProduct> getCreditNoteProductsByOrderId(Integer order_id);
}
