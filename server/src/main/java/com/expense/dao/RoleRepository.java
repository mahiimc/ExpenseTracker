package com.expense.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findRoleByName(String name);
	Role findRoleByRoleId(Integer roleId);

}
