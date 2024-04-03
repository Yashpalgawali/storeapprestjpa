package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Invoice;

@Repository("invoicerepo")
public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {

	@Query(value="select MAX(invoice_id) from tbl_invoice", nativeQuery =true)
	public Integer getMaxInvoiceId();
	
	
	@Query(value="select * from tbl_invoice join tbl_invoice_product on tbl_invoice_product.order_id=tbl_invoice.order_id join tbl_product ON tbl_product.pid=tbl_invoice_product.prod_id join tbl_customer on tbl_customer.customer_id=tbl_invoice.customer_id group by tbl_invoice.invoice_no", nativeQuery = true)
	public List<Invoice> getAllInvoices();
	
	
	@Query(value="select * from tbl_invoice join tbl_temp_invoice ON tbl_temp_invoice.temp_invoice_id=tbl_invoice.order_id where order_id=?1", nativeQuery = true)
	public Invoice getInvoiceByOrderId(String id);
	
	
	@Query(value="select * from tbl_invoice where invoice_id=?1", nativeQuery = true)
	public Invoice getInvoiceByInvoiceId(String id);
	
}
