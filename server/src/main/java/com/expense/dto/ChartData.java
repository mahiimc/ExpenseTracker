package com.expense.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ChartData implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private String category;
	private Double amount;

}
