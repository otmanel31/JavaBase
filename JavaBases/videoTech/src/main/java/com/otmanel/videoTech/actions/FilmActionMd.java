package com.otmanel.videoTech.actions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.otmanel.videoTech.metiers.Film;
import com.otmanel.videoTech.utils.GeneriqueDao;

public class FilmActionMd extends ActionSupport  implements ApplicationAware, ModelDriven<Film>{
	Logger log = LogManager.getLogger(FilmAction.class);
	private List<Film> films;
	private GeneriqueDao<Film> filmDao;
	private Film model;
	@Override
	public Film getModel() {
		if (model == null) model = new Film();
		return model;
	}
	
	@Override
	public void setApplication(Map<String, Object> ctx) {
		this.filmDao = (GeneriqueDao<Film>)ctx.get("filmDAO");
	}
	
	
	public List<Film> getFilms() {
		log.info("appel de getFilms");//return films;
		if (films == null)
			films = filmDao.findAll();
		return films;
	}
	public String listFilms() {
		log.info("appel de listFilm");
		return SUCCESS;
	}
	
	public String editerFilm() {
		log.info("appel de editer film avc id " + this.getModel().getId());
		Film m = getModel();
		Film f = filmDao.findById(m.getId());
		if (f != null) {
			m.setTitle(f.getTitle());
			m.setAnnee(f.getAnnee());
			m.setRealisateur(f.getRealisateur());
			m.setDescription(f.getDescription());
			m.setRating(f.getRating());
			return SUCCESS;
		}
		else return ERROR;
	}
	public String creerFilm() {
		log.info("passage ds creerFilm; id => " );
		Film m = getModel();
		m.setId(0);
		m.setTitle("un titre ... ");
		m.setAnnee(1999);
		m.setRealisateur("moi pardi ...");
		m.setDescription("tralalalala" );
		m.setRating(3);
		
		return SUCCESS;
	}
	public String deleteFilm() {
		Film m = getModel();
		filmDao.delete(m.getId());
		return SUCCESS;
	}
	public String saveFilm() {
		log.info("passage ds editFilm; id => " + getModel().getId());
		Film m = getModel();
		// on essaye de voir si le film existe
		Film f = filmDao.findById(m.getId());
		// tentative de maj dun film inexsitant
		if (f == null && m.getId() != 0) return ERROR;
		// ds le cas contrairee etdition  /creation == > a=on sauvbegarde
		
		filmDao.save(m);
		log.info("sauvegarde succes");
		
		return SUCCESS;
		
	}
}

