package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.models.Invoice_Product;
import com.example.demo.models.Product;
import com.example.demo.models.Temp_Invoice;
import com.example.demo.repository.InvoiceProductRepo;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.TempInvoiceRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("tempinvserv")
@RequiredArgsConstructor
@Slf4j
public class TempInvoiceServImpl implements TempInvoiceService {

	private final TempInvoiceRepo tempinvrepo;

	private final ProductRepository prodrepo;

//	private final TempInvoiceService tempinserv;
	
	private final InvoiceProductRepo invprodrepo;
	private final ProductService prodserv;

	private Logger logger = LoggerFactory.getLogger(TempInvoiceServImpl.class);

	@Override
	public Temp_Invoice saveTempInvoice(Temp_Invoice tin,HttpServletRequest request) {
		logger.error("Temp Invoice is {} ", tin);
		
		System.err.println("TEMP INVOICE is "+tin.toString());
		
		HttpSession sess = request.getSession();
		Integer sessid = (Integer) sess.getAttribute("temp_id");
		System.err.println(
				"Inside saveTempInvoice() session ID is " + sess.getId() + "\n temp_id in the session is " + sessid);
 
		Integer chk_tmp_id = 0;
		if (sessid == null) {
			chk_tmp_id = tempinvrepo.getMaxTempInvoiceNum();
					
			if (chk_tmp_id == null) {
				System.err.println("MAX temp_id is  NULL \n");
				chk_tmp_id = 1;

			} else {
				System.err.println("MAX temp_id is = " + chk_tmp_id);
				chk_tmp_id = chk_tmp_id + 1;
			}
			sess.setAttribute("temp_id", chk_tmp_id);
			sessid = chk_tmp_id;
		}

		System.err.println("inside saveTempInvoice() sessid = " + sessid);

		Long prod_id = tin.getProduct().getPid();
//		Long p_hsn = tin.getProduct().getProd_hsn();
//		Integer p_qty = tin.getQty();
//		Float p_cust_price = tin.getUnit_price();
		Float unit_price = 0.0f;

		float sub_tot, cgst, sgst, igst, total;

		Product tem = prodserv.getProductById(prod_id);
		if ( tin.getCustom_price() > 0) {
			 tin.setCustom_price(tin.getCustom_price() * 1.18f);
			unit_price = (float) (tin.getCustom_price() / 1.18);
			System.err.println("Unit Price after 18% tax is "+ unit_price * 1.18);
		} else {
			unit_price = (float) (Float.parseFloat(tem.getProd_price()) / (1.18));
		}

		sub_tot = unit_price * tin.getQty();
System.err.println("subtotal is "+sub_tot);
		if (tin.getStoption().equals("mh")) {
			tin.setCgst_per(tem.getCgst_per());
			tin.setSgst_per(tem.getSgst_per());
			tin.setIgst(0);

			cgst = Math.round((sub_tot / 100) * tem.getCgst_per());
			sgst = Math.round((sub_tot / 100) * tem.getSgst_per());
			igst = 0.0f;
		} else {
			tin.setIgst_per(tem.getIgst_per());
			tin.setCgst_per(0);
			tin.setSgst_per(0);

			cgst = 0.0f;
			sgst = cgst;
			igst = Math.round((sub_tot / 100) * tem.getIgst_per());
		}

		tin.setTemp_invoice_id(sessid);
		tin.setCgst(cgst);
		tin.setSgst(sgst);
		tin.setIgst(igst);

		Long phsn = tem.getProd_hsn();
		String nhsn = String.valueOf(phsn);
		tin.setHsn(nhsn);
		tin.setUnit(tem.getProd_unit());
		tin.setUnit_price(unit_price);
		tin.setTotal(sub_tot + cgst + sgst + igst);
		
		
		Temp_Invoice save = tempinvrepo.save(tin);
		if(save!=null ) {
			Invoice_Product ivprod =new Invoice_Product();
			ivprod.setCgst(cgst);
			ivprod.setSgst(sgst);
			ivprod.setIgst(igst);
			ivprod.setCgst_per(tin.getCgst_per());
			ivprod.setSgst_per(tin.getSgst_per());
			ivprod.setIgst(tin.getIgst_per());
			ivprod.setOrder_id(sessid);
			ivprod.setQty(tin.getQty());
			ivprod.setTotal(tin.getTotal());
			ivprod.setPrice(tin.getUnit_price());
			ivprod.setSubtotal(sub_tot);
			ivprod.setProduct(tem);
			
			invprodrepo.save(ivprod);
			
			return save;
		}
		return null;
	}

	@Override
	public List<Temp_Invoice> getTempInvById(Integer tid) {
		return tempinvrepo.getTempInvById(tid);
	}

	@Override
	public Integer getMaxTempInvoiceId() {
		return tempinvrepo.getMaxTempInvoiceNum();
	}

	@Override
	public boolean deleteSelectedTempInvoice(String temp_id) {
		Integer tid = Integer.parseInt(temp_id);

		if (tempinvrepo.existsById(tid)) {
			tempinvrepo.deleteById(tid);

//			Activities act = new Activities("Product with ID "+temp_id+" deleted from table ", LocalDate.now().format(Global.DATE_FORMATTER),  LocalDate.now().format(Global.TIME_FORMATTER));
//			actrepo.save(act);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Temp_Invoice> getTempInvByTempInvoiceId(Integer tid) {

		return tempinvrepo.getTempInvByTempInvoiceId(tid);
	}

	@Override
	public int updateTempInvoice(Temp_Invoice tin, HttpServletRequest request) {

		Long prod_id = tin.getProduct().getPid();
		Long p_hsn = tin.getProduct().getProd_hsn();
		Integer p_qty = tin.getQty();
		Float p_cust_price = tin.getCustom_price();
		Float unit_price = 0.0f;

		float sub_tot, cgst, sgst, igst, total;

		Product tem = prodrepo.findById(prod_id).get();
		if (p_cust_price > 0) {
			unit_price = (float) (p_cust_price / 1.18);
		} else {
			unit_price = (float) (Float.parseFloat(tem.getProd_price()) / (1.18));
		}

		sub_tot = unit_price * tin.getQty();

		if (tin.getStoption().equals("mh")) {
			tin.setCgst_per(tem.getCgst_per());
			tin.setSgst_per(tem.getSgst_per());
			tin.setIgst(0);

			cgst = Math.round((sub_tot / 100) * tem.getCgst_per());
			sgst = Math.round((sub_tot / 100) * tem.getSgst_per());
			igst = Math.round((sub_tot / 100) * tin.getIgst_per());
		} else {
			tin.setIgst_per(tem.getIgst_per());
			tin.setCgst_per(0);
			tin.setSgst_per(0);

			cgst = Math.round((sub_tot / 100) * tin.getCgst_per());
			sgst = Math.round((sub_tot / 100) * tin.getSgst_per());
			igst = Math.round((sub_tot / 100) * tem.getIgst_per());
		}

		tin.setTemp_invoice_id(tin.getTemp_id());
		tin.setCgst(cgst);
		tin.setSgst(sgst);
		tin.setIgst(igst);

		Long phsn = tem.getProd_hsn();
		String nhsn = String.valueOf(phsn);
		tin.setHsn(nhsn);
		tin.setUnit(tem.getProd_unit());
		tin.setUnit_price(unit_price);
		tin.setTotal(sub_tot + cgst + sgst + igst);

		Temp_Invoice tmpinv = tempinvrepo.save(tin);
		if (tmpinv != null) {
			return 1;
		} else {
			return 0;
		}
	}

}