package com.intiformation.ecommerce.dao;

import java.util.List;

import com.intiformation.ecommerce.modeles.Commande;

/**
 * interface de la DAO spécifique aux commandes
 * hérite de IGeneriqueDAO
 * déclare les méthodes spécifiques aux commandes
 * @author marle
 *
 */
public interface ICommandeDAO extends IGeneriqueDAO<Commande>{
	
	
	public int getIdByLastAdd();
	
	public List<Commande> getAllByIdClient(int pIdClient);
	
}//end interface
