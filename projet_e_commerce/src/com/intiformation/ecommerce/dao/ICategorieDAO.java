package com.intiformation.ecommerce.dao;

import com.intiformation.ecommerce.modeles.Categorie;

/**
 * interface de la DAO sp�cifique aux cat�gories
 * h�rite de IGeneriqueDAO
 * d�clare les m�thodes sp�cifiques aux cat�gories
 * @author marle
 *
 */
public interface ICategorieDAO extends IGeneriqueDAO<Categorie>{
	
	public int getIdByName(String nomCategorie);

}//end interface
