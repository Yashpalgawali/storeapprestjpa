package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.PoProductsList;

@Repository("poprodlistrepo")
public interface PoProductListRepository extends JpaRepository<PoProductsList, Integer> {

	@Transactional
	@Modifying
	@Query("UPDATE PoProductsList p SET p.prod_name=:pname,p.prod_model=:pmodel,p.prod_hsn=:phsn,p.prod_price=:price,p.prod_unit=:unit,p.cgst_per=:cgst,p.sgst_per=:sgst,p.igst_per=:igst WHERE p.prod_id=:pid")
	public int updatePoProductById(Integer pid,String pname,String pmodel,String phsn,Float price,String unit,int cgst,int sgst,int igst);
}
