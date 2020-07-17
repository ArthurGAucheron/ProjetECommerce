package com.intiformation.ecommerce.dao;

import com.intiformation.ecommerce.modeles.Categorie;

/**
 * interface de la DAO spécifique aux catégories
 * hérite de IGeneriqueDAO
 * déclare les méthodes spécifiques aux catégories
 * @author marle
 *
 */
public interface ICategorieDAO extends IGeneriqueDAO<Categorie>{
	
	public int getIdByName(String nomCategorie);

}//end interface
