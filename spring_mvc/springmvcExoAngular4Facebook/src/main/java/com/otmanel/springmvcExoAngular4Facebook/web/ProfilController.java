package com.otmanel.springmvcExoAngular4Facebook.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.otmanel.springmvcExoAngular4Facebook.metier.Profil;
import com.otmanel.springmvcExoAngular4Facebook.repositorie.IProfilDao;

@Controller
@RequestMapping("/api")
public class ProfilController {
	@Autowired
	private IProfilDao profilDao;
	
	@RequestMapping(value="/profils", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Profil> liste(){
		return this.profilDao.findAll();
	}
	@RequestMapping(value="/profils/{id:[0-9]+}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Profil findById(@PathVariable("id")int id) {
		return this.profilDao.findById(id);
	}
	
	@RequestMapping(value="/profils/{id:[0-9]+}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> delete(@PathVariable("id")int id) {
		this.profilDao.delete(id);
		Map<String, String> result = new HashMap<>();
		result.put("nbDeleted", "1");
		return result;
	}
	
	@RequestMapping(value="/profils", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Profil save(@RequestBody Profil p) {
		return this.profilDao.save(p);
	}
	
	@RequestMapping(value="/profils/searchByName/{searchTerm:.+}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Profil> searchByName(@PathVariable("searchTerm")String nom){
		return profilDao.searchByName(nom);
	}
	@RequestMapping(value="/profils/searchByCity/{searchTerm:.+}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Profil> searchByCity(@PathVariable("searchTerm")String city){
		return profilDao.searchByCity(city);
	}
}
