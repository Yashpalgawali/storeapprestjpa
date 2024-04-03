package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Product;


@Repository("prodrepo")
public interface ProductRepository extends JpaRepository<Product , Long>{
	
	@Transactional
	@Modifying
	@Query(value="update tbl_product set prod_name=?2,prod_price=?3,prod_unit=?4,prod_model_no=?5,prod_hsn=?6,gsttax=?7 where pid=?1", nativeQuery = true )
	Integer updateProduct(Long pid,String pname,String price, String unit,String model,Long hsn ,float gst);
}
