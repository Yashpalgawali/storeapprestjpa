package com.example.demo.jwt;

import java.time.Instant;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
@CrossOrigin(origins = "*") // Add this line
@SessionAttributes("session_id") // Indicate which model attributes should be stored in the session
public class JwtAuthenticationResource {

	@Autowired
	HttpSession session;
	
	private JwtEncoder jwtEncoder;
	
	public JwtAuthenticationResource(JwtEncoder jwtEncoder) {
		this.jwtEncoder=jwtEncoder;
	}
 
	
	@GetMapping("/authenticate")
	public JwtToken authenticate(HttpServletRequest request, Model model) {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        session = request.getSession();
	        String sessionId = session.getId();
	        model.addAttribute("session_id", sessionId);
	        session.setAttribute("session_id", sessionId); // Store session ID in HttpSession

	        System.err.println("Session ID set: " + sessionId); // Debugging output
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		session = request.getSession();
//		session.setAttribute("session_id", session.getId());
//		String sessionId = session.getId();
//	    model.addAttribute("session_id", sessionId); // Store session ID as a model attribute
//	        
//		System.err.println("Session ID in auth Controller === "+session.getAttribute("session_id"));
//		
		return new JwtToken(createToken(authentication));
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
		return auth.getAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.joining(" "));
	}
}

record JwtToken (String token) {}