package com.expense.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CategoryDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String name;
	private String userId;

}
