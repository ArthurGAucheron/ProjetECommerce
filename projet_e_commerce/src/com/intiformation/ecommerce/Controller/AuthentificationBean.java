package com.intiformation.ecommerce.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.intiformation.ecommerce.service.IUtilisateurService;
import com.intiformation.ecommerce.service.UtilisateurServiceImpl;

/**
 * Managed Bean pour la gestion de l'authentification de l'utilisateur
 * @author marle
 *
 */
@ManagedBean(name="authentificationBean")
@SessionScoped
public class AuthentificationBean implements Serializable {

	/*___________ propri�t�s _____________*/
	private String userName;
	private String userPassword;
	
	//d�claration de la couche service
	private IUtilisateurService utilisateurService;
		
	/*___________ ctors _____________*/

	/**
	 * ctor vide pour instanciation du MB par le Serveur
	 */
	public AuthentificationBean() {
		utilisateurService = new UtilisateurServiceImpl();
	}//end ctor
	
	/*___________ m�thodes _____________*/
	
	/**
	 * permet de faire connecter l'utilisateur � son espace et lui cr�er une session
	 * invoqu�e � la soumission du formulaire d'authentification
	 * @return
	 */
	public String connecterUtilisateur() {
		
		//1. d�claration du contexte de JSF : objet FacesContext
		FacesContext contextJSF = FacesContext.getCurrentInstance(); 
		
		//2. v�rif si l'utilisateur existe
		if (utilisateurService.utilisateurExists(userName, userPassword)) {
			
			//cr�ation de la session 
			HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(true);
			
			//sauvegarde du nom de l'utilisateur dans la session
			session.setAttribute("user_name", userName);
			
			//navigation vers la page 'page-principale-utilisateur.xhtml'
			return "page-principale-utilisateur.xhtml";
			
		}else {
			
			//envoi d'un msg vers la vue 
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec de connexion ", ": Identifiant ou Mot de passe invalide");
			contextJSF.addMessage(null, message);
			
			//navigation vers le formulaire d'authentification login-utilisateur
			return "login-utilisateur.xhtml";
			
		}//end else

	}//end connecterUtilisateur

	/**
	 * permet de faire d�connecter l'utilisateur de son espace et de d�truire la session
	 * m�thode invoqu�e � la soumission du bouton 'Se D�connecter'
	 * @return
	 */
	public String deconnecterUtilisateur() {
		
		//1. r�cup du context de JSF (pour r�cup�rer la session)
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//2. r�cup de la session et destruction
		HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(false);
		session.invalidate();
		
		//3. msg de deconnexion vers la vue
		FacesMessage msgDeconnexion = new FacesMessage(FacesMessage.SEVERITY_INFO, "D�connexion ", ": vous �tes maintenant d�connect�");
		contextJSF.addMessage(null, msgDeconnexion);
		 
		//3. redirection vers la page du formulaire login-utilisateur
		return "login-utilisateur.xhtml";
		
	}//end deconnecterUtilisateur

	/*___________ getters/setters _____________*/

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}//end class
