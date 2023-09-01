package com.expense.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.expense.dto.SecuredUser;
import com.expense.exception.ExpenseException;
import com.expense.model.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ExpenseSecurityContextHolder {

	public User getCurrentLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			return ((SecuredUser) authentication.getPrincipal()).getUser();
		}
		throw new ExpenseException("Security Context is empty, Please Login again.");
	}

}
