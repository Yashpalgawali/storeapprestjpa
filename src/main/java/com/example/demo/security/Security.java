package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//@EnableWebSecurity
public class Security  extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private DataSource datasource;
//	
//	 @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		 auth.jdbcAuthentication()
//		.dataSource(datasource)
//		
//		//Following will select the username from database depending on the username from Login form
//		.usersByUsernameQuery("select username,password,enabled from tbl_users where username=? ")
//		
//		//Following will select the authority(s) depending on the username
//		.authoritiesByUsernameQuery("select username,role from tbl_users  where username=?")
//		
//		.passwordEncoder(new BCryptPasswordEncoder())
//		;
//
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//	
//		http
//		.csrf().disable()
//		.authorizeRequests()
//		
//		.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
//		.anyRequest()
//		.authenticated() 
//		.and()
//		
//		.sessionManagement()
//			.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//		
//		.and()
//		.logout()
//		.invalidateHttpSession(true)
//		.and()
//		.httpBasic();
// 
//		
//	}
//	
//	@Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//	
//	@Bean
//	public WebMvcConfigurer corsConfigurer()
//	{
//		return new WebMvcConfigurer() {
//			public void addCorsMapping(CorsRegistry reg)
//			{
//				reg
//					.addMapping("/*")
//					.allowedOrigins("*")
//					.allowedMethods("GET","POST","PATCH","PUT","DELETE")
//					.allowedHeaders("Origin","Content-Type","Accept")
//					.allowCredentials(true)
//				;
//			}
//		};
//	}
}