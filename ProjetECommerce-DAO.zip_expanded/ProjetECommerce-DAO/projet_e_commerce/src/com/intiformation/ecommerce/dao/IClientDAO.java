package com.intiformation.ecommerce.dao;

import com.intiformation.ecommerce.modeles.Client;

/**
 * interface de la DAO sp�cifique aux clients
 * h�rite de IGeneriqueDAO
 * d�clare les m�thodes sp�cifiques aux clients
 * @author marle
 *
 */
public interface IClientDAO extends IGeneriqueDAO<Client>{

	public boolean isClientExists(String pMail, String pPassword);
	
}//end interface
