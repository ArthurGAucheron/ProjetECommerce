package com.intiformation.ecommerce.dao;

import java.util.List;

import com.intiformation.ecommerce.modeles.Produit;

/**
 * interface de la DAO spécifique aux produits
 * hérite de IGeneriqueDAO
 * déclare les méthodes spécifiques aux produits
 * @author marle
 *
 */
public interface IProduitDAO extends IGeneriqueDAO<Produit>{

	public List<Produit> getProduitsByIDCategorie(Integer pIDCategorie);
	
	public List<Produit> getProduitsByMotCle(String pMotCle);
	
	public List<Produit> getProduitsSelectionnes();
	
}//end interface
