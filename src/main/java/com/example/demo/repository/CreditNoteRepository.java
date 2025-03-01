package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.CreditNote;

@Repository("crednoterepo")
public interface CreditNoteRepository extends JpaRepository<CreditNote, Integer> {

}
