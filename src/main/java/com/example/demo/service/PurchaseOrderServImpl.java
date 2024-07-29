package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.PurchaseOrder;
import com.example.demo.repository.PurchaseOrderRepository;

@Service("purshaseorderserv")
public class PurchaseOrderServImpl implements PurchaseOrderService {

	private PurchaseOrderRepository porderrepo;
	
	@Autowired
	public PurchaseOrderServImpl(PurchaseOrderRepository porderrepo) {
		this.porderrepo = porderrepo;
	}
	
	@Override
	public PurchaseOrder savePurchaseOrder(PurchaseOrder porder,HttpServletRequest request) {
		
		return porderrepo.save(porder);
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrders() {
 
		return porderrepo.findAll();
	}

	@Override
	public PurchaseOrder getPurchaseOrderById(Integer pid) {
		Optional<PurchaseOrder> porder = porderrepo.findById(pid);
		if(!porder.isEmpty()) {
			return porder.get();
		}
		else {
			return null;
		}
	}

	@Override
	public int updatePurchaseOrder(PurchaseOrder porder) {

		return 0;
	}

}
