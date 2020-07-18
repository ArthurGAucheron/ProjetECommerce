package com.intiformation.ecommerce.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.intiformation.ecommerce.modeles.Commande;
import com.intiformation.ecommerce.modeles.LigneDeCommande;
import com.intiformation.ecommerce.service.CommandeServiceImpl;
import com.intiformation.ecommerce.service.ICommandeService;
import com.intiformation.ecommerce.service.ILigneDeCommandeService;
import com.intiformation.ecommerce.service.LigneDeCommandeServiceImpl;

@ManagedBean(name="gestionCommande")
@SessionScoped


public class GestionCommandeBean implements Serializable{

	private ICommandeService commandeService;
	private ILigneDeCommandeService ligneCommandeService;
	
	private List<Commande> commandesClientListe;
	private List<LigneDeCommande> ligneDeCommandeListe;
	
	
	
	private double prixCommande;
	

	public GestionCommandeBean() {
		
		commandeService = new CommandeServiceImpl();
		ligneCommandeService = new LigneDeCommandeServiceImpl();
	
	}// end ctor
	
	public List<Commande> commandesClient(){
		
		
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) contextJSF.getExternalContext().getSession(false);
		int idCient = (int) session.getAttribute("id_client");
		
		commandesClientListe = new ArrayList<>();
		commandesClientListe = commandeService.getAllByIdCLient(idCient);

	
		
		
		
		
		
		return commandesClientListe;
		
	}// end commandesClient()

	
	public double prixCommandes(int pIdCommande) {
		
		 double somme =0;
		
		ligneDeCommandeListe = new ArrayList<>();
		
			ligneDeCommandeListe = ligneCommandeService.getAllByIdCommande(pIdCommande);
			
			for (LigneDeCommande ligneCommande : ligneDeCommandeListe) {
				
				
				somme= somme+ (ligneCommande.getPrix()*ligneCommande.getQuantite());
				
			}
			
			return somme;
		
	}
	
	
	public double getPrixCommande() {
		return prixCommande;
	}

	public void setPrixCommande(double prixCommande) {
		this.prixCommande = prixCommande;
	}

	

	
	
	
}// end class
