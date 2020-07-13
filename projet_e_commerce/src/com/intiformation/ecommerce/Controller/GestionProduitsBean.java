package com.intiformation.ecommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.intiformation.ecommerce.modeles.Categorie;
import com.intiformation.ecommerce.modeles.Produit;
import com.intiformation.ecommerce.service.CategorieServiceImpl;
import com.intiformation.ecommerce.service.ICategorieService;
import com.intiformation.ecommerce.service.IProduitService;
import com.intiformation.ecommerce.service.ProduitServiceImpl;

/**
 * managed bean pour la gestion des produits
 * @author marle
 *
 */
@ManagedBean(name="gestionProduitsBean")
@SessionScoped
public class GestionProduitsBean implements Serializable{

	/*___________ propriétés _____________*/
	private Produit produit;
	private List<Produit> listeProduits;
	private int idCat;
	
	//déclaration de la couche service
	private IProduitService produitService;
		
	/*___________ ctors _____________*/

	/**
	 * ctor vide pour instanciation du MB par le Serveur
	 */
	public GestionProduitsBean() {
		produitService = new ProduitServiceImpl();
	}//end ctor
	
	/*___________ méthodes _____________*/
	
	/**
	 * permet d'afficher la liste des produits par catégorie
	 * @return
	 */
	public void afficherProduitsParCategorie(ActionEvent event){
		
		//1. récup du paramètre passé dans le composant au click sur la catégorie
		UIParameter uip = (UIParameter) event.getComponent().findComponent("idCat");
						
		//2. récup de la valeur du parmaètre
		int categorieID = (int) uip.getValue();
						
		//3. récupération des produits de la categorie dans la bdd via l'id
							
			//récup contexte JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
							
			//recup des produits
		List<Produit> listeProduitsParCategorie = produitService.findProduitsByIDCategorie(categorieID);
		System.out.println("ID catégorie = " + categorieID);
		listeProduits.addAll(listeProduitsParCategorie);
		System.out.println("listeProduits = " + listeProduits);					
		
			//redirection vers gestion-produits-utilisateur.xhtml (réf : les clés d'outcom dans faces-config.xml)
									
	}//end afficherProduitsParCategorie
	
	/**
	 * permet d'afficher la liste des produits
	 * @return
	 */
	public List<Produit> afficherProduits(){
							
		//recup des produits
		listeProduits = produitService.findAll();

		return listeProduits;
											
	}//end afficherProduits
	
	/**
	 * permet d'initialiser un nouvel objet produit
	 * invoquée au click sur le bouton 'ajouter' de 'gestion-produits-utilisateur.xhtml' 
	 * @return
	 */
	public String initialiserProduit() {
		
		//instanciation nouvel objet produit
		Produit produitToAdd = new Produit();
		
		//affectation du produit à ajouter à la variable produit du MB -> va réceptionner infos envoyées depuis formulaire d'ajout
		setProduit(produitToAdd);
		
		return "ajout-produit-utilisateur.xhtml";
		
	}//end initialiserCategorie()
	
	/**
	 * permet d'ajouter un nouveau produit dans la bdd
	 * méthode invoquée au click sur le bouton 'enregistrer' de 'ajout-produit-utilisateur.xhtml'
	 * @return
	 */
	public String ajouterProduit() {
		
		//récup du contexte de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
				
		//ajout du produit dans la bdd via la couche service
		if(produitService.ajouter(produit)) {
			
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ajout produit ", " - le nouveau produit a été ajouté avec succès"));

			return "gestion-produits-utilisateur.xhtml";
					
		} else {
					
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec ajout produit ", " - l'ajout du produit a échoué"));
			return "ajout-produit-utilisateur.xhtml";
	
		}//end else
			
	}//end ajouterProduit
	
	/**
	 * permet de supprimer un produit dans la bdd
	 * méthode invoquée au click sur le bouton 'supprimer' de 'gestion-produits-utilisateur.xhtml'
	 * @return
	 */
	public void supprimerProduit(ActionEvent event) {
		
		// 1. récup du paramètre passé dans le composant au click sur le lien 'supprimer'
		UIParameter uip = (UIParameter) event.getComponent().findComponent("deleteID");
				
		//2. récup de la valeur du parmaètre
		int produitID = (int) uip.getValue();
				
		//3. suppression du produit dans la bdd via l'id
					
			//récup contexte JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
					
			//suppression produit
		if (produitService.supprimerById(produitID)) {
					
			//envoi d'un msg vers la vue
		contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Suppression produit ", " - le produit a été supprimé"));
					
			//redirection vers gestion-categories-utilisateur.xhtml (réf : les clés d'outcom dans faces-config.xml)
						
		} else {

			//envoi d'un msg vers la vue
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec suppression produit ", " - la suppression du produit a échouée"));
					
			//redirection vers gestion-produits-utilisateur.xhtml (réf : les clés d'outcom dans faces-config.xml)
					
		}//end else
					
	}//end supprimerProduit
	
	/**
	 * permet d'éditer un produit dans la bdd 
	 * invoquée au click sur le lien 'modifier' de 'gestion-produits-utilisateur.xhtml' 
	 */
	public void selectionnerProduit(ActionEvent event){
		
		//récup du paramètre passé dans le composant au click
		UIParameter uip = (UIParameter) event.getComponent().findComponent("updateID");
		
		//récup de la valeur du paramètre
		int produitID = (int) uip.getValue();
		
		//recup du produit à editer via l'id dans la bdd
		Produit produitToUpdate = produitService.findById(produitID);
		
		//affectation du produit à éditer à la variable categorie du MB
		setProduit(produitToUpdate);
		
		//redirection vers la page d'édition 'modification-produit-utilisateur.xhtml' (ref : clé d'outcum dans faces-config.xml)
		
	}//end selectionnerProduit
	
	/**
	 * permet de modifier un produit dans la bdd 
	 * invoquée au click sur le bouton 'modifier' de 'modification-produit-utilisateur.xhtml'
	 * @param event
	 */
	public String modifierProduit() {
		
		// récup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//modif du produit dans la bdd
		if (produitService.modifier(produit)) {
			
			//envoi d'un msg vers la vue
			FacesMessage messageOK = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification réussie ", " - le produit a été modifié avec succès");
			
			//envoi du msg
			contextJSF.addMessage(null, messageOK);
			
		} else {

			//envoi d'un msg vers la vue
			FacesMessage messageNOTOK = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec de la modification ", " - la modification du produit a échouée");
			
			//envoi du msg
			contextJSF.addMessage(null, messageNOTOK);
			
		}//end else
		
		//redirection vers gestion-categories-utilisateur.xhtml
		return "gestion-produits-utilisateur.xhtml";
		
	}//end modifierProduit	

	/*___________ getters/setters _____________*/

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public int getIdCat() {
		return idCat;
	}

	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}
	
}//end class
