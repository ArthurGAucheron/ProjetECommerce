package com.intiformation.ecommerce.service;

import java.util.List;

import com.intiformation.ecommerce.dao.ClientDAOImpl;
import com.intiformation.ecommerce.dao.IClientDAO;
import com.intiformation.ecommerce.modeles.Client;

/**
 * implémentation concrète de la couche Service pour les clients
 * implémente IClientService
 * @author marle
 *
 */
public class ClientServiceImpl implements IClientService{

	//déclaration de la couche DAO
	IClientDAO clientDAO = new ClientDAOImpl();

	@Override
	public List<Client> findAll() {
		return clientDAO.getAll();
	}

	@Override
	public Client findById(Integer pIdClient) {
		return clientDAO.getById(pIdClient);
	}

	@Override
	public boolean ajouter(Client pClient) {
		return clientDAO.add(pClient);
	}

	@Override
	public boolean modifier(Client pClient) {
		return clientDAO.update(pClient);
	}

	@Override
	public boolean supprimerById(Integer pIdClient) {
		return clientDAO.deleteById(pIdClient);
	}

	@Override
	public boolean clientExists(String pMail, String pPassword) {
		return clientDAO.isClientExists(pMail, pPassword);
	}
	
}//end class
