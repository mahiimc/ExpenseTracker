package com.expense.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.dto.ExpenseDTO;
import com.expense.dto.Response;
import com.expense.service.ExpenseService;
import com.expense.utils.MessageProvider;
import com.expense.utils.ResponseBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/expense")
public class ExpenseController {

	private final ResponseBuilder<ExpenseDTO> expenseResponseBuilder;
	private final ExpenseService expenseService;
	private final MessageProvider ms;
	
	@PostMapping("/")
	public ResponseEntity<Response<ExpenseDTO>> saveExpense(@Valid @RequestBody ExpenseDTO dto) {
		expenseService.saveExpense(dto);
		return expenseResponseBuilder.build(ms.getMessage("expense.save.success"),dto);
	}
	
	
}