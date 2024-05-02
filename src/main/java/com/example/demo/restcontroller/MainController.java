package com.example.demo.restcontroller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class MainController{
	
	@PostMapping("/basicauth")
	public String authenticate(Authentication auth) {
	
		return "You are authenticated";
	}
	
}