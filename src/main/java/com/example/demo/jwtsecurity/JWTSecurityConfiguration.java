package com.example.demo.jwtsecurity;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class JWTSecurityConfiguration {
	
	@Autowired
	private DataSource dataSource;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.httpBasic();
		//http.cors();
		http.cors().configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Your Angular app's URL
            config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
            config.setAllowCredentials(true); // Allow cookies
            return config;
        });
		http.csrf().disable();
		http.authorizeRequests(auth->{
			auth.antMatchers(HttpMethod.OPTIONS,"/**").permitAll();
			auth.anyRequest().authenticated();
			
		});
		http.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
		http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
		http.headers().frameOptions().sameOrigin();
 
		
		http.logout(logout->{
		logout.logoutUrl("/logouturl");
		logout.invalidateHttpSession(true);
		logout.clearAuthentication(true);
		logout.deleteCookies("SESSION");
		logout.logoutSuccessHandler((request, response , authentication)->{
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write("{\" message\": \" Logged Out Successfully \" }");
			response.getWriter().flush();
		});
	});
	return http.build();
}
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
    	authBuilder.jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(new BCryptPasswordEncoder())
            .usersByUsernameQuery("select username, password, enabled from tbl_users where username=?")
            .authoritiesByUsernameQuery("select username, role from tbl_users where username=?")
            ;
    }
	
	@Bean
	public KeyPair keyPair() throws Exception {
		 var keyPairGen = KeyPairGenerator.getInstance("RSA");
		 keyPairGen.initialize(2048);
		 return keyPairGen.generateKeyPair();
	}
	
	@Bean
	public RSAKey rsaKey(KeyPair keyPair) {
		return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
						  .privateKey(keyPair.getPrivate())
						  .keyID(UUID.randomUUID()
						  .toString()).build();
	}
	
	@Bean
	public JWKSource<SecurityContext> jwkSource( RSAKey rsaKey) {
		var  jwkset = new JWKSet(rsaKey);
		
//		var jwksource = new JWKSource<SecurityContext>() {
//			@Override
//			public List<JWK> get(JWKSelector jwkSelector, SecurityContext context) throws KeySourceException {
//				// TODO Auto-generated method stub
//				return jwkSelector.select(jwkset);
//			}
//		};
		
		//By using Lambda function
		return (jwkSelector, context)-> jwkSelector.select(jwkset);
	}
	
	@Bean
	public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
		return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
	}
	
	@Bean
	public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
		return new NimbusJwtEncoder(jwkSource);
	}
	
//	@Bean
//	public DataSource dataSource() {
//		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
//				.addScript("org/springframework/security/core/userdetails/jdbc/users.ddl").build();
//	}
	 
	@Bean
	public BCryptPasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
} 
