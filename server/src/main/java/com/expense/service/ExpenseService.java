package com.expense.service;

import java.util.List;

import com.expense.dto.ChartData;
import com.expense.dto.ExpenseDTO;
import com.expense.exception.ExpenseNotFoundException;

public interface ExpenseService {
	Long saveExpense(ExpenseDTO expenseDTO);
	ExpenseDTO findExpenseById(Long expenseId) throws ExpenseNotFoundException;
	List<ExpenseDTO> findAllExpenses();
	Long deleteExpenseById(Long expenseId) throws ExpenseNotFoundException;
	List<ExpenseDTO> findExpenseByUserName();
	List<ChartData> getCategoryWiseDetails();
}
