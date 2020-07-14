package com.intiformation.ecommerce.service;

import com.intiformation.ecommerce.modeles.Categorie;

/**
 * interface de la couche service sp�cifique pour les categories
 * h�rite de IGeneriqueService
 * @author marle
 *
 */
public interface ICategorieService extends IGeneriqueService<Categorie>{
	
	public int getIdByName(String nomCategorie);

}//end interface
