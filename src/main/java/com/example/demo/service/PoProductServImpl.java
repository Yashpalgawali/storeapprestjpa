package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.GlobalException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.globalconfig.Global;
import com.example.demo.models.Activities;
import com.example.demo.models.PoProducts;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.PoProductRepository;

import lombok.RequiredArgsConstructor;

@Service("poprodserv")
@RequiredArgsConstructor
public class PoProductServImpl implements PoProductService {

	private final PoProductRepository poprodlistrepo;
	private final ActivityRepository actrepo;	
	
	@Override
	public void savePoProductsList(PoProducts poprod) {

		int cgst_per = poprod.getGst_rate()/2;
		int igst_per = poprod.getGst_rate();
		poprod.setCgst_per(cgst_per);
		poprod.setSgst_per(cgst_per);
		poprod.setIgst_per(igst_per);
		
		PoProducts prod = poprodlistrepo.save(poprod);
		if(prod!=null) {
			Activities activity = new Activities();
			activity.setActivity("PO Product "+prod.getProd_name() +" is Saved successfully");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
		}
		else {
			Activities activity = new Activities();
			activity.setActivity("PO Product is Not saved successfully");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
			
			throw new GlobalException("PO Product "+poprod.getProd_name()+" is Not saved ");
		}
			
		 
	}

	@Override
	public List<PoProducts> getAllPoProductList() {

		List<PoProducts> poProdList = poprodlistrepo.findAll();
		if(poProdList.size() > 0 )
			return poProdList;
		throw new ResourceNotFoundException("Po Product", "Po Product", "po product");
	}

	@Override
	public PoProducts getPoProductById(Integer pid) {
		return poprodlistrepo.findById(pid).orElseThrow(()-> new ResourceNotFoundException("Id", "Po Product ID", ""+pid));
		
	}

	@Override
	public void updatePoProductsList(PoProducts poprod) {

		int cgst = poprod.getGst_rate()/2;
		int igst = poprod.getGst_rate();
		int result = poprodlistrepo.updatePoProductById(poprod.getProd_id(), poprod.getProd_name(), poprod.getProd_model(), poprod.getProd_hsn(), poprod.getProd_price(), poprod.getProd_unit(), cgst, cgst, igst);
		if(result > 0) {
			Activities activity = new Activities();
			activity.setActivity("Purchase Product "+poprod.getProd_name() +" is updated successfully");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
		}
		else {
			Activities activity = new Activities();
			activity.setActivity("Purchase Product "+poprod.getProd_name() +" is not updated ");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
			
			throw new GlobalException("PO Product "+poprod.getProd_name()+" is not updated ");
		}
		 
	}

}
