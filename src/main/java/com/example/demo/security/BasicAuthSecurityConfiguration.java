package com.example.demo.security;

import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class BasicAuthSecurityConfiguration {

//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeRequests(auth->{
//			auth.anyRequest().authenticated();
//			
//		});
//		http.httpBasic();
//		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
//		http.csrf().disable();
//		return http.build();
//	}
//	
//	public WebMvcConfigurer corsConfigurer()
//	{
//		return new WebMvcConfigurer() {
//			public void addCorsMappings(CorsRegistry  cors) {
//				
//			}
//		};
//	}
}
