package com.expense.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.expense.dto.UserDTO;
import com.expense.exception.UserAlreadyExistsException;
import com.expense.exception.UserNotExistsException;
import com.expense.model.User;

public interface UserService extends UserDetailsService {
	
	Long saveUser(User user) throws UserAlreadyExistsException;
	UserDTO findUserById(Long userId) throws UserNotExistsException;
	UserDTO findUserByUserName(String userName);
}
