package com.intiformation.ecommerce.service;

import java.util.List;

import com.intiformation.ecommerce.dao.CategorieDAOImpl;
import com.intiformation.ecommerce.dao.ICategorieDAO;
import com.intiformation.ecommerce.modeles.Categorie;

/**
 * impl�mentation concr�te de la couche Service pour les cat�gories
 * impl�mente ICategorieService
 * @author marle
 *
 */
public class CategorieServiceImpl implements ICategorieService{

	//d�claration de la couche DAO
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
