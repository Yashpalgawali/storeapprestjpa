package com.example.demo.mapper;

import com.example.demo.dto.CreditNoteProductDto;
import com.example.demo.models.CreditNoteProduct;
import com.example.demo.models.Product;

public class CreditNoteProductMapper {

	public static CreditNoteProduct mapToCreditNoteProduct(CreditNoteProductDto crednoteproddto,
			CreditNoteProduct crednoteprod) {

		crednoteprod.setCredit_note_prod_id(crednoteproddto.getCredit_note_prod_id());
		crednoteprod.setCgst(crednoteproddto.getCgst());
		crednoteprod.setSgst(crednoteproddto.getSgst());
		crednoteprod.setIgst(crednoteproddto.getIgst());
		crednoteprod.setCgst_per(crednoteproddto.getCgst_per());
		crednoteprod.setSgst_per(crednoteproddto.getSgst_per());
		crednoteprod.setIgst_per(crednoteproddto.getIgst_per());
		crednoteprod.setQty(crednoteproddto.getQty());
		crednoteprod.setSubtotal(crednoteproddto.getSubtotal());
		crednoteprod.setTotal(crednoteproddto.getTotal());
		crednoteprod.setPrice(crednoteproddto.getPrice());
		crednoteprod.setOrder_id(crednoteproddto.getOrder_id());
		crednoteprod.setCredit_note_date(crednoteproddto.getCredit_note_date());

		crednoteprod.setProduct(crednoteproddto.getProduct());

		return crednoteprod;
	}

	public static CreditNoteProductDto mapToCreditNoteProductDto(CreditNoteProduct crednoteprod,
			CreditNoteProductDto crednoteproddto) {

		crednoteproddto.setCredit_note_prod_id(crednoteprod.getCredit_note_prod_id());
		crednoteproddto.setCgst(crednoteprod.getCgst());
		crednoteproddto.setSgst(crednoteprod.getSgst());
		crednoteproddto.setIgst(crednoteprod.getIgst());
		crednoteproddto.setCgst_per(crednoteprod.getCgst_per());
		crednoteproddto.setSgst_per(crednoteprod.getSgst_per());
		crednoteproddto.setIgst_per(crednoteprod.getIgst_per());
		crednoteproddto.setQty(crednoteprod.getQty());
		crednoteproddto.setSubtotal(crednoteprod.getSubtotal());
		crednoteproddto.setTotal(crednoteprod.getTotal());
		crednoteproddto.setPrice(crednoteprod.getPrice());
		crednoteproddto.setCredit_note_date(crednoteprod.getCredit_note_date());
		crednoteproddto.setOrder_id(crednoteprod.getOrder_id());
		crednoteproddto.setProduct(crednoteprod.getProduct());

		return crednoteproddto;
	}
}
