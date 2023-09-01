package com.expense.dao.impl;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Repository;

import com.expense.dao.UserDao;
import com.expense.model.User;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserDaoImpl implements UserDao {

	private final EntityManager em;
	
	
	@Override
	public Optional<User> findUserByUserNameOrEmail(String userName, String email) {
		Session session = em.unwrap(Session.class);
		SelectionQuery<User> query = session.createSelectionQuery("from User  where userName =:userName or profile.email =:email",User.class);
		query.setParameter("userName", userName);
		query.setParameter("email", email);
		return query.uniqueResultOptional();
	}
}
