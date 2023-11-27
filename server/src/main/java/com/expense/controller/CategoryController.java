package com.expense.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expense.dto.CategoryDTO;
import com.expense.dto.Response;
import com.expense.handler.AuthContextHolder;
import com.expense.mapper.DtoMapper;
import com.expense.model.Category;
import com.expense.service.CategoryService;
import com.expense.utils.ResponseBuilder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/category")
public class CategoryController {
	
	private final CategoryService categoryService;
	private final ResponseBuilder<CategoryDTO> categoryResponseBuilder;
	private final ResponseBuilder<List<CategoryDTO>> categoryListResponseBuilder;
	private final DtoMapper mapper;
	private final AuthContextHolder authContext;
	
	
	@PostMapping("/{category}")
	public ResponseEntity<Response<CategoryDTO>> saveCategory(@PathVariable("category") String category) {
		int id = categoryService.saveCategory(category);
		CategoryDTO dto = new CategoryDTO();
		dto.setId(id);
		dto.setName(category);
		dto.setUserId(String.valueOf(authContext.getLoggedInUserId()));
		return categoryResponseBuilder.build("Category saved successfully.",dto);
	}
	
	@GetMapping("/")
	public ResponseEntity<Response<List<CategoryDTO>>> findAllCategories() {
		List<Category> categories =  categoryService.findAllCategories();
		List<CategoryDTO> categoryList = categories.stream()
				.map(mapper::map)
				.toList();
		return categoryListResponseBuilder.build(categoryList);
	}

}
