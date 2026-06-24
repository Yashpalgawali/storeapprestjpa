package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Product;

@Repository("prodrepo")
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Modifying
//	@Query(value="update tbl_product set prod_name=?2,prod_price=?3,prod_unit=?4,prod_model_no=?5,prod_hsn=?6,gsttax=?7 where pid=?1", nativeQuery = true )
	@Query("UPDATE Product p set p.prod_name=:pname,p.prod_price=:price,p.prod_unit=:unit,p.prod_model_no=:model,p.prod_hsn=:hsn,p.gsttax=:gst WHERE p.pid=:pid")
	Integer updateProduct(Long pid, String pname, String price, String unit, String model, Long hsn, float gst);
	
	@Query("SELECT p FROM Product p WHERE p.prod_name=:name")
	public Optional<Product> findProductByName(String name);
}
