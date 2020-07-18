package com.intiformation.ecommerce.dao;

import java.util.List;

import com.intiformation.ecommerce.modeles.LigneDeCommande;

/**
 * interface de la DAO sp�cifique aux lignes de commmande
 * h�rite de IGeneriqueDAO
 * d�clare les m�thodes sp�cifiques aux lignes de commande
 * @author marle
 *
 */
public interface ILigneDeCommandeDAO extends IGeneriqueDAO<LigneDeCommande>{
	
	public int nombreLignesParPanier (int pIdPanier);
	
	public List<LigneDeCommande> getAllByIdPanier (int pIdPanier);
	
	public List<LigneDeCommande> getAllByIdCommande (int pIdCommande);
	

}//end interface
