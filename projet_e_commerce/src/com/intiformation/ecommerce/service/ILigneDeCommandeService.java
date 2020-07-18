package com.intiformation.ecommerce.service;

import java.util.List;

import com.intiformation.ecommerce.modeles.LigneDeCommande;

/**
 * interface de la couche service spécifique pour les lignes de commande
 * hérite de IGeneriqueService
 * @author marle
 *
 */
public interface ILigneDeCommandeService extends IGeneriqueService<LigneDeCommande>{
	
	public int nombreLignesParPanier(int pIdPanier);
	
	public List<LigneDeCommande> getAllByIdPanier ( int pIdPanier);
	
	public List<LigneDeCommande> getAllByIdCommande( int pIdCommande);

}//end interface
