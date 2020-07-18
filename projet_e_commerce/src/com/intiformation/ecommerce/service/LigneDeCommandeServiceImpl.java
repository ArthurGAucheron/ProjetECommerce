package com.intiformation.ecommerce.service;

import java.util.List;

import com.intiformation.ecommerce.dao.ILigneDeCommandeDAO;
import com.intiformation.ecommerce.dao.LigneDeCommandeDAOImpl;
import com.intiformation.ecommerce.modeles.LigneDeCommande;

/**
 * implémentation concrète de la couche Service pour les lignes de commande
 * implémente ILigneDeCommandeService
 * @author marle
 *
 */
public class LigneDeCommandeServiceImpl implements ILigneDeCommandeService{

	//déclaration de la couche DAO
	ILigneDeCommandeDAO ligneDeCommandeDAO = new LigneDeCommandeDAOImpl();

	@Override
	public List<LigneDeCommande> findAll() {
		return ligneDeCommandeDAO.getAll();
	}

	@Override
	public LigneDeCommande findById(Integer pIdLigneDeCommande) {
		return ligneDeCommandeDAO.getById(pIdLigneDeCommande);
	}

	@Override
	public boolean ajouter(LigneDeCommande pLigneDeCommande) {
		return ligneDeCommandeDAO.add(pLigneDeCommande);
	}

	@Override
	public boolean modifier(LigneDeCommande pLigneDeCommande) {
		return ligneDeCommandeDAO.update(pLigneDeCommande);
	}

	@Override
	public boolean supprimerById(Integer pIdLigneDeCommande) {
		return ligneDeCommandeDAO.deleteById(pIdLigneDeCommande);
	}

	@Override
	public int nombreLignesParPanier(int pIdPanier) {
		return ligneDeCommandeDAO.nombreLignesParPanier(pIdPanier);
	}

	@Override
	public List<LigneDeCommande> getAllByIdPanier(int pIdPanier) {
		return ligneDeCommandeDAO.getAllByIdPanier(pIdPanier);
	}

	@Override
	public List<LigneDeCommande> getAllByIdCommande(int pIdCommande) {
	
		return ligneDeCommandeDAO.getAllByIdCommande(pIdCommande);
	}
	


}//end class
