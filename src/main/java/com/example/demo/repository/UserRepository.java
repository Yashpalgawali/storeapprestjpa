package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Users;

@Repository("userrepo")
public interface UserRepository extends JpaRepository<Users, Integer> {

	@Query("UPDATE Users u SET u.password=:pass WHERE u.user_id=:id")
	@Transactional
	@Modifying
	public int updatePassword(Integer id,String pass);
	
	
	@Query("UPDATE Users u SET u.password=:pass WHERE u.username=:uname")
	@Transactional
	@Modifying
	public int updatePasswordByUserName(String uname,String pass);
	
}
 