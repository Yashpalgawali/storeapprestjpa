package com.example.demo.jwt;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationResource {

	private JwtEncoder jwtEncoder;
	
	public JwtAuthenticationResource(JwtEncoder jwtEncoder) {
		this.jwtEncoder=jwtEncoder;
	}
	
	@PostMapping("/authenticate")
	public JwtResponse authenticate(Authentication auth)
	{
		return new JwtResponse(createToken(auth));
	}

	private String createToken(Authentication auth) {
		var claims = JwtClaimsSet.builder().issuer("self")
							  .issuedAt(Instant.now())
							  .expiresAt(Instant.now().plusSeconds(60*30))
							  .subject(auth.getName())
							  .claim("scope", createScope(auth))
							  .build();
		
		
		JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);
		return jwtEncoder.encode(parameters).getTokenValue();
	}

	private String createScope(Authentication auth) {
		// TODO Auto-generated method stub
		return auth.getAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.joining(" "));
		
	}
}

record JwtResponse (String response) {}