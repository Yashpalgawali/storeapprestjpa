package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.example.demo.globalconfig.Global;
import com.example.demo.models.Activities;
import com.example.demo.models.Product;
import com.example.demo.models.Temp_Invoice;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.TempInvoiceRepo;

@Service("tempinvserv")
public class TempInvoiceServImpl implements TempInvoiceService {

	
	private TempInvoiceRepo tempinvrepo;
	
	private ActivityRepository actrepo;
	
	private ProductRepository prodrepo;
	
	public TempInvoiceServImpl(TempInvoiceRepo tempinvrepo, ActivityRepository actrepo,ProductRepository prodrepo) {
		super();
		this.tempinvrepo = tempinvrepo;
		this.actrepo = actrepo;
		this.prodrepo = prodrepo;
	}

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
			
//			Activities act = new Activities("Product with ID "+temp_id+" deleted from table ", LocalDate.now().format(Global.DATE_FORMATTER),  LocalDate.now().format(Global.TIME_FORMATTER));
//			actrepo.save(act);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<Temp_Invoice> getTempInvByTempInvoiceId(Integer tid) {
		
		return tempinvrepo.getTempInvByTempInvoiceId(tid);
	}

	@Override
	public int updateTempInvoice(Temp_Invoice teinv,HttpServletRequest request) {
		
		Long 	prod_id 	= teinv.getProduct().getPid();
		Long 	p_hsn 		= teinv.getProduct().getProd_hsn();
		Integer p_qty   	= teinv.getQty();
		Float   p_cust_price= teinv.getCustom_price();
		Float unit_price=0.0f;
		
		float sub_tot,cgst,sgst,igst,total;
		
		Product tem = prodrepo.findById(prod_id).get();
 		if(p_cust_price> 0){
			unit_price = (float) (p_cust_price / 1.18);
		}
		else{
			unit_price = (float) (Float.parseFloat(tem.getProd_price())/(1.18));
		}
		
		sub_tot = unit_price * teinv.getQty();
		
		if(teinv.getStoption().equals("mh")) {
			teinv.setCgst_per(tem.getCgst_per());
			teinv.setSgst_per(tem.getSgst_per());
			teinv.setIgst(0);

			cgst = Math.round((sub_tot/100) * tem.getCgst_per());
			sgst = Math.round((sub_tot/100) * tem.getSgst_per());
			igst = Math.round((sub_tot/100) * teinv.getIgst_per());
		}
		else {	
			teinv.setIgst_per(tem.getIgst_per());
			teinv.setCgst_per(0);
			teinv.setSgst_per(0);
			
			cgst = Math.round((sub_tot/100) * teinv.getCgst_per());
			sgst = Math.round((sub_tot/100) * teinv.getSgst_per());
			igst = Math.round((sub_tot/100) * tem.getIgst_per());
		}
		
		teinv.setTemp_invoice_id(teinv.getTemp_id());
		teinv.setCgst(cgst);
		teinv.setSgst(sgst);
		teinv.setIgst(igst);
		
		Long phsn = tem.getProd_hsn();
		String nhsn = String.valueOf(phsn);
		teinv.setHsn(nhsn);
		teinv.setUnit(tem.getProd_unit());
		teinv.setUnit_price(unit_price);
		teinv.setTotal(sub_tot+cgst+sgst+igst);
		
		Temp_Invoice tmpinv = tempinvrepo.save(teinv);
		if(tmpinv!=null) {
			return 1;
		}
		else {
			return 0;
		}
	}

}