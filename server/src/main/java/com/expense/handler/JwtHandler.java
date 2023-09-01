package com.expense.handler;

import java.util.Base64;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.expense.config.ApplicationProperties;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtHandler {
	
	// TODO: Improve this code.
	
	private static final ApplicationProperties props = ApplicationProperties.getInstance();
	
	public  String generateToken(Authentication authentication) {
		
		Date date = new Date();
		return Jwts.builder().setIssuedAt(date)
				.setExpiration(new Date(date.getTime() + Long.valueOf(props.getTokenExpiration())))
				.setIssuer("Expense Tracker Application")
				.setSubject(authentication.getPrincipal().toString())
				.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(props.getTokenSecret().getBytes()))
				.compact();
	}
	
	public  Boolean validateToken(String authToken) {

		try {
			Jwts.parserBuilder()
				.setSigningKey(Base64.getEncoder().encode(props.getTokenSecret().getBytes()))
				.build()
				.parseClaimsJws(authToken);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public  String getUserNameFromToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(Base64.getEncoder().encode(props.getTokenSecret().getBytes()))
				.build().parseClaimsJws(token)
				.getBody().getSubject();
	}
}
