package com.otmanel.blogSecu.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.otmanel.blogSecu.metier.User;

public interface IUserDDao {

	List<User> findAll();

	User findById(int id);

	User findByUserName(String username);

	User save(User u);

}