package com.expense.exception;

public class UserNotExistsException extends ExpenseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotExistsException(String message) {
		super(message);
	}

}
