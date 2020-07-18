package com.intiformation.ecommerce.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.intiformation.ecommerce.modeles.Categorie;
import com.intiformation.ecommerce.modeles.Client;
import com.intiformation.ecommerce.modeles.Produit;
import com.intiformation.ecommerce.service.CategorieServiceImpl;
import com.intiformation.ecommerce.service.ClientServiceImpl;
import com.intiformation.ecommerce.service.ICategorieService;
import com.intiformation.ecommerce.service.IClientService;
import com.intiformation.ecommerce.service.IProduitService;
import com.intiformation.ecommerce.service.ProduitServiceImpl;

@ManagedBean(name = "pagePrincipaleBean")
@SessionScoped

public class PagePrincipaleBean implements Serializable {

	private String nomCategorie = "Toutes les catégories";
	private ICategorieService categorieService;
	private List<Categorie> listeCategorie;

	private IProduitService produitService;
	private List<Produit> listeProduits;
	
	private IClientService clientService;

	private int nombreProduit;
	

	private String motCle = null;

	private boolean etatSession;
	
	public PagePrincipaleBean() {

		categorieService = new CategorieServiceImpl();

		produitService = new ProduitServiceImpl();
		
		clientService = new  ClientServiceImpl();
		

	}// end ctor

	/**
	 * Permet de créer la liste déroulante pour rechercher dans une catégorie
	 * spécifique (voir header page principale)
	 * 
	 * @return
	 */

	public List<String> choixCategorie() {

		listeCategorie = new ArrayList<>();

		listeCategorie = categorieService.findAll();

		List<String> listeNomCategorie = new ArrayList<>();

		listeNomCategorie.add(nomCategorie);

		for (Categorie categorie : listeCategorie) {

			listeNomCategorie.add(categorie.getNomCategorie());
		

		} // end for

		listeNomCategorie.add("Toutes les catégories");

		return listeNomCategorie;

	}// end choixCategorie

	public List<Produit> listeProduits(String nomCategorie, String motCle) {

		

		listeProduits = new ArrayList<>();

		if ((motCle.isEmpty() || motCle.equals(""))) {

			if (nomCategorie.equals("Toutes les catégories") || nomCategorie.isEmpty()) {

				listeProduits = produitService.findAll();

			
			
			} else {

				int idCategorie = categorieService.getIdByName(nomCategorie);

				
				
				listeProduits = produitService.findProduitsByIDCategorie(idCategorie);

				
				
			} // end else

		}else {
			
			listeProduits=produitService.findProduitsByMotCle(motCle);
			
		}

		return listeProduits;

	}// end listeProduits()

	/**
	 * Permet d'alimenter la liste déroulante sur la quantite du produit disponible
	 * 
	 * @param maxDispo
	 * @return
	 */
	public List<Integer> nombreDisponibleProduit(int maxDispo) {

		List<Integer> listeNombreDisponibleProd = new ArrayList<>();

		for (int i = 1; i <= maxDispo; i++) {

			listeNombreDisponibleProd.add(i);

		} // end for

		return listeNombreDisponibleProd;

	}// end nombreDisponibleProduit

	public String refresh() {

		return "page-principale.xhtml?faces-redirect=true";

	}// end refresh
	
	public boolean clientIsConnect() {
		
	
		FacesContext contexJSF = FacesContext.getCurrentInstance();
		
		HttpSession session = (HttpSession) contexJSF.getExternalContext().getSession(false);

		
		if (session.getAttribute("id_client") !=null) {
			return true;
		}else {
			
			return false;
		}
	}// end clienIsConnect()
	
	
	public String infosClient() {
		
        FacesContext contexJSF = FacesContext.getCurrentInstance();
		
		HttpSession session = (HttpSession) contexJSF.getExternalContext().getSession(false);
		
		int idClient = (int) session.getAttribute("id_client");
		
		return clientService.findById(idClient).getNomClient();
	}
		
	
		
	

	
	
	
	/////////////////////////////// GETTERS/SETTERS	/////////////////////////////// ////////////////////////////////////////////
	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public int getNombreProduit() {
		return nombreProduit;
	}

	public void setNombreProduit(int nombreProduit) {
		this.nombreProduit = nombreProduit;
	}

	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}


	public boolean isEtatSession() {
		return etatSession;
	}

	public void setEtatSession(boolean etatSession) {
		this.etatSession = etatSession;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	
	

}// end class
