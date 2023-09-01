package com.expense.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.expense.filter.AuthenticationFilter;

@EnableWebSecurity
@Configuration
public class ExpenseSecurityConfig {
	
	@Bean
	public AuthenticationFilter authFilter() {
		return new AuthenticationFilter();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityChain(HttpSecurity http,AuthenticationEntryPoint entry,AuthenticationProvider authenticationProvider) throws Exception {
		
		http
			.csrf(csrf -> csrf.disable())
			.exceptionHandling(exception ->	exception.authenticationEntryPoint(entry))
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> 
					auth.requestMatchers("/api/v1/expense/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
					.requestMatchers("/api/v1/auth/**").permitAll()
					.anyRequest().authenticated()
				);
		http.authenticationProvider(authenticationProvider);
		http.addFilterBefore(authFilter(),UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
