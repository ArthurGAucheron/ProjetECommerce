package com.intiformation.ecommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

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

@ManagedBean(name="gestionPanier")
@SessionScoped

public class GestionPanierBean implements Serializable {

	private ICommandeService commandeService;
	private IPanierService panierService;
	private ILigneDeCommandeService ligneCommandeService;
	private IClientService clientService;
	private IProduitService produitService;
	
	private Panier newPanier;
	
	private LigneDeCommande newLigneCommande;
	private List<LigneDeCommande> maListePanier; 
	
	private Produit produitPanier;
	
	private int quantite;
	private double prixProduit;
	private int idProduit;
	private int idClient;
	private int idPanier;
	
	public GestionPanierBean() {
		
		commandeService= new CommandeServiceImpl();
		panierService = new PanierServiceImpl();
		ligneCommandeService = new LigneDeCommandeServiceImpl();
		clientService = new ClientServiceImpl();
		produitService = new ProduitServiceImpl();
			
	}// end ctor
	
	public void ajouterProduit(ActionEvent event) {
		
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		UIParameter uip_id_produit = (UIParameter) event.getComponent().findComponent("idProduit");
		UIParameter uip_prix_produit = (UIParameter) event.getComponent().findComponent("prixProduit");
		idProduit = (int) uip_id_produit.getValue();
		prixProduit = (double) uip_prix_produit.getValue();

		if (clientIsConnect()) {
				
			HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(false);
			idClient = (int) session.getAttribute("id_client");
			idPanier = (int) session.getAttribute("id_panier");
			
			newLigneCommande = new LigneDeCommande(quantite, prixProduit, idProduit, idPanier);
			ligneCommandeService.ajouter(newLigneCommande);
		
		}else {
			
			HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(false);
			
			if (session.getAttribute("id_panier") == null) {
				
				 session = (HttpSession) contextJSF.getExternalContext().getSession(true);
				 
				 panierService.ajouter(newPanier);
				 idPanier = panierService.getIdLastAdd();
				 newLigneCommande = new LigneDeCommande(quantite, prixProduit, idProduit, idPanier);
				 ligneCommandeService.ajouter(newLigneCommande);
				 session.setAttribute("id_panier", idPanier);
				 
			} else {

				idPanier= (int) session.getAttribute("id_panier");
				newLigneCommande = new LigneDeCommande(quantite, prixProduit, idProduit, idPanier);
				ligneCommandeService.ajouter(newLigneCommande);
			
			}// end else
		}//end else
		
		System.out.println("Quantité demander " + quantite);
		
	}//end ajouter produit


	
	/**
	 * Retourne true si le client est connecté et false si le client n'est pas connecté
	 * @return
	 */
	public boolean clientIsConnect() {
		
		
		FacesContext contexJSF = FacesContext.getCurrentInstance();
		
		HttpSession session = (HttpSession) contexJSF.getExternalContext().getSession(false);

		
		if (session.getAttribute("id_client") !=null) {
			return true;
		}else {
			
			return false;
		}
	}// end clienIsConnect()
	
	
	public int quantitePanier() {
		FacesContext contexJSF = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) contexJSF.getExternalContext().getSession(false);
		
		if (session.getAttribute("id_panier")!=null) {
			
			idPanier = (int) session.getAttribute("id_panier");
			
			return ligneCommandeService.nombreLignesParPanier(idPanier);
		}else {
			
			return 0;
		}
	
			
		
		
		
		
		
	}// end quantitePanier()
	
	public List<LigneDeCommande> listePanier(){
		
		FacesContext contexJSF = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) contexJSF.getExternalContext().getSession(false);
	
		
		
		idPanier = (int) session.getAttribute("id_panier");
		
		maListePanier = ligneCommandeService.getAllByIdPanier(idPanier);
		
		return maListePanier;
		
	}// 
	
	public String getPhoto(int pIdProduit){
		
		produitPanier = produitService.findById(pIdProduit);
		
		return produitPanier.getPhoto();
		
	}// end getPhoto()
	
	public String getDesignation(int pIdProduit) {
		
		produitPanier = produitService.findById(pIdProduit);
		
		return produitPanier.getDesignation();
	}//  end getDesignation()
	
	
	public String getDescriptif(int pIdProduit) {
		
		produitPanier = produitService.findById(pIdProduit);
		
		return produitPanier.getDescription();
		
	}// end getDescriptif()
	
	public String deleteLigne(int pIdLigne) {
		
		
		ligneCommandeService.supprimerById(pIdLigne);
		
	
		
		return "panier-client.xhtml";
		
	}
	
	//////////////////////////////////// GETTERS/ SETTERS /////////////////////////////////////////////////
	
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
