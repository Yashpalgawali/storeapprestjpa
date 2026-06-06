package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.CreditNoteProduct;

@Repository("creditnoteprodrepo")
public interface CreditNoteProductRepository extends JpaRepository<CreditNoteProduct, Integer> {

	@Query("SELECT MAX(c.order_id) FROM CreditNoteProduct c")
	 public Integer getMaxTempCreditNoteNumber();
	
	@Query("SELECT MAX(c.credit_note_id) FROM CreditNoteProduct c")
	 public Integer getMaxCreditNoteId();
}
