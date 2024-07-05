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
		// TODO Auto-generated method stub
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
	public PoProductsList updatePoProductsList(PoProductsList poprod) {
		
		int res = poprodlistrepo.updatePoProductById(poprod.getProd_id(), poprod.getProd_name(), poprod.getProd_model(), poprod.getProd_hsn(), poprod.getProd_price(), poprod.getProd_unit(), poprod.getGst_rate());
		if(res>0)
			return poprodlistrepo.findById(poprod.getProd_id()).get();
		else 
			return null;
	}

}
