package com.otmanel.blogSecu.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// veux dire que ce bean est un service destiné a etre injecté ds dautres beans
@Service
public class MyPasswordEncoder implements PasswordEncoder {
	
	private BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
	private static final String SERVERSALT = "toto";
	/*
	 * 
	 * cete fonction est appele pour crypter le mot de passe
	 */
	@Override
	public String encode(CharSequence rawPassword) {
		// je concatene le server salt + le mdp et je renvoie le hash sha256
		//return DigestUtils.sha256Hex(SERVERSALT+rawPassword); with sha ...
		return bcryptEncoder.encode(rawPassword);
		
	}

	/*
	 * 
	 * celle ci sera apeler pour comparer un mdp a sa ersion hasher
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// sha256
		//return DigestUtils.sha256Hex(SERVERSALT+rawPassword).equals(encodedPassword); 
		// WITH BCRYPT
		return bcryptEncoder.matches(rawPassword, encodedPassword);
	}

}
