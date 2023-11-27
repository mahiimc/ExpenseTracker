package com.expense.handler;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.expense.dto.SecuredUser;
import com.expense.model.User;

@Component
public class AuthContextHolder {
	
	public User getLoggedInUser() {
		SecuredUser secured =   (SecuredUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return secured.getUser();
	}
	
	public Long getLoggedInUserId() {
		User user = getLoggedInUser();
		return user.getUserId();
	}
	
	public String getLoggedInUserName() {
		User user = getLoggedInUser();
		return user.getUserName();
	}

}
