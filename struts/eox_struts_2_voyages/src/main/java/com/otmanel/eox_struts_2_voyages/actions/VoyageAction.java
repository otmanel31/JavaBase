package com.otmanel.eox_struts_2_voyages.actions;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.otmanel.eox_struts_2_voyages.metiers.Agence;
import com.otmanel.eox_struts_2_voyages.metiers.Voyage;
import com.otmanel.eox_struts_2_voyages.utils.GeneriqueDao;
import com.otmanel.eox_struts_2_voyages.utils.GeneriqueDao.GenericDaoBuilder;


public class VoyageAction extends ActionSupport implements ApplicationAware, ModelDriven<Voyage> {
	Logger log = LogManager.getLogger(VoyageAction.class);
	private GeneriqueDao<Voyage> voyageDao;
	private GeneriqueDao<Agence> agenceDao;

	private Voyage model;
	private List<Voyage> voyages;
	private List<Agence> agences;
	
	@Override
	public Voyage getModel() {
		if (model == null) model = new Voyage();
		return model;
	}

	@Override
	public void setApplication(Map<String, Object> arg0) {
		// arg0  ==> context
		this.voyageDao = (GeneriqueDao<Voyage>) arg0.get("voyageDao");
		this.agenceDao = (GeneriqueDao<Agence>) arg0.get("agenceDao");
	}
	public List<Voyage> getVoyages(){
		log.info("appel de la liste des films: methodes => getVoyages");
		if (voyages == null ) { 
			this.voyages = voyageDao.findAll();
			for (Voyage v: voyages) {
				// je recupere lobjet agence associer a mon voyage (via idVoyage) et je lassovcie a mon voyage
				v.setAgencee(agenceDao.findById(v.getAgence()));
			}
		}
		return this.voyages;
	}
	public List<Agence> getAgences(){
		if (this.agences == null) agences = agenceDao.findAll();
		return this.agences;
	}
	public String listVoyages() {
		log.info("appel methodes => listVoyages");
		return SUCCESS;
	}
	public String editerVoyage() {
		log.info("appel de l'edition vpoyage: methodes => editVoyage, id => " + getModel().getId());
		Voyage m = getModel();
		Voyage v = voyageDao.findById(m.getId());
		//System.out.println("PASSPORT " + v.isPassport());
		if (v != null) {
			m.copyFrom(v);
			/*m.setId(v.getId());
			m.setLibelle(v.getLibelle());
			m.setDestination(v.getDestination());
			m.setDateDept(v.getDateDept());
			m.setDateRetour(v.getDateRetour());
			m.setAgence(v.getAgence());
			m.setPrix(v.getPrix());
			m.setPassport(v.isPassport())*/;
			return SUCCESS;
		}
		return ERROR;
	}
	public String delete() {
		Voyage m = getModel();
		voyageDao.delete(m.getId());
		return SUCCESS;
	}
	public String create() {
		Voyage m = getModel();
		m.setLibelle("libelle");
		m.setDestination("");
		LocalDate today = LocalDate.now();
		LocalDate inOneWeek = today.plusDays(7);
		m.setDateDept(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		m.setDateRetour(Date.from(inOneWeek.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		m.setPrix(99.99);
		m.setAgence(1);
		m.setPassport(false);
		return SUCCESS;
		
	}
	public String save() {
		log.info(getModel().toString());
		LocalDate depart = getModel().getDateDept().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate retour = getModel().getDateRetour().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate passee = LocalDate.now().minusWeeks(2);
		LocalDate futur = LocalDate.now().plusYears(2);
		if( depart.isAfter(retour)) {
			addActionError("la date retour ne peut etre avant le depart");
			return INPUT;
		}
		if (depart.isBefore(passee)) {
			addFieldError("dateDept", "on  ne peuw editer un voyage ds la passéé");
			return INPUT;
		}
		if (retour.isAfter(futur)) {
			addFieldError("dateRetour", "on ne peux prévoir un voyage autant en avance");
			return INPUT;
		}
		voyageDao.save(getModel());
		return SUCCESS;
	}
}
