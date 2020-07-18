package com.intiformation.ecommerce.service;

import java.util.List;

import com.intiformation.ecommerce.modeles.Commande;

/**
 * interface de la couche service sp�cifique pour les commandes
 * h�rite de IGeneriqueService
 * @author marle
 *
 */
public interface ICommandeService extends IGeneriqueService<Commande>{
	
	public int getIdByLastAdd();
	
	public List<Commande> getAllByIdCLient (int pIdClient);

}//end interface
