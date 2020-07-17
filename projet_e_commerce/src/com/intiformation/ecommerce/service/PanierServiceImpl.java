package com.intiformation.ecommerce.service;

import java.util.List;

import com.intiformation.ecommerce.dao.IPanierDAO;
import com.intiformation.ecommerce.dao.PanierDAOImpl;
import com.intiformation.ecommerce.modeles.Panier;

/**
 * implémentation concrète de la couche Service pour le panier
 * implémente IPanierService
 * @author marle
 *
 */
public class PanierServiceImpl implements IPanierService{

	//déclaration de la couche DAO
	IPanierDAO panierDAO = new PanierDAOImpl();
	
	@Override
	public List<Panier> findAll() {
		return panierDAO.getAll();
	}

	@Override
	public Panier findById(Integer pIdPanier) {
		return panierDAO.getById(pIdPanier);
	}

	@Override
	public boolean ajouter(Panier pPanier) {
		return panierDAO.add(pPanier);
	}

	@Override
	public boolean modifier(Panier pPanier) {
		return panierDAO.update(pPanier);
	}

	@Override
	public boolean supprimerById(Integer pIdPanier) {
		return panierDAO.deleteById(pIdPanier);
	}

	@Override
	public boolean panierIsExist(int pIdPanier) {
		return panierDAO.panierIsExist(pIdPanier);
	}

	@Override
	public int getIdLastAdd() {
		return panierDAO.getIdLastAdd();
	}

}//end class
