package com.intiformation.ecommerce.dao;

import com.intiformation.ecommerce.modeles.Client;

/**
 * interface de la DAO spécifique aux clients
 * hérite de IGeneriqueDAO
 * déclare les méthodes spécifiques aux clients
 * @author marle
 *
 */
public interface IClientDAO extends IGeneriqueDAO<Client>{

	public boolean isClientExists(String pMail, String pPassword);
	
}//end interface
