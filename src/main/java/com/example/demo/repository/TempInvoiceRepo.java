package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Temp_Invoice;


@Repository("tempinvrepo")
public interface TempInvoiceRepo extends JpaRepository<Temp_Invoice, Integer> {

	@Query(value="select * from tbl_temp_invoice as tin join tbl_product as tp on tp.pid=tin.prod_id where tin.temp_invoice_id=?1", nativeQuery = true)
	List<Temp_Invoice> getTempInvById(Integer tid);
	
	@Query(value="select max(temp_invoice_id) from tbl_temp_invoice",nativeQuery = true)
	Integer getMaxTempInvoiceNum();
	
//	@Query(value="delete * from tbl_temp_invoice where temp_id=?1" , nativeQuery = true)
//	Integer deleteTempInvoiceByTempInvId(Integer tid);

}