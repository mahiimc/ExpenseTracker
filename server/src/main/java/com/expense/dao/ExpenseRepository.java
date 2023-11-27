package com.expense.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>  , ExpenseDao {

	

}
