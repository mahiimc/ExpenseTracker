package com.expense.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.expense.dao.UserRepository;
import com.expense.dto.SecuredUser;
import com.expense.dto.UserDTO;
import com.expense.exception.UserAlreadyExistsException;
import com.expense.exception.UserNotExistsException;
import com.expense.mapper.DtoMapper;
import com.expense.model.Role;
import com.expense.model.User;
import com.expense.service.RoleService;
import com.expense.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final DtoMapper dtoMapper;
	private final PasswordEncoder passwordEncoder;
	private final RoleService roleService;
	
	@Transactional
	@Override
	public Long saveUser(User user) throws UserAlreadyExistsException {
		
		Optional<User> dbUser = userRepository.findUserByUserNameOrEmail(user.getUserName(),user.getProfile().getEmail());
		if(dbUser.isPresent()) {
			throw new UserAlreadyExistsException("User already exits!");
		}
		user.getProfile().setUser(user);
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		Role role = roleService.findRoleByName("ROLE_USER");
		user.setRoles(List.of(role));
		User createdUser = userRepository.save(user);
		return createdUser.getUserId(); 
	}
	
	@Transactional
	@Override
	public UserDTO findUserById(Long userId) {
		User user =   userRepository.findById(userId).orElseThrow(() -> new UserNotExistsException("User with the given id not present."));
		return dtoMapper.map(user);
	}
	

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =  userRepository.findUserByUserNameOrEmail(username, username).orElseThrow(()-> new UserNotExistsException("User not found."));
		return new SecuredUser(user);
	}

}
