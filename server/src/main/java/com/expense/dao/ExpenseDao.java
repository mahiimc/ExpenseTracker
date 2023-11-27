package com.expense.dao;

import java.util.List;

import com.expense.model.Expense;

public interface ExpenseDao {
	
	List<Expense> findExpenseByUserName(String userName);
}
