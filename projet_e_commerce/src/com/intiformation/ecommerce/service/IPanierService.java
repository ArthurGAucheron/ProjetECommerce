package com.intiformation.ecommerce.service;

import com.intiformation.ecommerce.modeles.Panier;

/**
 * interface de la couche service spécifique pour le panier
 * hérite de IGeneriqueService
 * @author marle
 *
 */
public interface IPanierService extends IGeneriqueService<Panier> {

	
	public boolean panierIsExist(int pIdPanier);
	
}//end interface
