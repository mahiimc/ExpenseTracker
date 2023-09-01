package com.expense.service;

import com.expense.model.Role;

public interface  RoleService {
	
	Integer saveRole(Role role);
	Role findRoleByName(String name);
	Role findRoleById(Integer roleId);

}
