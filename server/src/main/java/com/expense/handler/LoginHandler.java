package com.expense.handler;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.expense.dto.LoginDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LoginHandler  {

	private final AuthenticationManager authManager;
	private final JwtHandler jwtHandler;
	
	public String handle(LoginDTO dto) {
		
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return jwtHandler.generateToken(authentication);
	}

}
