package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Vendor;
import com.example.demo.repository.VendorRepo;

@SpringBootTest(classes = FinalstoreappApplication.class)
@Transactional
class VendorTest {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Long initialValue = null;
	
	@Autowired
	VendorRepo vendrepo;
	
    @Autowired
    JdbcTemplate jdbcTemplate; // To execute custom SQL queries
    
  

	@Test @DirtiesContext  
	void saveVendor() {
		// Long currentSequenceValue = jdbcTemplate.queryForObject("SELECT last_value FROM vendor_seq", Long.class);
		  
		Vendor vendor = new Vendor("xyz","email@gmail.com", 9874L , "Maharashtra", "Aurangabad", 431001L , "asdfawewer", "N-7 CIDCO");
		Vendor vend1 = vendrepo.save(vendor);
		logger.info("saved vendor is {} ",vend1);
		assertEquals("xyz", vend1.getVendor_name());
	}

	
	@BeforeEach
	void getInitialSequenceValue() {
		initialValue = jdbcTemplate.queryForObject("SELECT next_val from vendor_seq", Long.class);
		logger.info("Before Each is called and value is {} ",initialValue);
	}
	
	@Autowired
	EntityManager entityManager;
	
	@AfterEach @Modifying
	public void resetInitialValue() throws SQLException {
		
		 logger.info("After Each is called and value is {} ", initialValue);
 	     
		logger.info("After each is called {} ",initialValue);
		if(initialValue != null) {
			//jdbcTemplate.execute("alter sequence vendor_seq restart with "+initialValue);
			 jdbcTemplate.execute("UPDATE vendor_seq SET next_val = " + initialValue  );
			// jdbcTemplate.getDataSource().getConnection().commit();
			//jdbcTemplate.execute("update vendor_seq set next_val ="+initialValue);
		}
		logger.info("After each is called after updating tha value {} ",initialValue);
	}
	
}

