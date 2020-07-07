package com.intiformation.ecommerce.dao;

import java.util.List;

import com.intiformation.ecommerce.modeles.Produit;

/**
 * interface de la DAO sp�cifique aux produits
 * h�rite de IGeneriqueDAO
 * d�clare les m�thodes sp�cifiques aux produits
 * @author marle
 *
 */
public interface IProduitDAO extends IGeneriqueDAO<Produit>{

	public List<Produit> getProduitsByIDCategorie(Integer pIDCategorie);
	
	public List<Produit> getProduitsByMotCle(String pMotCle);
	
	public List<Produit> getProduitsSelectionnes();
	
}//end interface
