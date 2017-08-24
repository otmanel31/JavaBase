package com.otmanel.blogSecu.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.otmanel.blogSecu.metier.Role;
import com.otmanel.blogSecu.metier.User;
import com.otmanel.blogSecu.repositories.IRoleDao;
import com.otmanel.blogSecu.repositories.IUserDDao;

@Service
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private IUserDDao userDao;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private PasswordEncoder myPasswordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		List<User> users = userDao.findAll();
		List<Role> roles = roleDao.findAll();
		if (users.isEmpty() || roles.isEmpty()) {
			//la base est vide on linitialise
			Role r1 = new Role(0, "ROLE_USER");
			Role r2 = new Role(0, "ROLE_ADMIN");
			roleDao.save(r1);
			roleDao.save(r2);
			User u1 = new User(0, "otman", myPasswordEncoder.encode("meknes"), true);
			User u2 = new User(0, "toto", myPasswordEncoder.encode("toto"), true);
			u2.getRoles().add(r1);
			u1.getRoles().add(r1);
			u1.getRoles().add(r2);
			userDao.save(u1);
			userDao.save(u2);
		}
	}

}
