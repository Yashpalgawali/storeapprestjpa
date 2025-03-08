package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Vendor;
import com.example.demo.repository.VendorRepo;

@SpringBootTest(classes = FinalstoreappApplication.class)
@Transactional
class VendorTest {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	VendorRepo vendrepo;
	
	@Test @DirtiesContext @Rollback(true)
	void saveVendor() {
		//Long currentSequenceValue = jdbcTemplate.queryForObject("SELECT last_value FROM vendor_seq", Long.class);
		  
		Vendor vendor = new Vendor("xyz","email@gmail.com", 9874L , "Maharashtra", "Aurangabad", 431001L , "asdfawewer", "N-7 CIDCO");
		Vendor vend1 = vendrepo.save(vendor);
		logger.info("saved vendor is {} ",vend1);
		assertEquals("xyz", vend1.getVendor_name());
	}

}
