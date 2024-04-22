package com.example.demo.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController{
	
	@GetMapping("/basicauth")
	public String authenticate() {
	
		return "You are authenticated";
	}
	
}