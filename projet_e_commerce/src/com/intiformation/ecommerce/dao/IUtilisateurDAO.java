package com.intiformation.ecommerce.dao;

import com.intiformation.ecommerce.modeles.Utilisateur;

/**
 * interface de la DAO sp�cifique aux utilisateurs
 * h�rite de IGeneriqueDAO
 * d�clare les m�thodes sp�cifiques aux utilisateurs
 * @author marle
 *
 */
public interface IUtilisateurDAO extends IGeneriqueDAO<Utilisateur>{

	public boolean isUtilisateurExists(String pUserName, String pPassword);
	
}//end interface
