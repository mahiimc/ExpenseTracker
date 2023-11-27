package com.expense.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.expense.dto.CategoryDTO;
import com.expense.dto.ExpenseDTO;
import com.expense.dto.UserDTO;
import com.expense.model.Category;
import com.expense.model.Expense;
import com.expense.model.User;

@Mapper(componentModel = "spring",
		unmappedSourcePolicy = ReportingPolicy.IGNORE, 
		unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface DtoMapper {
	
	// User mappers
	
	@Mapping(source = "firstName",target = "profile.firstName")
	@Mapping(source = "lastName",target = "profile.lastName")
	@Mapping(source = "email",target = "profile.email")
	@Mapping(source = "mobileNum",target = "profile.mobileNum")
	@Mapping(source = "profileUrl",target = "profile.url")
	User map(UserDTO dto);

	@Mapping(source = "profile.firstName",target = "firstName")
	@Mapping(source = "profile.lastName",target = "lastName")
	@Mapping(source = "profile.email",target = "email")
	@Mapping(source = "profile.mobileNum",target = "mobileNum")
	@Mapping(source = "profile.url",target = "profileUrl")
	UserDTO map(User user);
	
	
	// Expense Mappers
	Expense map(ExpenseDTO dto);
	@Mapping(source = "user.userId",target="userId")
	ExpenseDTO map(Expense model);
	
	// Category Mappers
	
	Category map(CategoryDTO dto);
	@Mapping(source="user.userId",target = "userId")
	CategoryDTO map(Category model);
}
