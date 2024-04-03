package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Docket;
import com.example.demo.repository.DocketRepo;

@Service("dockserv")
public class DocketServImpl implements DocketService {

	@Autowired
	DocketRepo dockrepo;
	
	@Override
	public Docket saveDocket(Docket dock) {
		return dockrepo.save(dock);
	}

	@Override
	public List<Docket> getAllDockets() {
		return dockrepo.findAll();
	}

	@Override
	public Docket getDocketById(String id) {
		Integer did = Integer.parseInt(id);
		Docket dct = dockrepo.getById(did);
		if(dct!=null) {
			return dct ;
		}
		return dct ;
	}

	@Override
	public int updateDocket(Docket dock) {
		
		return dockrepo.updateDocket(dock.getOrder_id(), dock.getCust_name(), dock.getDocket_num(), dock.getParty().getParty_id(),dock.getDocket_id());
	}

	@Override
	public List<Docket> getAllDocketsWithJoin() {
		return dockrepo.getAllDockets();
	}

}