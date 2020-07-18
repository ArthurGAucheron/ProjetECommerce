package com.intiformation.ecommerce.service;

import java.util.List;

import com.intiformation.ecommerce.dao.CommandeDAOImpl;
import com.intiformation.ecommerce.dao.ICommandeDAO;
import com.intiformation.ecommerce.modeles.Commande;

/**
 * implémentation concrète de la couche Service pour les commandes
 * implémente ICommandeService
 * @author marle
 *
 */
public class CommandeServiceImpl implements ICommandeService{

	//déclaration de la couche DAO
	ICommandeDAO commandeDAO = new CommandeDAOImpl();

	@Override
	public List<Commande> findAll() {
		return commandeDAO.getAll();
	}

	@Override
	public Commande findById(Integer pIdCommande) {
		return commandeDAO.getById(pIdCommande);
	}

	@Override
	public boolean ajouter(Commande pCommande) {
		return commandeDAO.add(pCommande);
	}

	@Override
	public boolean modifier(Commande pCommande) {
		return commandeDAO.update(pCommande);
	}

	@Override
	public boolean supprimerById(Integer pIdCommande) {
		return commandeDAO.deleteById(pIdCommande);
	}

	@Override
	public int getIdByLastAdd() {
	
		return commandeDAO.getIdByLastAdd();
	}

	@Override
	public List<Commande> getAllByIdCLient(int pIdClient) {
		return commandeDAO.getAllByIdClient(pIdClient);
	}
	
}//end class
