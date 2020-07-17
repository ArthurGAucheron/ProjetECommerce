package com.intiformation.ecommerce.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.intiformation.ecommerce.modeles.Categorie;
import com.intiformation.ecommerce.modeles.Utilisateur;
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
	private boolean actived;
	
	private Utilisateur utilisateur = null;
	
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
		
			//sauvegarde du nom de l'utilisateur et mdp dans la session
			session.setAttribute("username", userName);
			session.setAttribute("userpwd", userPassword);
			
			setUtilisateur(utilisateurService.findUtilisateur(userName, userPassword));
			
			session.setAttribute("user", utilisateur);
			
			if(utilisateur.getActived()) {
				return "page-principale-utilisateur.xhtml";
			}else {
				return "page-principale-utilisateur-unactivated.xhtml";				
			}	

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
	
	/**
	 * permet de créer un compte utilisateur
	 * @return
	 */
	public String nouvelUtilisateur() {
		
		//1. récup du context de JSF (pour récupérer la session)
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//2. création nouvel utilisateur
		Utilisateur nouvelUtilisateur = new Utilisateur(userName, userPassword, actived);
		setUtilisateur(nouvelUtilisateur);
		
		//3. création dans la bdd
		if(utilisateurService.ajouter(nouvelUtilisateur)) {
			
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nouveau compte : ", " compte utilisateur créé avec succès"));
			
			return "login-utilisateur.xhtml";
			
		}else {
			
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nouveau compte : ", " échec dans la création du compte utilisateur"));
			
			return "creation-utilisateur.xhtml";
		}
		
	}//end nouvelUtilisateur()
	
	/**
	 * permet de modifier un utilisateur dans la bdd 
	 * @param event
	 */
	public String modifierUtilisateur() {
		
		// récup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//modif de la categorie dans la bdd
		if (utilisateurService.modifier(utilisateur)) {
			
			if(utilisateurService.findById(utilisateur.getIdUtilisateur()).getActived()) {	
				
				//envoi d'un msg vers la vue
				FacesMessage messageOK = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification réussie ", " - l'utilisateur a été modifié avec succès");
				
				//envoi du msg
				contextJSF.addMessage(null, messageOK);
				
				return "page-principale-utilisateur.xhtml";			
				
			}else {
				
				//envoi d'un msg vers la vue
				FacesMessage messageOK = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification réussie ", " - l'utilisateur a été modifié avec succès");
				
				//envoi du msg
				contextJSF.addMessage(null, messageOK);
				
				return "page-principale-utilisateur-unactivated.xhtml";	
				
			}
			
		} else {

			//envoi d'un msg vers la vue
			FacesMessage messageNOTOK = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec de la modification ", " - la modification de l'utilisateur a échouée");
			
			//envoi du msg
			contextJSF.addMessage(null, messageNOTOK);
			
		}//end else
		
		//récup de la session
		HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(false);
		
		//sauvegarde du nom de l'utilisateur et mdp dans la session
		session.setAttribute("username", utilisateur.getNomUtilisateur());
		session.setAttribute("userpwd", utilisateur.getPasswordUtilisateur());
		session.setAttribute("user", utilisateur);
		
		return null;
		
	}//end modifierUtilisateur
	
	/**
	 * récupère les infos de l'utilisateur
	 * @return
	 */
	public String infosUtilisateur() {
		
		// récup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//récup de la session 
		HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(false);
		
		//récup utilisateur
		Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("user");
		setUtilisateur(utilisateurConnecte);
		
		return "informations-utilisateur.xhtml";
		
	}//end infosUtilisateur

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

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}//end class
