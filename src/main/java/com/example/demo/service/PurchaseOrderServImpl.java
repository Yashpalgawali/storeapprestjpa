package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.globalconfig.Global;
import com.example.demo.models.Activities;
import com.example.demo.models.Prefix;
import com.example.demo.models.PurchaseOrder;
import com.example.demo.models.PurchaseOrderProducts;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.PurchaseOrderProductsRepo;
import com.example.demo.repository.PurchaseOrderRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service("purshaseorderserv")
@RequiredArgsConstructor
public class PurchaseOrderServImpl implements PurchaseOrderService {

	private final PurchaseOrderRepository porderrepo;
	private final PurchaseOrderProductsRepo po_prod_repo;
	private final PrefixService prefixserv;
	private final ActivityRepository actrepo;

	@Override
	public PurchaseOrder savePurchaseOrder(PurchaseOrder porder, HttpServletRequest request) {

		HttpSession sess = request.getSession();
		Integer temp_id = (Integer) sess.getAttribute("temp_po_id");

		porder.setOrder_id(temp_id);

		porder.setPo_date(LocalDate.now().format(Global.DATE_FORMATTER));

		Prefix allPrefixes = prefixserv.getAllPrefixes();
		porder.setPrefix(allPrefixes.getFin_year());

		float total = (float) po_prod_repo.getPurchaseOrderProductsByTempId(temp_id).stream()
				.mapToDouble(PurchaseOrderProducts::getTotal).sum();

		porder.setTotal_amount(total);

		PurchaseOrder order = porderrepo.save(porder);
		
		if (order != null) {
			Activities activity = new Activities();
			activity.setActivity("Order " + order.getOrder_id() + " is Saved successfully");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
		} else {
			Activities activity = new Activities();
			activity.setActivity("Order is not Saved ");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
		}
		 return null;
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrders() {

		List<PurchaseOrder> purchaseOrdList = porderrepo.findAll();
		if (purchaseOrdList.size() > 0)
			return purchaseOrdList;
		throw new ResourceNotFoundException("Purchase Order", "List", "Purchase Order");
	}

	@Override
	public PurchaseOrder getPurchaseOrderById(Integer pid) {
		System.err.println("purchase order ID is "+pid);
		return porderrepo.findById(pid)
				.orElseThrow(() -> new ResourceNotFoundException("Purchase Order", "ID", "" + pid));
	}

	@Override
	public PurchaseOrder updatePurchaseOrder(PurchaseOrder porder) {

		return null;
		
	}

}
