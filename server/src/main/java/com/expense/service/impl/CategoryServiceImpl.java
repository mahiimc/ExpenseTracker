package com.expense.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.expense.dao.CategoryRepository;
import com.expense.exception.ExpenseException;
import com.expense.handler.AuthContextHolder;
import com.expense.model.Category;
import com.expense.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository catRepository;
	private final AuthContextHolder authContext;

	@Override
	public int saveCategory(String category) {
		Category cat = findCategoryByName(category);
		if(cat != null ) {
			throw new ExpenseException("Category already exists.");
		}
		cat = new Category();
		cat.setName(category);
		cat.setUser(authContext.getLoggedInUser());
		cat = catRepository.save(cat);
		return cat.getId();
	}

	@Override
	public Category findCategoryByName(String category) {
		return  catRepository.findCategoryByNameAndUserId(category,authContext.getLoggedInUserId());
	}

	@Override
	public List<Category> findAllCategories() {
		return catRepository.findAllCategoriesByUser(authContext.getLoggedInUserId());
	}

}
