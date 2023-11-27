package com.expense.service;

import java.util.List;

import com.expense.model.Category;

public interface  CategoryService {
	
	int saveCategory(String category);
	Category findCategoryByName(String category);
	List<Category> findAllCategories();

}
