package com.example.demo.restcontroller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class MainController{
	
	@PostMapping("/basicauth")
	public String authenticate(Authentication auth,HttpServletRequest request) {
	
		HttpSession sess = request.getSession();
		System.err.println("Inside basicauth() \n session ID is "+sess.getId()+"\n temp_id value in session is "+sess.getAttribute("temp_id"));
		//sess.removeAttribute("temp_id");
		return "You are authenticated";
	}
	
}