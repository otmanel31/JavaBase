package com.otmanel.struts2_spring_jpaFirst.repositories;

import org.springframework.transaction.annotation.Transactional;

public interface ICatDao {

	void removeCategorieFromProduit(int idProd, int idCat);

	void addCategorieToProduit(int idProd, int idCat);

}