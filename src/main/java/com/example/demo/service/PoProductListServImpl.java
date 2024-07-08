package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.PoProductsList;
import com.example.demo.repository.PoProductListRepository;

@Service("poprodlistserv")
public class PoProductListServImpl implements PoProductListService {

	private PoProductListRepository poprodlistrepo;
	
	@Autowired
	public PoProductListServImpl(PoProductListRepository poprodlistrepo ) {
		this.poprodlistrepo=poprodlistrepo ;
	}
	
	@Override
	public PoProductsList savePoProductsList(PoProductsList poprod) {
		
		int cgst_per = poprod.getGst_rate()/2;
		int igst_per = poprod.getGst_rate();
		poprod.setCgst_per(cgst_per);
		poprod.setSgst_per(cgst_per);
		poprod.setIgst_per(igst_per);
		System.err.println("cgst per is "+cgst_per+"\n sgst_per "+(poprod.getGst_rate()/2)+"\n IGST per = "+igst_per);
		System.err.println("Inside savePoProductsList service \n "+poprod.toString());
		return poprodlistrepo.save(poprod);
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
		
		System.err.println("Inside update poproducts \n"+poprod.toString());
		
		int cgst = poprod.getGst_rate()/2;
		int igst = poprod.getGst_rate();
		int result = poprodlistrepo.updatePoProductById(poprod.getProd_id(), poprod.getProd_name(), poprod.getProd_model(), poprod.getProd_hsn(), poprod.getProd_price(), poprod.getProd_unit(), cgst, cgst, igst);
		
		return result;
	}

}
