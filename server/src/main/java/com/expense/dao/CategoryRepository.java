package com.expense.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expense.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
	
	@Query("FROM Category cat where cat.name =:name and cat.user.userId =:userId")
	Category findCategoryByNameAndUserId(String name, Long userId);
	@Query("FROM Category cat where cat.user.userId = :userId")
	List<Category> findAllCategoriesByUser(Long userId);

}
