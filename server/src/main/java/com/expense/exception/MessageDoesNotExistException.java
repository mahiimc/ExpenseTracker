package com.expense.exception;

public class MessageDoesNotExistException extends ExpenseException {

	private static final long serialVersionUID = 1L;

	public MessageDoesNotExistException(String message) {
		super(message);
	}

}
