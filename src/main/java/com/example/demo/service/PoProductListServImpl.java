package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.globalconfig.Global;
import com.example.demo.models.Activities;
import com.example.demo.models.PoProductsList;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.PoProductListRepository;

@Service("poprodlistserv")
public class PoProductListServImpl implements PoProductListService {

	private final PoProductListRepository poprodlistrepo;
	private final ActivityRepository actrepo;
	
	public PoProductListServImpl(PoProductListRepository poprodlistrepo, ActivityRepository actrepo) {
		super();
		this.poprodlistrepo = poprodlistrepo;
		this.actrepo = actrepo;
	}
	
	@Override
	public PoProductsList savePoProductsList(PoProductsList poprod) {
		
		int cgst_per = poprod.getGst_rate()/2;
		int igst_per = poprod.getGst_rate();
		poprod.setCgst_per(cgst_per);
		poprod.setSgst_per(cgst_per);
		poprod.setIgst_per(igst_per);
		
		PoProductsList prod = poprodlistrepo.save(poprod);
		if(prod!=null) {
			Activities activity = new Activities();
			activity.setActivity("Product "+prod.getProd_name() +" is Saved successfully");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
		}
		else {
			Activities activity = new Activities();
			activity.setActivity("Product is Not saved successfully");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
		}
			
		return prod;
	}

	@Override
	public List<PoProductsList> getAllPoProductList() {

		return poprodlistrepo.findAll();
	}

	@Override
	public PoProductsList getPoProductById(Integer pid) {
		Optional<PoProductsList> poprod = poprodlistrepo.findById(pid); 
		if(!poprod.isEmpty())
		{
			return poprod.get();
		}
		else {
			return null;
		}
	}

	@Override
	public int updatePoProductsList(PoProductsList poprod) {
		
		int cgst = poprod.getGst_rate()/2;
		int igst = poprod.getGst_rate();
		int result = poprodlistrepo.updatePoProductById(poprod.getProd_id(), poprod.getProd_name(), poprod.getProd_model(), poprod.getProd_hsn(), poprod.getProd_price(), poprod.getProd_unit(), cgst, cgst, igst);
		if(result>0) {
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
		}
		return result;
	}

}
