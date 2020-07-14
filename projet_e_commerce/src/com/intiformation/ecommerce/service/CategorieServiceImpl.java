package com.intiformation.ecommerce.service;

import java.util.List;

import com.intiformation.ecommerce.dao.CategorieDAOImpl;
import com.intiformation.ecommerce.dao.ICategorieDAO;
import com.intiformation.ecommerce.modeles.Categorie;

/**
 * implémentation concrète de la couche Service pour les catégories
 * implémente ICategorieService
 * @author marle
 *
 */
public class CategorieServiceImpl implements ICategorieService{

	//déclaration de la couche DAO
	ICategorieDAO categorieDAO = new CategorieDAOImpl();
	
	@Override
	public List<Categorie> findAll() {
		return categorieDAO.getAll();
	}

	@Override
	public Categorie findById(Integer pIdCategorie) {
		return categorieDAO.getById(pIdCategorie);
	}

	@Override
	public boolean ajouter(Categorie pCategorie) {
		return categorieDAO.add(pCategorie);
	}

	@Override
	public boolean modifier(Categorie pCategorie) {
		return categorieDAO.update(pCategorie);
	}

	@Override
	public boolean supprimerById(Integer pIdCategorie) {
		return categorieDAO.deleteById(pIdCategorie);
	}

	@Override
	public int getIdByName(String nomCategorie) {
		return categorieDAO.getIdByName(nomCategorie);
	}
	

}//end class
