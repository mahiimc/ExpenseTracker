package com.expense.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> , UserDao {
	
	User findUserByUserName(String userName);

}
 