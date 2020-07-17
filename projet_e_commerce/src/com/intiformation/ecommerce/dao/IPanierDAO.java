package com.intiformation.ecommerce.dao;

import com.intiformation.ecommerce.modeles.Panier;

/**
 * interface de la DAO spécifique aux paniers
 * hérite de IGeneriqueDAO
 * déclare les méthodes spécifiques aux paniers
 * @author marle
 *
 */
public interface IPanierDAO extends IGeneriqueDAO<Panier>{

	public boolean panierIsExist(int pIdPanier);
	
}//end interface
