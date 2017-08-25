package com.otmanel.blogSecu.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(exclude="users")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String rolename;
	@ManyToMany(mappedBy="roles")
	private Set<User> users;
	
	public Set<User> getUsers(){
		if (users == null ) users = new HashSet<>();
		return users;
	}

	public Role(int id, String rolename) {
		this.id = id;
		this.rolename = rolename;
	}
	
}
