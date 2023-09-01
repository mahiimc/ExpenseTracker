package com.expense.filter;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.expense.dto.SecuredUser;
import com.expense.handler.JwtHandler;
import com.expense.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtHandler jwtHandler;
	
	
	@Autowired
	private  UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			
		String jwt = parse(request);
		
		if ((jwt != null && jwtHandler.validateToken(jwt))) {
			String userName = jwtHandler.getUserNameFromToken(jwt);
			SecuredUser userDetails = (SecuredUser) userService.loadUserByUsername(userName);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} else {
			log.warn("Could not find header, Ignoring Authentication for this endpoint: {}", request.getRequestURI());
		}
		filterChain.doFilter(request, response);
	}
	
	private String parse(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if(StringUtils.isNotBlank(header) && header.startsWith("Bearer ")) {
			return header.substring(7);
		}
		log.warn("no Authorization Header found for url : {}", request.getRequestURI());
		return null;
	}


}
