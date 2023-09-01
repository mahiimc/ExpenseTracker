package com.expense.exception;

public class ExpenseNotFoundException extends ExpenseException {
	
	
	private static final long serialVersionUID = 1L;

	public ExpenseNotFoundException(String message) {
		super(message);
	}

}
