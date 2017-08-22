package com.otmanel.struts2_spring_jpaFirst.actions;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.otmanel.struts2_spring_jpaFirst.metier.Categorie;
import com.otmanel.struts2_spring_jpaFirst.metier.Produit;
import com.otmanel.struts2_spring_jpaFirst.repositories.ICatDao;
import com.otmanel.struts2_spring_jpaFirst.repositories.ICategorieDao;
import com.otmanel.struts2_spring_jpaFirst.repositories.IProduitDao;

public class ProduitAction extends ActionSupport implements ModelDriven<Produit> {
	
	private Produit model;
	@Override
	public Produit getModel() {
		if( model == null) model = new Produit();
		return model;
	}
	// injectiondu dao par spring
	private IProduitDao produitDao;
	private List<Produit> produits;
	private List<Categorie> allCategories;
	
	public List<Categorie> getAllCategories() {return allCategories;}
	public void setAllCategories(List<Categorie> allCategories) {this.allCategories = allCategories;}
	
	private ICatDao catDao;
	
	private ICategorieDao categorieDao;
	public void setCategorieDao(ICategorieDao categorieDao) {
		this.categorieDao = categorieDao;
	}
	public void setCatDao(ICatDao catDao) {this.catDao = catDao;}
	private int pid;
	private int cid;
	
	public int getPid() {return pid;}
	public void setPid(int pid) {this.pid = pid;}
	public int getCid() {return cid;}
	public void setCid(int cid) {this.cid = cid;}
	
	public String removeCategorieFromProduct() {
		catDao.removeCategorieFromProduit(pid, cid);
		return SUCCESS;
	}
	
	//categorie
	public String addCategorieToProduct() {
		catDao.addCategorieToProduit(pid, cid);
		return SUCCESS;
	}
	// list de produits
	public List<Produit> getProduits() {return produits;}
	
	public IProduitDao getProduitDao() {return produitDao;}
	public void setProduitDao(IProduitDao produitDao) {this.produitDao = produitDao;}
	
	// action pour obenir la liste
	public String liste() {
		this.produits = produitDao.findAll(/*true*/);
		return SUCCESS;
	}
	public String edit() {
		Produit toEdit = produitDao.findById(getModel().getId());
		if (toEdit == null ) return ERROR;
		this.model.setNom(toEdit.getNom());
		this.model.setPoid(toEdit.getPoid());
		this.model.setPrix(toEdit.getPrix());
		this.model.setCategories(toEdit.getCategories());
		this.allCategories = categorieDao.findAll();
		return SUCCESS;
	}
	public String create() {
		return SUCCESS;
	}
	public String save() {
		produitDao.save(getModel());
		return SUCCESS;
	}
	public String delete() {
		produitDao.delete(getModel().getId());
		return SUCCESS;
	}

	
}
