package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.GstTaxRate;


@Repository("gsttaxrepo")
public interface GstTaxRateRepository extends JpaRepository<GstTaxRate, Integer> {

	@Query("UPDATE GstTaxRate g SET g.type=:type,g.taxrate=:taxrate WHERE g.gstid=:id")
	@Modifying
	public int updateGstTaxRate(Integer id,String type,Float taxrate );
}
