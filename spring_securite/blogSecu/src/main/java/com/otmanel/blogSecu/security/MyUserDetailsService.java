package com.otmanel.blogSecu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.otmanel.blogSecu.metier.User;
import com.otmanel.blogSecu.repositories.IUserDDao;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserDDao userDDao;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		try {
			User u = userDDao.findByUserName(login);
			return new MyUserDetails(u);
		}catch (Exception e) {
			throw new UsernameNotFoundException("bad credential");
		}
		
	}

}
