package com.expense.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.expense.dao.ExpenseRepository;
import com.expense.dto.ExpenseDTO;
import com.expense.exception.ExpenseNotFoundException;
import com.expense.mapper.DtoMapper;
import com.expense.model.Expense;
import com.expense.service.ExpenseService;
import com.expense.utils.ExpenseSecurityContextHolder;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {

	private final ExpenseRepository expenseRepository;
	private final DtoMapper mapper;
	private final ExpenseSecurityContextHolder securityContextHolder;	
	
	@Transactional
	@Override
	public Long saveExpense(ExpenseDTO expenseDTO) {
		Expense expense = mapper.map(expenseDTO);
		expense.setUser(securityContextHolder.getCurrentLoggedInUser());
		expense = expenseRepository.save(expense);
		expenseDTO = mapper.map(expense);
		return expenseDTO.getExpenseId();
	}

	@Transactional
	@Override
	public ExpenseDTO findExpenseById(Long expenseId) {
		Expense expense = expenseRepository.findById(expenseId)
				.orElseThrow(() -> new ExpenseNotFoundException("Expense with given id not found"));
		return mapper.map(expense);
	}
	/**
	 * 
	 * TODO: Implement Pagination for this feature
	 * Problem: .. as data grows , this method has to handle large dataset
	 */
	@Transactional
	@Override
	public List<ExpenseDTO> findAllExpenses() {
		return expenseRepository.findAll().stream().map( mapper::map).toList();
	}

	@Transactional
	@Override
	public Long deleteExpenseById(Long expenseId) {
		ExpenseDTO expenseToDelete = findExpenseById(expenseId);
		Expense expModel = mapper.map(expenseToDelete);
		expenseRepository.delete(expModel);
		return expenseId;
	}

}
