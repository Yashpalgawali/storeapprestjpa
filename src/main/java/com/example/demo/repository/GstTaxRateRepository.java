package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.GstTaxRate;


@Repository("gsttaxrepo")
public interface GstTaxRateRepository extends JpaRepository<GstTaxRate, Integer> {

}
