package com.intiformation.ecommerce.service;

import java.util.List;

import com.intiformation.ecommerce.modeles.Produit;

/**
 * interface de la couche service spécifique pour les produits
 * hérite de IGeneriqueService
 * @author marle
 *
 */
public interface IProduitService extends IGeneriqueService<Produit>{

	public List<Produit> findProduitsByIDCategorie(Integer pIDCategorie);
	
	public List<Produit> findProduitsByMotCle(String pMotCle);
	
	public List<Produit> findProduitsSelectionnes();
	
}//end interface
