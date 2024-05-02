package com.example.demo.jwtsecurity;

import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class GlobalConfiguration {

//	  @Bean
//	    public CorsFilter corsFilter() {
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        CorsConfiguration config = new CorsConfiguration();
//	        config.setAllowCredentials(true);
//	        config.addAllowedOrigin("http://localhost:4200");
//	        config.addAllowedHeader("*");
//	        config.addAllowedMethod("*");
//	        source.registerCorsConfiguration("/**", config);
//	        CorsFilter corsFilter = new CorsFilter();
//	        corsFilter.
//	        corsFilter.setOrder(Ordered.HIGHEST_PRECEDENCE);
//	        return corsFilter;
//	    }
//}

//@Configuration
public class GlobalConfiguration implements WebMvcConfigurer {

	
//	 @Override
//	    public void addCorsMappings(CorsRegistry registry) {
//	        registry.addMapping("/*")
//	                .allowedOrigins("http://localhost:4200") // Allow requests from Angular frontend
//	        		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//	                .allowedHeaders("Origin","Content-Type","Accept")
//	                .allowCredentials(true)
//	                .maxAge(3600);
//	    }
	
}
