package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Prefix;

@Repository("prefixrepo")
public interface PrefixRepository extends JpaRepository<Prefix, Integer> {

	@Query("UPDATE Prefix p SET p.fin_year=:fin_year WHERE p.setting_id=:id")
	@Transactional
	@Modifying
	public int updatePrefix(String fin_year, Integer id);
}
