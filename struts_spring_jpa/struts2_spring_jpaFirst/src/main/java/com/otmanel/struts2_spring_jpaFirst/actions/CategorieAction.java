package com.otmanel.struts2_spring_jpaFirst.actions;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.otmanel.struts2_spring_jpaFirst.metier.Categorie;
import com.otmanel.struts2_spring_jpaFirst.repositories.ICategorieDao;


public class CategorieAction extends ActionSupport implements ModelDriven<Categorie> {

	private Categorie model;
	@Override
	public Categorie getModel() {
		if( model == null ) model = new Categorie();
		return model;
	}
	// injectiondu dao par spring
		private ICategorieDao categorieDao;
		private List<Categorie> categories;
		
		// list de produits
		public List<Categorie> getCategories() {return categories;}
		
		public ICategorieDao getCategorieDao() {return categorieDao;}
		public void setCategorieDao(ICategorieDao categorieDao) {this.categorieDao = categorieDao;}
		
		// action pour obenir la liste
		public String liste() {
			this.categories = categorieDao.findAll();
			return SUCCESS;
		}
		public String edit() {
			Categorie toEdit = categorieDao.findById(getModel().getId());
			if (toEdit == null ) return ERROR;
			this.model.setLibelle(toEdit.getLibelle());
			return SUCCESS;
		}
		public String create() {
			return SUCCESS;
		}
		public String save() {
			categorieDao.save(getModel());
			return SUCCESS;
		}
		public String delete() {
			categorieDao.delete(getModel().getId());
			return SUCCESS;
		}

}
