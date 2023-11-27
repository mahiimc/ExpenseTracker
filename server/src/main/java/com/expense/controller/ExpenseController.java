package com.expense.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.dto.ChartData;
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
	private final ResponseBuilder<List<ExpenseDTO>> expenseListResponseBuilder;
	private final ResponseBuilder<List<ChartData>> chartDataBuilder ;
	private final ExpenseService expenseService;
	private final MessageProvider ms;
	
	@PostMapping("/")
	public ResponseEntity<Response<ExpenseDTO>> saveExpense(@Valid @RequestBody ExpenseDTO dto) {
		Long id = expenseService.saveExpense(dto);
		dto.setExpenseId(id);
		return expenseResponseBuilder.build(ms.getMessage("expense.save.success"),dto);
	}
	
	@GetMapping("/")
	public ResponseEntity<Response<List<ExpenseDTO>>> findAllExpensesByUserId() {
		List<ExpenseDTO> expenses =  expenseService.findExpenseByUserName();
		return expenseListResponseBuilder.build(ms.getMessage("expense.fetch.success"),expenses);
		
	}
	
	@GetMapping("/chart")
	public ResponseEntity<Response<List<ChartData>>> getCategoryWiseDetails() {
		List<ChartData> chartData =  expenseService.getCategoryWiseDetails();
		return chartDataBuilder.build("Data Fetched Successfully.",chartData); 
	}
	
	
}