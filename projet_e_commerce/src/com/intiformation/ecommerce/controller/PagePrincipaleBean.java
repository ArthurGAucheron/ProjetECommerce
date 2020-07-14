package com.intiformation.ecommerce.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.intiformation.ecommerce.modeles.Categorie;
import com.intiformation.ecommerce.modeles.Produit;
import com.intiformation.ecommerce.service.CategorieServiceImpl;
import com.intiformation.ecommerce.service.ICategorieService;
import com.intiformation.ecommerce.service.IProduitService;
import com.intiformation.ecommerce.service.ProduitServiceImpl;

@ManagedBean(name = "pagePrincipaleBean")
@SessionScoped

public class PagePrincipaleBean implements Serializable {

	private String nomCategorie = "Toutes les cat�gories";
	private ICategorieService categorieService;
	private List<Categorie> listeCategorie;

	private IProduitService produitService;
	private List<Produit> listeProduits;

	private int nombreProduit = 0;

	private String motCle = null;

	public PagePrincipaleBean() {

		categorieService = new CategorieServiceImpl();

		produitService = new ProduitServiceImpl();

	}// end ctor

	/**
	 * Permet de cr�er la liste d�roulante pour rechercher dans une cat�gorie
	 * sp�cifique (voir header page principale)
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

		listeNomCategorie.add("Toutes les cat�gories");

		return listeNomCategorie;

	}// end choixCategorie

	public List<Produit> listeProduits(String nomCategorie, String motCle) {

		FacesContext contextJSF = FacesContext.getCurrentInstance();

		listeProduits = new ArrayList<>();

		if ((motCle.isEmpty() || motCle.equals(""))) {

			if (nomCategorie.equals("Toutes les cat�gories") || nomCategorie.isEmpty()) {

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
	 * Permet d'alimenter la liste d�roulante sur la quantite du produit disponible
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

	/////////////////////////////// GETTERS/SETTERS
	/////////////////////////////// ////////////////////////////////////////////
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

}// end class