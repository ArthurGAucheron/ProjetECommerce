package com.intiformation.ecommerce.service;

import com.intiformation.ecommerce.modeles.Client;

/**
 * interface de la couche service spécifique pour les clients
 * hérite de IGeneriqueService
 * @author marle
 *
 */
public interface IClientService extends IGeneriqueService<Client>{

	public boolean clientExists(String pMail, String pPassword);
	
}//end interface
