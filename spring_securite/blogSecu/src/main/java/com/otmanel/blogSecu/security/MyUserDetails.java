package com.otmanel.blogSecu.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.otmanel.blogSecu.metier.Role;
import com.otmanel.blogSecu.metier.User;

public class MyUserDetails implements UserDetails {

	private User user;
	public MyUserDetails(User u ) {
		this.user = u;
	}	

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnebled();
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> authorities = new ArrayList<>();
		for (Role r: user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(r.getRolename()));
		}
		return authorities;
		/*return user.getRoles().stream().map(r-> new SimpleGrantedAuthority(r.getRolename()))
				.collect(Collectors.toList());*/
	}

}
