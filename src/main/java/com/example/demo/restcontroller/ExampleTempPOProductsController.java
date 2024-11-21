package com.example.demo.restcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ExamplePOTempProducts;

@RestController
@RequestMapping("example_temp_prod")
public class ExampleTempPOProductsController {

	
	@PostMapping("/")
	public void saveTempPoProduct(@RequestBody ExamplePOTempProducts poprod,HttpServletRequest request) {
		HttpSession sess = request.getSession();
		
		System.err.println(poprod.toString()+"\n"+sess.getId());
		
	}
	
}

