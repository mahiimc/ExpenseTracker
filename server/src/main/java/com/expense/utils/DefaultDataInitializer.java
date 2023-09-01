package com.expense.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.expense.model.Role;
import com.expense.service.RoleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DefaultDataInitializer implements CommandLineRunner {

	private final RoleService roleService;

	@Override
	public void run(String... args) throws Exception {
		Role userRole = new Role("ROLE_USER");
		Role adminRole = new Role("ROLE_ADMIN");
		roleService.saveRole(userRole);
		roleService.saveRole(adminRole);
	}

}