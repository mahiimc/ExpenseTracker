package com.expense.dao;

import java.util.Optional;

import com.expense.model.User;

public interface  UserDao {
	Optional<User> findUserByUserNameOrEmail(String userName , String email);
}
