package com.intiformation.ecommerce.service;

import com.intiformation.ecommerce.modeles.Client;

/**
 * interface de la couche service sp�cifique pour les clients
 * h�rite de IGeneriqueService
 * @author marle
 *
 */
public interface IClientService extends IGeneriqueService<Client>{

	public boolean clientExists(String pMail, String pPassword);
	
}//end interface
