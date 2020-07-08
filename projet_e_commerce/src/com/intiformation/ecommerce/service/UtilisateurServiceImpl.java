package com.intiformation.ecommerce.service;

import java.util.List;

import com.intiformation.ecommerce.dao.IUtilisateurDAO;
import com.intiformation.ecommerce.dao.UtilisateurDAOImpl;
import com.intiformation.ecommerce.modeles.Utilisateur;

/**
 * impl�mentation concr�te de la couche Service pour les utilisateurs
 * impl�mente IUtilisateurService
 * @author marle
 *
 */
public class UtilisateurServiceImpl implements IUtilisateurService{

	//d�claration de la couche DAO
	IUtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();

	@Override
	public List<Utilisateur> findAll() {
		return utilisateurDAO.getAll();
	}

	@Override
	public Utilisateur findById(Integer pIdUtilisateur) {
		return utilisateurDAO.getById(pIdUtilisateur);
	}

	@Override
	public boolean ajouter(Utilisateur pUtilisateur) {
		return utilisateurDAO.add(pUtilisateur);
	}

	@Override
	public boolean modifier(Utilisateur pUtilisateur) {
		return utilisateurDAO.update(pUtilisateur);
	}

	@Override
	public boolean supprimerById(Integer pIdUtilisateur) {
		return utilisateurDAO.deleteById(pIdUtilisateur);
	}

	@Override
	public boolean utilisateurExists(String pUserName, String pPassword) {
		return utilisateurDAO.isUtilisateurExists(pUserName, pPassword);
	}
	
}//end class
