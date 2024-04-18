package com.example.demo.restcontroller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.AuthenticationBean;

@RestController
@CrossOrigin("*")

public class MainController {

	
	@GetMapping("/basicauth")
	public AuthenticationBean authenticationBean() { 
		System.err.println( new BCryptPasswordEncoder().encode("samyak"));
		return new AuthenticationBean("You are authenticated"); } 
}
