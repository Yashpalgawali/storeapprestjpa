package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Invoice_Product;


@Repository("invprodrepo")
public interface InvoiceProductRepo extends JpaRepository<Invoice_Product, Integer> {
	
//	@Query(value="select * from tbl_invoice_product "
//			+ "	JOIN tbl_invoice ON tbl_invoice.order_id=tbl_invoice_product.order_id "
//			+ " WHERE tbl_invoice_product.order_id=:id",nativeQuery =  true)
	@Query("SELECT ip FROM Invoice_Product ip WHERE ip.order_id=:id")
	List<Invoice_Product> findInvoiceProductsByOrderId(String id);
}
