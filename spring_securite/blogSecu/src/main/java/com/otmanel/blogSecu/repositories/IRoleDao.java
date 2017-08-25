package com.otmanel.blogSecu.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.otmanel.blogSecu.metier.Role;

public interface IRoleDao {

	List<Role> findAll();

	Role findById(int id);

	Role save(Role r);

}