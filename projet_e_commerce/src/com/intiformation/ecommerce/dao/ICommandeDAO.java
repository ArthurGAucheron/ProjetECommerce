package com.intiformation.ecommerce.dao;

import java.util.List;

import com.intiformation.ecommerce.modeles.Commande;

/**
 * interface de la DAO sp�cifique aux commandes
 * h�rite de IGeneriqueDAO
 * d�clare les m�thodes sp�cifiques aux commandes
 * @author marle
 *
 */
public interface ICommandeDAO extends IGeneriqueDAO<Commande>{
	
	
	public int getIdByLastAdd();
	
	public List<Commande> getAllByIdClient(int pIdClient);
	
}//end interface
