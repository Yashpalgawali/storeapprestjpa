package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.example.demo.models.Temp_Invoice;
import com.example.demo.repository.TempInvoiceRepo;

@Service("tempinvserv")
public class TempInvoiceServImpl implements TempInvoiceService {

	@Autowired
	TempInvoiceRepo tempinvrepo;
	
	@Override
	public Temp_Invoice saveTempInvoice(Temp_Invoice tin) {
		return tempinvrepo.save(tin);
	}

	@Override
	public List<Temp_Invoice> getTempInvById(Integer tid) {
		return tempinvrepo.getTempInvById(tid);
	}

	@Override
	public Integer getMaxTempInvoiceId() {
		return  tempinvrepo.getMaxTempInvoiceNum();
	}

	@Override
	public boolean deleteSelectedTempInvoice(String temp_id) {
		Integer tid = Integer.parseInt(temp_id);

		if(tempinvrepo.existsById(tid)) {
			tempinvrepo.deleteById(tid);
			return true;
		}
		else {
			return false;
		}
	}

}