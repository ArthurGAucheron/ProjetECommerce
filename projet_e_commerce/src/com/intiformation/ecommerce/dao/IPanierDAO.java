package com.intiformation.ecommerce.dao;

import com.intiformation.ecommerce.modeles.Panier;

/**
 * interface de la DAO sp�cifique aux paniers
 * h�rite de IGeneriqueDAO
 * d�clare les m�thodes sp�cifiques aux paniers
 * @author marle
 *
 */
public interface IPanierDAO extends IGeneriqueDAO<Panier>{

	public boolean panierIsExist(int pIdPanier);
	
}//end interface
