package com.intiformation.ecommerce.service;

import com.intiformation.ecommerce.modeles.Utilisateur;

/**
 * interface de la couche service sp�cifique pour les utilisateurs
 * h�rite de IGeneriqueService
 * @author marle
 *
 */
public interface IUtilisateurService extends IGeneriqueService<Utilisateur>{

	public boolean utilisateurExists(String pUserName, String pPassword);
	
}//end interface
