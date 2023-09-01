package com.expense.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.expense.dto.SecuredUser;
import com.expense.exception.ExpenseException;
import com.expense.exception.InvalidCredentilsException;
import com.expense.service.UserService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ExpenseAuthenticationProvider implements AuthenticationProvider {
	
	private final PasswordEncoder passwordEncoder;
	private final UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		SecuredUser user =  (SecuredUser) userService.loadUserByUsername(userName);
		if(passwordEncoder.matches(password, user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),user.getAuthorities());
		}
		else {
			throw new InvalidCredentilsException("Invalid credentils provided.");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
