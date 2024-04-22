package com.example.demo.jwtsecurity;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class JWTSecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth->{
			auth.anyRequest().authenticated();
		});
		http.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
		http.httpBasic();
		
		http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
		
		
		http.headers().frameOptions().sameOrigin();
		http.csrf().disable();
		
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailService(DataSource datasource) {
		var admin = User.withUsername("admin")
				.password("admin").passwordEncoder(str->passEncoder().encode(str))
				.roles("ADMIN","USER")
				.build()
				;
		
		var user = User.withUsername("user")
				.password("user").passwordEncoder(str->passEncoder().encode(str))
				.roles("USER")
				.build()
				;
		var jdbcuserdetails = new JdbcUserDetailsManager(datasource);
		jdbcuserdetails.createUser(user);
		jdbcuserdetails.createUser(admin);
		
		return jdbcuserdetails;
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
		var  jwkset =new JWKSet(rsaKey);
		
//		var jwksource = new JWKSource<SecurityContext>() {
//
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
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript("org/springframework/security/core/userdetails/jdbc/users.ddl").build();
	}
	
	@Bean
	public BCryptPasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder(); 
	}
} 
