package com.expense.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ExpenseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long expenseId;
	private String category;
	private String description;
	private Date date;
	private Long userId;
	private Double amount;
}
