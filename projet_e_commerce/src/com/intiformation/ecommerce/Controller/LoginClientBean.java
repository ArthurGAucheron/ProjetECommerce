package com.intiformation.ecommerce.Controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


import com.intiformation.ecommerce.service.ClientServiceImpl;
import com.intiformation.ecommerce.service.IClientService;

 @ManagedBean(name="loginClientBean")
 @SessionScoped
 
/**
 * MAnaged Bean pour le login d'un client
 * @author arthu
 *
 */
public class LoginClientBean implements Serializable{
	
	
	private String emailClient;
	private String passWordClient;
	
	private IClientService clientService;
	
	public LoginClientBean() {
	
		clientService = new ClientServiceImpl();
	}// end LoginClientBean
	
	/**
	 * Permet au client de se connecter au site web et de créer une session.
	 * Aprés la connexion le client est redirigé vers la page principale du site web.
	 * @return
	 */
	
	public String connecterClient() {
		
		// 1. Déclaration du contexte de JSF
		
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		// 2. vérif si l'utilisateur est dans la bdd
		
		
		if (clientService.clientExists(emailClient, passWordClient)==true) {
			
			// -> création de la session 
			
			HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(true);
			
			// sauvegarde de l'adresse mail dans la session
			session.setAttribute("client_email", emailClient);
			
	
			// -> navigation vers la page principale du site "page-principale.xhtml'
			return "page-principale.xhtml";
			
		}else {
		
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec de connexion "," Identifiant ou mot de passe invalide");
			
			
			contextJSF.addMessage(null, message);
	
			return "login-client.xhtml";
		}
		
	}// end connecter client

	public String getEmailClient() {
		return emailClient;
	}

	public void setEmailClient(String emailClient) {
		this.emailClient = emailClient;
	}

	public String getPassWordClient() {
		return passWordClient;
	}

	public void setPassWordClient(String passWordClient) {
		this.passWordClient = passWordClient;
	}
	
	

}// end class
