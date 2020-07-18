package com.intiformation.ecommerce.controller;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.intiformation.ecommerce.modeles.Commande;
import com.intiformation.ecommerce.modeles.LigneDeCommande;
import com.intiformation.ecommerce.modeles.Panier;
import com.intiformation.ecommerce.modeles.Produit;
import com.intiformation.ecommerce.service.ClientServiceImpl;
import com.intiformation.ecommerce.service.CommandeServiceImpl;
import com.intiformation.ecommerce.service.IClientService;
import com.intiformation.ecommerce.service.ICommandeService;
import com.intiformation.ecommerce.service.ILigneDeCommandeService;
import com.intiformation.ecommerce.service.IPanierService;
import com.intiformation.ecommerce.service.IProduitService;
import com.intiformation.ecommerce.service.LigneDeCommandeServiceImpl;
import com.intiformation.ecommerce.service.PanierServiceImpl;
import com.intiformation.ecommerce.service.ProduitServiceImpl;

@ManagedBean(name = "gestionPanier")
@SessionScoped

public class GestionPanierBean implements Serializable {

	private ICommandeService commandeService;
	private IPanierService panierService;
	private ILigneDeCommandeService ligneCommandeService;
	private IClientService clientService;
	private IProduitService produitService;

	private Panier newPanier;
	private Commande newCommande;

	private LigneDeCommande newLigneCommande;
	private List<LigneDeCommande> maListePanier;

	private Produit produitPanier;

	private int quantite;
	private double prixProduit;
	private int idProduit;
	private int idClient;
	private int idPanier;
	private Produit produit = new Produit();


	private Date dateCommande;
	private long millis;

	public GestionPanierBean() {

		commandeService = new CommandeServiceImpl();
		panierService = new PanierServiceImpl();
		ligneCommandeService = new LigneDeCommandeServiceImpl();
		clientService = new ClientServiceImpl();
		produitService = new ProduitServiceImpl();

	}// end ctor

	public void ajouterProduit(ActionEvent event) {

		FacesContext contextJSF = FacesContext.getCurrentInstance();

		UIParameter uip_id_produit = (UIParameter) event.getComponent().findComponent("idProduit");
		UIParameter uip_prix_produit = (UIParameter) event.getComponent().findComponent("prixProduit");

		/*
		 * UIParameter uip_quantite = (UIParameter)
		 * event.getComponent().findComponent("quantiteProduit"); quantite= (int)
		 * uip_quantite.getValue();
		 */

		idProduit = (int) uip_id_produit.getValue();
		prixProduit = (double) uip_prix_produit.getValue();

		if (clientIsConnect()) {

			HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(false);
			idClient = (int) session.getAttribute("id_client");
			if (!panierVide()) {
				
				panierService.ajouter(newPanier);
				idPanier = panierService.getIdLastAdd();
				session.setAttribute("id_panier", idPanier);
			}// end if
			idPanier = (int) session.getAttribute("id_panier");

			newLigneCommande = new LigneDeCommande(quantite, prixProduit, idProduit, idPanier);
			ligneCommandeService.ajouter(newLigneCommande);

		} else {

			HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(false);

			if (session.getAttribute("id_panier") == null) {

				session = (HttpSession) contextJSF.getExternalContext().getSession(true);

				panierService.ajouter(newPanier);
				idPanier = panierService.getIdLastAdd();
				newLigneCommande = new LigneDeCommande(quantite, prixProduit, idProduit, idPanier);
				ligneCommandeService.ajouter(newLigneCommande);
				session.setAttribute("id_panier", idPanier);

			} else {

				idPanier = (int) session.getAttribute("id_panier");
				newLigneCommande = new LigneDeCommande(quantite, prixProduit, idProduit, idPanier);
				ligneCommandeService.ajouter(newLigneCommande);

			} // end else
		} // end else

	}// end ajouter produit

	/**
	 * Retourne true si le client est connecté et false si le client n'est pas
	 * connecté
	 * 
	 * @return
	 */
	public boolean clientIsConnect() {

		FacesContext contexJSF = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) contexJSF.getExternalContext().getSession(false);

		if (session.getAttribute("id_client") != null) {
			return true;
		} else {

			return false;
		}
	}// end clienIsConnect()
	
	
	public boolean panierVide() {
		
		FacesContext contexJSF = FacesContext.getCurrentInstance();

		HttpSession session = (HttpSession) contexJSF.getExternalContext().getSession(false);

		if (session.getAttribute("id_panier") != null) {
			
			return true;
		
		} else {

			return false;
		}
		
	}
	
	

	public int quantitePanier() {
		FacesContext contexJSF = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) contexJSF.getExternalContext().getSession(false);

		if (session.getAttribute("id_panier") != null) {

			idPanier = (int) session.getAttribute("id_panier");

			return ligneCommandeService.nombreLignesParPanier(idPanier);
		} else {

			return 0;
		}

	}// end quantitePanier()

	
	public List<LigneDeCommande> listePanier() {

		FacesContext contexJSF = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) contexJSF.getExternalContext().getSession(false);

		idPanier = (int) session.getAttribute("id_panier");

		maListePanier = ligneCommandeService.getAllByIdPanier(idPanier);

		return maListePanier;

	}//

	public String getPhoto(int pIdProduit) {

		produitPanier = produitService.findById(pIdProduit);

		return produitPanier.getPhoto();

	}// end getPhoto()

	public String getDesignation(int pIdProduit) {

		produitPanier = produitService.findById(pIdProduit);

		return produitPanier.getDesignation();
	}// end getDesignation()

	public String getDescriptif(int pIdProduit) {

		produitPanier = produitService.findById(pIdProduit);

		return produitPanier.getDescription();

	}// end getDescriptif()

	public String deleteLigne(int pIdLigne) {

		ligneCommandeService.supprimerById(pIdLigne);

		return "panier-client.xhtml";

	}

	/**
	 * Retourne le prix du panier
	 * 
	 * @return
	 */
	public double prixTotal() {

		double somme = 0;
		List<LigneDeCommande> maListeLigne = new ArrayList<>();

		FacesContext contexJSF = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) contexJSF.getExternalContext().getSession(false);
		idPanier = (int) session.getAttribute("id_panier");

		maListeLigne = ligneCommandeService.getAllByIdPanier(idPanier);

		for (LigneDeCommande ligneDeCommande : maListeLigne) {

			somme = somme + (ligneDeCommande.getPrix() * ligneDeCommande.getQuantite());
		}

		return somme;
	}// end prixTotal

	/**
	 * Permet de valider la commande en cliquant sur le bouton Passer Commande de la
	 * page panier-client.xhtml
	 * 
	 * @return
	 */
	public String commander() {

		FacesContext contextJSF = FacesContext.getCurrentInstance();

		if (clientIsConnect()) {

			HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(false);
			
			idClient = (int) session.getAttribute("id_client");
			millis = System.currentTimeMillis();
			dateCommande = new Date(millis);
			newCommande = new Commande(dateCommande, idClient);
			
			commandeService.ajouter(newCommande);
			int idCommande = commandeService.getIdByLastAdd();
			
			List<LigneDeCommande> maListeLigne = new ArrayList<>();

			idPanier = (int) session.getAttribute("id_panier");

			maListeLigne = ligneCommandeService.getAllByIdPanier(idPanier);

			for (LigneDeCommande ligneDeCommande : maListeLigne) {

				
				ligneDeCommande.setCommandeID(idCommande);
				ligneCommandeService.modifier(ligneDeCommande);
				
				
				int idProduit = ligneDeCommande.getProduitID();
				int quantiteProduit = ligneDeCommande.getQuantite();
				
				produit = produitService.findById(idProduit);
				
				int quantiteProduitInit = produit.getQuantite();
				
				produit.setQuantite(quantiteProduitInit-quantiteProduit);
				produitService.modifier(produit);
				
			
			}// end for
			
			session.removeAttribute("id_panier");
			
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Commande : ", "Merci pour votre commande"));
			
			return "historique-commande.xhtml";

		} else {

			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation commande : ",
					" Veuillez vous connecter pour valider la commander"));

			return "panier-client.xhtml";
		}

	}// end commander()

	public String refresh() {

		return "panier-client.xhtml";
	}

	//////////////////////////////////// GETTERS/ SETTERS
	//////////////////////////////////// /////////////////////////////////////////////////

	public double getPrixProduit() {
		return prixProduit;
	}

	public void setPrixProduit(double prixProduit) {
		this.prixProduit = prixProduit;
	}

	public int getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

} // end class
