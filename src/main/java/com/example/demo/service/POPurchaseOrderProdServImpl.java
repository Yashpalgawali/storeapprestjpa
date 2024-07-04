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
		// TODO Auto-generated method stub
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
