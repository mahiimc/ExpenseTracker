package com.expense.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.dto.JWTToken;
import com.expense.dto.LoginDTO;
import com.expense.dto.Response;
import com.expense.dto.UserDTO;
import com.expense.handler.LoginHandler;
import com.expense.mapper.DtoMapper;
import com.expense.model.User;
import com.expense.service.UserService;
import com.expense.utils.MessageProvider;
import com.expense.utils.ResponseBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;
	private final DtoMapper mapper;
	private final ResponseBuilder<UserDTO> userResponseBuilder;
	private final ResponseBuilder<JWTToken> jwtResponseBuilder;
	private final MessageProvider ms;
	private final LoginHandler loginHandler;
	
	@PostMapping("/register")
	public ResponseEntity<Response<UserDTO>> saveUser(@Valid @RequestBody UserDTO user) {
		User userModel = mapper.map(user);
		userService.saveUser(userModel);
		user = mapper.map(userModel);
		return userResponseBuilder.build(ms.getMessage("user.save.success"), user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response<JWTToken>> login(@Valid @RequestBody LoginDTO dto) {
		String token = loginHandler.handle(dto);
		return jwtResponseBuilder.build(new JWTToken(token,ms.getMessage("user.login.success")));
	}

}
