package com.otmanel.exo_struts_jpa_spring.actions;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.otmanel.exo_struts_jpa_spring.metier.Acteur;
import com.otmanel.exo_struts_jpa_spring.metier.Film;
import com.otmanel.exo_struts_jpa_spring.metier.Realisateur;
import com.otmanel.exo_struts_jpa_spring.repositories.IActeurDao;
import com.otmanel.exo_struts_jpa_spring.repositories.IFilmDao;
import com.otmanel.exo_struts_jpa_spring.repositories.IRealisateurDao;

public class FilmAction extends ActionSupport implements ModelDriven<Film>{
	private Film model;
	private List<Film> films;
	private List<Realisateur> realisateurs;
	private List<Acteur> acteurs2;
	private List<Acteur> acteursNotInFilm;
	
	public List<Acteur> getActeurs2() {return acteurs2;}
	public void setActeurs2(List<Acteur> acteursInFilm) {this.acteurs2 = acteursInFilm;}
	public List<Acteur> getActeursNotInFilm() {return acteursNotInFilm;}
	public void setActeursNotInFilm(List<Acteur> acteursNotInFilm) {this.acteursNotInFilm = acteursNotInFilm;}
	public List<Realisateur> getRealisateurs() {return realisateurs;}
	public void setRealisateurs(List<Realisateur> realisateurs) {this.realisateurs = realisateurs;}
	
	@Override
	public Film getModel() {
		if (this.model == null) model = new Film();
		return this.model;
	}
	
	// injection dao
	private IFilmDao filmDao;
	public IFilmDao getFilmDao() {return filmDao;}
	public void setFilmDao(IFilmDao filmDao) {this.filmDao = filmDao;}
	private IRealisateurDao realisateurDao;
	public IRealisateurDao getRealisateurDao() {return realisateurDao;}
	public void setRealisateurDao(IRealisateurDao realisateurDao) {this.realisateurDao = realisateurDao;}
	private IActeurDao acteurDao;
	public IActeurDao getActeurDao() {return acteurDao;}
	public void setActeurDao(IActeurDao acteurDao) {this.acteurDao = acteurDao;}
	// liste de films
	public List<Film> getFilms(){
		return this.films;
	}
	
	public String liste() {
		films = filmDao.findAll();
		return SUCCESS;
	}
	public String edit(){
		Film toEdit  = filmDao.findById(getModel().getId());
		if (toEdit == null) return ERROR;
		this.model.setTitre(toEdit.getTitre());
		this.model.setSynopsis(toEdit.getSynopsis());
		this.model.setAnnee(toEdit.getAnnee());
		this.model.setRealisateur(toEdit.getRealisateur());
		this.model.setActeurs(toEdit.getActeurs());
		this.model.setRealisateurId(toEdit.getRealisateur().getId());
		this.realisateurs = realisateurDao.findAll();
		System.out.println("mon id " +getModel().getId());
		this.acteursNotInFilm = acteurDao.findAllNotInFilm(getModel().getId());
		return SUCCESS;
	}
	public String save() {
		Film filmOld = filmDao.findById(getModel().getId());
		this.model.setRealisateur(realisateurDao.findById(this.model.getRealisateurId()));
		if( filmOld != null) 
			this.model.setActeurs(filmOld.getActeurs());
		filmDao.save(getModel());
		return SUCCESS;
	}
}
