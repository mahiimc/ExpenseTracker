package com.expense.exception;

public class  ExpenseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ExpenseException(String message) {
		super(message);
	}

}
