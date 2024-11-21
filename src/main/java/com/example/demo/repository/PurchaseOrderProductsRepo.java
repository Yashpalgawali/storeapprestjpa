package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.PurchaseOrderProducts;

@Repository("po_prod_repo")
public interface PurchaseOrderProductsRepo extends JpaRepository<PurchaseOrderProducts, Integer> {

	
	@Query("SELECT p FROM PurchaseOrderProducts p WHERE p.temp_id=:temp_id")
	public List<PurchaseOrderProducts> getPurchaseOrderProductsByTempId(Integer temp_id);
	
	@Query("SELECT MAX(p.temp_id) FROM PurchaseOrderProducts p")
	public Integer getMaxTempId();
	
	@Query("DELETE FROM PurchaseOrderProducts p WHERE p.purchase_prod_order_id=:id")
	
	public void removeProductById(Integer id);
}
