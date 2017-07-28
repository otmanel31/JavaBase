package com.otmanel.videoTech.actions;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;

import com.opensymphony.xwork2.ActionSupport;
import com.otmanel.videoTech.metiers.Film;
import com.otmanel.videoTech.utils.GeneriqueDao;

// implementation in=terface pour ecupere le servlet context
public class FilmAction extends ActionSupport implements ApplicationAware{
	private GeneriqueDao<Film> filmDao;
	
	@Override
	public void setApplication(Map<String, Object> ctx) {
		this.filmDao = (GeneriqueDao<Film>)ctx.get("filmDAO");
	}
	
	// je recupere un loggeer pour ma classe
	Logger log = LogManager.getLogger(FilmAction.class);
	
	private String title;
	private List<Film> films;
	private int id;
	// debut champ edition
	private int editId;
	private String editTitre;
	private String editRealisateur;
	private int editAnnee;
	private int editRating;
	private String editDescription;
	
	public int getEditId() {return editId;}
	public void setEditId(int editId) {this.editId = editId;}
	public String getEditTitre() {return editTitre;}
	public void setEditTitre(String editTitre) {this.editTitre = editTitre;}
	public String getEditRealisateur() {return editRealisateur;}
	public void setEditRealisateur(String editRealisateur) {this.editRealisateur = editRealisateur;}
	public int getEditAnnee() {return editAnnee;}
	public void setEditAnnee(int editAnnee) {this.editAnnee = editAnnee;}
	public int getEditRating() {return editRating;}
	public void setEditRating(int editRating) {this.editRating = editRating;}
	public String getEditDescription() {return editDescription;}
	public void setEditDescription(String editDescription) {this.editDescription = editDescription;}
	// fin champ edition
	
	public String getTitle() { log.info("appel de getTitle"); return title;}
	public void setTitle(String title) {log.info("appel de setTitle"); this.title = title;}
	public void setId(int id) {log.info("appel de setId");this.id = id;}
	public List<Film> getFilms() {
		log.info("appel de getFilms");//return films;
		if (films == null)
			films = filmDao.findAll();
		return films;
	}

	public FilmAction() {
		log.info("Construction de filmAction");
		/*
		 * PLus necessaire depuis implementation du daoBuilder
		 * films = Arrays.asList(new Film(1, "Valerian", 2017, "film pourri", "Luc Besson", 2),
				new Film(2, "Kung fury", 2017, "film parodique", "David sSnaberg", 4)
			);
		 */
		
	}
	
	/*
	 * Lister les films => repondrat a l url /films
	 */
	public String listFilms() {
		log.info("appel de listFilm");
		title = "la video du futur en " + LocalDateTime.now();
		
		
		return SUCCESS;
	}
	
	public String editerFilm() {
		log.info("passage ds editFilm; id => " + this.id);
		// optional car il peut ne pas en trouver
		// un optionall contient l objet indiqué ou indique sil ne contient pas avc isPresent
		// pas besoins de la liste des film desormais avc daobuilder
		//Optional<Film> f = getFilms().stream().filter(fl-> fl.getId() == this.id).findFirst();
		Film f = filmDao.findById(this.id);
		
		/* old version with optionnnal
		 * if (f.isPresent()) {
			setEditId(f.get().getId());
			setEditTitre(f.get().getTitle());
			setEditAnnee(f.get().getAnnee());
			setEditRealisateur(f.get().getRealisateur());
			setEditDescription(f.get().getDescription());
			setEditRating(f.get().getRating());*/
		if(f !=  null) {
			setEditId(f.getId());
			setEditTitre(f.getTitle());
			setEditAnnee(f.getAnnee());
			setEditRealisateur(f.getRealisateur());
			setEditDescription(f.getDescription());
			setEditRating(f.getRating());
			return SUCCESS;
		}else
		{
			return ERROR;	
		}
	}
	// get => /create
	public String creerFilm() {
		log.info("passage ds editFilm; id => " + this.id);

		setEditId(0);
		setEditTitre("un titre");
		setEditAnnee(0);
		setEditRealisateur("moi ...");
		setEditDescription("bof bof ...");
		setEditRating(0);
		return SUCCESS;
	}
	
	public String saveFilm() {
		log.info("passage ds editFilm; id => " + this.id);
		// optional car il peut ne pas en trouver
		// un optionall contient l objet indiqué ou indique sil ne contient pas avc isPresent
		/* comme methode au dessys
		 * Optional<Film> f = films.stream().filter(fl-> fl.getId() == this.editId).findFirst();
		 */
		
		Film f = filmDao.findById(this.editId);
		
		if (f == null && getEditId() == 0){
			f = new Film();
		}
		if (f != null) {
			f.setTitle(editTitre);
			f.setAnnee(editAnnee);
			f.setDescription(editDescription);
			f.setRealisateur(editRealisateur);
			f.setRating(editRating);
			filmDao.save(f);
			log.info("film save => " + f);
			return SUCCESS;
		}else{
			return ERROR;	
		}
		
	}
	
	public String deleteFilm() {
		filmDao.delete(this.id);
		return SUCCESS;
	}

	
}
