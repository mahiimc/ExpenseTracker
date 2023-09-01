package com.expense.service.impl;

import org.springframework.stereotype.Service;

import com.expense.dao.RoleRepository;
import com.expense.exception.ExpenseException;
import com.expense.model.Role;
import com.expense.service.RoleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService  {

	private final RoleRepository roleRepository;
	
	@Override
	public Integer saveRole(Role role) {
		Role dbRole = findRoleByName(role.getName());
		if(null == dbRole) {
			dbRole = roleRepository.save(role);
		}
		return dbRole.getRoleId();
	}

	@Override
	public Role findRoleByName(String name) {
		return roleRepository.findRoleByName(name);
	}

	@Override
	public Role findRoleById(Integer roleId) {
		return null;
	}

}
