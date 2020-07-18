package com.intiformation.ecommerce.dao;

import java.util.List;

import com.intiformation.ecommerce.modeles.LigneDeCommande;

/**
 * interface de la DAO spécifique aux lignes de commmande
 * hérite de IGeneriqueDAO
 * déclare les méthodes spécifiques aux lignes de commande
 * @author marle
 *
 */
public interface ILigneDeCommandeDAO extends IGeneriqueDAO<LigneDeCommande>{
	
	public int nombreLignesParPanier (int pIdPanier);
	
	public List<LigneDeCommande> getAllByIdPanier (int pIdPanier);
	
	public List<LigneDeCommande> getAllByIdCommande (int pIdCommande);
	

}//end interface
