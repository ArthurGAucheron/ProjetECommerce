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

	/*___________ propri�t�s _____________*/
	private String userName;
	private String userPassword;
	private boolean actived;
	
	private Utilisateur utilisateur = null;
	
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
	
	/**
	 * permet de cr�er un compte utilisateur
	 * @return
	 */
	public String nouvelUtilisateur() {
		
		//1. r�cup du context de JSF (pour r�cup�rer la session)
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//2. cr�ation nouvel utilisateur
		Utilisateur nouvelUtilisateur = new Utilisateur(userName, userPassword, actived);
		setUtilisateur(nouvelUtilisateur);
		
		//3. cr�ation dans la bdd
		if(utilisateurService.ajouter(nouvelUtilisateur)) {
			
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nouveau compte : ", " compte utilisateur cr�� avec succ�s"));
			
			return "login-utilisateur.xhtml";
			
		}else {
			
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nouveau compte : ", " �chec dans la cr�ation du compte utilisateur"));
			
			return "creation-utilisateur.xhtml";
		}
		
	}//end nouvelUtilisateur()
	
	/**
	 * permet de modifier un utilisateur dans la bdd 
	 * @param event
	 */
	public String modifierUtilisateur() {
		
		// r�cup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//modif de la categorie dans la bdd
		if (utilisateurService.modifier(utilisateur)) {
			
			if(utilisateurService.findById(utilisateur.getIdUtilisateur()).getActived()) {	
				
				//envoi d'un msg vers la vue
				FacesMessage messageOK = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification r�ussie ", " - l'utilisateur a �t� modifi� avec succ�s");
				
				//envoi du msg
				contextJSF.addMessage(null, messageOK);
				
				return "page-principale-utilisateur.xhtml";			
				
			}else {
				
				//envoi d'un msg vers la vue
				FacesMessage messageOK = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification r�ussie ", " - l'utilisateur a �t� modifi� avec succ�s");
				
				//envoi du msg
				contextJSF.addMessage(null, messageOK);
				
				return "page-principale-utilisateur-unactivated.xhtml";	
				
			}
			
		} else {

			//envoi d'un msg vers la vue
			FacesMessage messageNOTOK = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec de la modification ", " - la modification de l'utilisateur a �chou�e");
			
			//envoi du msg
			contextJSF.addMessage(null, messageNOTOK);
			
		}//end else
		
		//r�cup de la session
		HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(false);
		
		//sauvegarde du nom de l'utilisateur et mdp dans la session
		session.setAttribute("username", utilisateur.getNomUtilisateur());
		session.setAttribute("userpwd", utilisateur.getPasswordUtilisateur());
		session.setAttribute("user", utilisateur);
		
		return null;
		
	}//end modifierUtilisateur
	
	/**
	 * r�cup�re les infos de l'utilisateur
	 * @return
	 */
	public String infosUtilisateur() {
		
		// r�cup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//r�cup de la session 
		HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(false);
		
		//r�cup utilisateur
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
