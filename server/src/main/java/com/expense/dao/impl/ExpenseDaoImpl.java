package com.expense.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Repository;

import com.expense.dao.ExpenseDao;
import com.expense.model.Expense;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ExpenseDaoImpl implements ExpenseDao {

	private final EntityManager em;

	@Override
	public List<Expense> findExpenseByUserName(String userName) {
		Session session = em.unwrap(Session.class);
		SelectionQuery<Expense> query = session.createSelectionQuery("FROM Expense WHERE user.userName =:userName",Expense.class);
		query.setParameter("userName", userName);
		return query.list();
	}

}
