package com.expense.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.expense.dao.ExpenseRepository;
import com.expense.dto.ChartData;
import com.expense.dto.ExpenseDTO;
import com.expense.exception.ExpenseNotFoundException;
import com.expense.handler.AuthContextHolder;
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
	private final AuthContextHolder authContextHolder;
	
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

	@Transactional
	@Override
	public List<ExpenseDTO> findExpenseByUserName() {
		String userName = authContextHolder.getLoggedInUserName();
		List<Expense> expenes =  expenseRepository.findExpenseByUserName(userName);
		return  expenes.stream().map(mapper::map).toList();
	}

	@Override
	public List<ChartData> getCategoryWiseDetails() {
		List<ExpenseDTO> expenseList =  findExpenseByUserName();
		Map<String,Double> chartData = new HashMap<>();
		for(ExpenseDTO expense : expenseList ) {
			String category = expense.getCategory();
			if(chartData.containsKey(category)) {
				Double prevAmount = chartData.get(category);
				chartData.put(category, prevAmount + expense.getAmount());
			}
			else {
				chartData.put(category, expense.getAmount());
			}
		}
		List<ChartData> data = new ArrayList<>();
		
		int sNo = 1;
		for(Map.Entry<String, Double> entry : chartData.entrySet()) {
			ChartData chart = new ChartData();
			chart.setId(sNo);
			chart.setCategory(entry.getKey());
			chart.setAmount(entry.getValue());
			data.add(chart);
			sNo++;
		}
		
		return data;
	}

}
