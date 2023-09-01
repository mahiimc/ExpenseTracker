package com.expense.exception;

public class UserAlreadyExistsException extends ExpenseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public UserAlreadyExistsException(String message) {
		super(message);
	}


}
