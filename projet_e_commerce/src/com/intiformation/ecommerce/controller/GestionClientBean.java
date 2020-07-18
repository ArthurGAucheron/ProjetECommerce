package com.intiformation.ecommerce.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;


import com.intiformation.ecommerce.modeles.Client;
import com.intiformation.ecommerce.modeles.Panier;
import com.intiformation.ecommerce.service.ClientServiceImpl;
import com.intiformation.ecommerce.service.IClientService;

import com.intiformation.ecommerce.service.IPanierService;
import com.intiformation.ecommerce.service.PanierServiceImpl;


 @ManagedBean(name="gestionClientBean")
 @SessionScoped
 
/**
 * MAnaged Bean pour le login d'un client
 * @author arthu
 *
 */
public class GestionClientBean implements Serializable{
	
	
	 private String nomClient;
	 private String adresseClient;
	 private String emailClient;
	 private String telClient;
	 private String passWordClient1;
	 private String passWordClient2;
	 private boolean verifAjout = false;
	 private Panier newPanier;
	 private int idClient;
	 private int idPanier;
	
	
	
	private Client client;
	
	private IClientService clientService;
	private IPanierService panierService;
	
	public GestionClientBean() {
	
		clientService = new ClientServiceImpl();
		panierService = new PanierServiceImpl();
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
		
		
		if (clientService.clientExists(emailClient, passWordClient1)==true) {
			
			// -> création de la session 
			
			HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(true);
			
			// sauvegarde de l'id client dans la session
			
			idClient = clientService.findIdByEmail(emailClient);
			session.setAttribute("id_client", idClient);
			
			// création d'un panier au client
			
			
			if (session.getAttribute("id_panier")==null) {
				
				 panierService.ajouter(newPanier);
				 idPanier = panierService.getIdLastAdd();
				 session.setAttribute("id_panier", idPanier);
				 
			}
			
			
	
	
			// -> navigation vers la page principale du site "page-principale.xhtml'
			return "page-principale.xhtml";
			
		}else {
		
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec de connexion "," Identifiant ou mot de passe invalide");
			
			contextJSF.addMessage(null, message);
	
			contextJSF.getExternalContext().getFlash().setKeepMessages(true);	

			return "login-client.xhtml";
		}
		
	}// end connecter client

		
	/**
	 * Permet de créer un nouveau client via le formulaire de la page "creation client"
	 */
	public String nouveauClient(){
		
		
		// 1. Déclaration du contexte de JSF
		
				FacesContext contextJSF = FacesContext.getCurrentInstance();
			
				
				if (passWordClient1.equals(passWordClient2)) {
					
					Client newClient = new Client(nomClient, adresseClient, emailClient, telClient, passWordClient1);
					 
					verifAjout = clientService.ajouter(newClient);
					
			
				}else {
					
					contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inscription : ", "Les mots de passe doivent être identique"));
					contextJSF.getExternalContext().getFlash().setKeepMessages(true);	
				
				}// end else
				
				
				
				if (verifAjout) {
					
					contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Inscription : ", " merci pour votre inscription"));
					contextJSF.getExternalContext().getFlash().setKeepMessages(true);	

					return "login-client.xhtml?faces-redirect=true";
					
				}else {
					
					contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inscription : ", " erreur lors de l'inscription"));
					contextJSF.getExternalContext().getFlash().setKeepMessages(true);	

					return "creation-client.xhtml?faces-redirect=true";
				}// end else
		
		
	}// end nouveauClient()
	
		public String deconnecterClient() {
		
		// 1. Récup du context de JSF
		FacesContext contexJSF = FacesContext.getCurrentInstance();
		
		// 2. Récup de la session http de l'utilisateur
		HttpSession session = (HttpSession) contexJSF.getExternalContext().getSession(false);
		
		// 3. déconnexion
		session.invalidate();
		
		// 4. Message de déconnexion vers la vue
		FacesMessage messageDeconnexion = new FacesMessage(FacesMessage.SEVERITY_INFO, "Déconnexion", " vous êtes maintenant déconnecté");
		contexJSF.addMessage(null, messageDeconnexion);
		
		// 5. redirection vers la page du formulaire 'login.xhtml'
		
		return "page-principale.xhtml?faces-redirect=true";
	}
	
	
	public String getEmailClient() {
		return emailClient;
	}

	public void setEmailClient(String emailClient) {
		this.emailClient = emailClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getAdresseClient() {
		return adresseClient;
	}

	public void setAdresseClient(String adresseClient) {
		this.adresseClient = adresseClient;
	}


	public String getTelClient() {
		return telClient;
	}

	public void setTelClient(String telClient) {
		this.telClient = telClient;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getPassWordClient1() {
		return passWordClient1;
	}

	public void setPassWordClient1(String passWordClient1) {
		this.passWordClient1 = passWordClient1;
	}

	public String getPassWordClient2() {
		return passWordClient2;
	}

	public void setPassWordClient2(String passWordClient2) {
		this.passWordClient2 = passWordClient2;
	}

	public boolean isVerifAjout() {
		return verifAjout;
	}

	public void setVerifAjout(boolean verifAjout) {
		this.verifAjout = verifAjout;
	}

	
	

}// end class
