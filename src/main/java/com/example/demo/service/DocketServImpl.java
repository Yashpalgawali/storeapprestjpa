package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.GlobalException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Docket;
import com.example.demo.repository.DocketRepo;

import lombok.RequiredArgsConstructor;

@Service("dockserv")
@RequiredArgsConstructor
public class DocketServImpl implements DocketService {

	private final DocketRepo dockrepo;

	@Override
	public Docket saveDocket(Docket dock) {
		return dockrepo.save(dock);
	}

	@Override
	public List<Docket> getAllDockets() {
		return dockrepo.findAll();
	}

	@Override
	public Docket getDocketById(Integer id) {

		return dockrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Docket ", "Docket Id", "" + id));
	}

	@Override
	@Transactional
	public int updateDocket(Docket dock) {

		int result = dockrepo.updateDocket(dock.getOrder_id(), dock.getCust_name(), dock.getDocket_num(),
				dock.getParty().getParty_id(), dock.getDocket_id());
		if (result > 0) {
			return result;
		} else {
			throw new GlobalException("Docket " + dock.getDocket_num() + " is not updated");
		}
	}

	@Override
	public List<Docket> getAllDocketsWithJoin() {
		return dockrepo.getAllDockets();
	}

}