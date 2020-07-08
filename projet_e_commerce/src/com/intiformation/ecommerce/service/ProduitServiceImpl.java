package com.intiformation.ecommerce.service;

import java.util.List;

import com.intiformation.ecommerce.dao.IProduitDAO;
import com.intiformation.ecommerce.dao.ProduitDAOImpl;
import com.intiformation.ecommerce.modeles.Produit;

/**
 * implémentation concrète de la couche Service pour les produits
 * implémente IProduitService
 * @author marle
 *
 */
public class ProduitServiceImpl implements IProduitService {

	//déclaration de la couche DAO
	IProduitDAO produitDAO = new ProduitDAOImpl();

	@Override
	public List<Produit> findAll() {
		return produitDAO.getAll();
	}

	@Override
	public Produit findById(Integer pIdProduit) {
		return produitDAO.getById(pIdProduit);
	}

	@Override
	public boolean ajouter(Produit pProduit) {
		return produitDAO.add(pProduit);
	}

	@Override
	public boolean modifier(Produit pProduit) {
		return produitDAO.update(pProduit);
	}

	@Override
	public boolean supprimerById(Integer pIdProduit) {
		return produitDAO.deleteById(pIdProduit);
	}

	@Override
	public List<Produit> findProduitsByIDCategorie(Integer pIDCategorie) {
		return produitDAO.getProduitsByIDCategorie(pIDCategorie);
	}

	@Override
	public List<Produit> findProduitsByMotCle(String pMotCle) {
		return produitDAO.getProduitsByMotCle(pMotCle);
	}

	@Override
	public List<Produit> findProduitsSelectionnes() {
		return produitDAO.getProduitsSelectionnes();
	}
	
}//end class
