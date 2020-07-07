package com.intiformation.ecommerce.dao;

import com.intiformation.ecommerce.modeles.Utilisateur;

/**
 * interface de la DAO spécifique aux utilisateurs
 * hérite de IGeneriqueDAO
 * déclare les méthodes spécifiques aux utilisateurs
 * @author marle
 *
 */
public interface IUtilisateurDAO extends IGeneriqueDAO<Utilisateur>{

	public boolean isUtilisateurExists(String pUserName, String pPassword);
	
}//end interface
