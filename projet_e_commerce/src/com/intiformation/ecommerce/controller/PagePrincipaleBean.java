package com.intiformation.ecommerce.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.intiformation.ecommerce.modeles.Categorie;
import com.intiformation.ecommerce.service.CategorieServiceImpl;
import com.intiformation.ecommerce.service.ICategorieService;

@ManagedBean(name="pagePrincipaleBean")
@SessionScoped

public class PagePrincipaleBean implements Serializable{

	private String nomCategorie = "Toutes les catégories";
	
	private ICategorieService categorieService;
	
	private List<Categorie> listeCategorie ;
	
	
	public PagePrincipaleBean() {
		
		categorieService = new CategorieServiceImpl();
	
	}//end ctor

	/**
	 * Permet de créer la liste déroulante pour rechercher dans une catégorie spécifique (voir header page principale)
	 * @return
	 */
	
	public List<String> choixCategorie() {
		
		listeCategorie = new ArrayList<>();
		
		listeCategorie = categorieService.findAll();
		
		List<String> listeNomCategorie = new ArrayList<>();
		
		listeNomCategorie.add(nomCategorie);
		
		for (Categorie categorie : listeCategorie) {
			
			listeNomCategorie.add(categorie.getNomCategorie());
		
		}// end for
		
		return listeNomCategorie;
		
	}// end choixCategorie

	
/////////////////////////////// GETTERS/SETTERS ////////////////////////////////////////////
	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	
	
	
	
}// end class
