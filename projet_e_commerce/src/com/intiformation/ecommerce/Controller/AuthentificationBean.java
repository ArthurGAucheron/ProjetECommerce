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

	/*___________ propriétés _____________*/
	private String userName;
	private String userPassword;
	
	//déclaration de la couche service
	private IUtilisateurService utilisateurService;
		
	/*___________ ctors _____________*/

	/**
	 * ctor vide pour instanciation du MB par le Serveur
	 */
	public AuthentificationBean() {
		utilisateurService = new UtilisateurServiceImpl();
	}//end ctor
	
	/*___________ méthodes _____________*/
	
	/**
	 * permet de faire connecter l'utilisateur à son espace et lui créer une session
	 * invoquée à la soumission du formulaire d'authentification
	 * @return
	 */
	public String connecterUtilisateur() {
		
		//1. déclaration du contexte de JSF : objet FacesContext
		FacesContext contextJSF = FacesContext.getCurrentInstance(); 
		
		//2. vérif si l'utilisateur existe
		if (utilisateurService.utilisateurExists(userName, userPassword)) {
			
			//création de la session 
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
	 * permet de faire déconnecter l'utilisateur de son espace et de détruire la session
	 * méthode invoquée à la soumission du bouton 'Se Déconnecter'
	 * @return
	 */
	public String deconnecterUtilisateur() {
		
		//1. récup du context de JSF (pour récupérer la session)
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//2. récup de la session et destruction
		HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(false);
		session.invalidate();
		
		//3. msg de deconnexion vers la vue
		FacesMessage msgDeconnexion = new FacesMessage(FacesMessage.SEVERITY_INFO, "Déconnexion ", ": vous êtes maintenant déconnecté");
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
