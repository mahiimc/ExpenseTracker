package com.expense.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expense.dto.Response;
import com.expense.dto.UserDTO;
import com.expense.handler.AuthContextHolder;
import com.expense.mapper.DtoMapper;
import com.expense.model.User;
import com.expense.utils.ResponseBuilder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/user")
public class UserController {
	
	private final ResponseBuilder<UserDTO> userResponseBuilder;
	private final AuthContextHolder authContextHolder;
	private final DtoMapper mapper;
	
	@GetMapping("/")
	public ResponseEntity<Response<UserDTO>> findLoggedInUser() {
		User user = authContextHolder.getLoggedInUser();
		UserDTO userDto = mapper.map(user);
		return userResponseBuilder.build(userDto);
	}

}
