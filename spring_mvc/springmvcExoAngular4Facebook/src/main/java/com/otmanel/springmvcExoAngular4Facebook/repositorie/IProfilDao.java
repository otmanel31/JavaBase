package com.otmanel.springmvcExoAngular4Facebook.repositorie;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.otmanel.springmvcExoAngular4Facebook.metier.Profil;

public interface IProfilDao {

	List<Profil> findAll();

	Profil findById(int id);

	void delete(int pid);

	Profil save(Profil p);
	List<Profil> searchByName(String nom);
	List<Profil> searchByCity(String city);

}