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
import com.intiformation.ecommerce.service.CategorieServiceImpl;
import com.intiformation.ecommerce.service.ICategorieService;

/**
 * managed bean pour la gesion des cat�gories
 * @author marle
 *
 */
@ManagedBean(name="gestionCategoriesBean")
@SessionScoped
public class GestionCategoriesBean implements Serializable{

	/*___________ propri�t�s _____________*/
	private Categorie categorie;
	private List<Categorie> listeCategories;
	
	//d�claration de la DAO
	private ICategorieService categorieService;
		
	/*___________ ctors _____________*/

	/**
	 * ctor vide pour instanciation du MB par le Serveur
	 */
	public GestionCategoriesBean() {
		categorieService = new CategorieServiceImpl();
	}//end ctor
	
	/*___________ m�thodes _____________*/
	/**
	 * permet de r�cup�rer et afficher la liste de toutes les cat�gories de la bdd
	 * @return
	 */
	public List<Categorie> afficherCategories() {
		
		listeCategories = categorieService.findAll();		
		return listeCategories;
			
	}//end afficher()
	
	/**
	 * permet d'initialiser un nouvel objet categorie
	 * invoqu�e au click sur le bouton 'ajouter' de 'gestion-categories-utilisateur.xhtml' 
	 * @return
	 */
	public String initialiserCategorie() {
		
		//instanciation nouvel objet categorie
		Categorie categorieToAdd = new Categorie();
		
		//affectation de la cat�gorie � ajouter � la variable categorie du MB -> va r�ceptionner infos envoy�es depuis formulaire d'ajout
		setCategorie(categorieToAdd);
		
		return "ajout-categorie-utilisateur.xhtml";
		
	}//end initialiserCategorie()

	/**
	 * permet d'ajouter une nouvelle cat�gorie dans la bdd
	 * m�thode invoqu�e au click sur le bouton 'ajouter' de 'ajout-categorie-utilisateur.xhtml'
	 * @return
	 */
	public String ajouterCategorie() {
		
		//r�cup du contexte de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
				
		//ajout de la cat�gorie dans la bdd via la couche service
		if(categorieService.ajouter(categorie)) {
			
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ajout cat�gorie ", " - la nouvelle cat�gorie a �t� ajout�e avec succ�s"));

			return "gestion-categories-utilisateur.xhtml";
					
		} else {
					
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec ajout cat�gorie ", " - l'ajout de la cat�gorie a �chou�"));
			return "ajouter-livre.xhtml";
	
		}//end else
			
	}//end ajouterCategorie
	
	/**
	 * permet de supprimer une cat�gorie dans la bdd
	 * m�thode invoqu�e au click sur le bouton 'supprimer' de 'gestion-categories-utilisateur.xhtml'
	 * @return
	 */
	public void supprimerCategorie(ActionEvent event) {
		
		// 1. r�cup du param�tre pass� dans le composant au click sur le lien 'supprimer'
		UIParameter uip = (UIParameter) event.getComponent().findComponent("deleteID");
				
		//2. r�cup de la valeur du parma�tre
		int categorieID = (int) uip.getValue();
				
		//3. suppression de la categorie dans la bdd via l'id
					
			//r�cup contexte JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
					
			//suppression categorie
		if (categorieService.supprimerById(categorieID)) {
					
			//envoi d'un msg vers la vue
		contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Suppression cat�gorie ", " - la cat�gorie a �t� supprim�e"));
					
			//redirection vers gestion-categories-utilisateur.xhtml (r�f : les cl�s d'outcom dans faces-config.xml)
						
		} else {

			//envoi d'un msg vers la vue
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec suppression cat�gorie ", " - la suppression de la cat�gorie a �chou�e"));
					
			//redirection vers gestion-categories-utilisateur.xhtml (r�f : les cl�s d'outcom dans faces-config.xml)
					
		}//end else
					
	}//end supprimerCategorie
	
	/**
	 * permet d'�diter une categorie dans la bdd 
	 * invoqu�e au click sur le lien 'modifier' de 'gestion-categories-utilisateur.xhtml' 
	 */
	public void selectionnerCategorie(ActionEvent event){
		
		//r�cup du param�tre pass� dans le composant au click
		UIParameter uip = (UIParameter) event.getComponent().findComponent("updateID");
		
		//r�cup de la valeur du param�tre
		int categorieID = (int) uip.getValue();
		
		//recup de la cat�gorie � editer via l'id dans la bdd
		Categorie categorieToUpdate = categorieService.findById(categorieID);
		
		//affectation de la cat�gorie � �diter � la variable categorie du MB
		setCategorie(categorieToUpdate);
		
		//redirection vers la page d'�dition 'modification-categorie-utilisateur.xhtml' (ref : cl� d'outcum dans faces-config.xml)
		
	}//end selectionnerCategorie
	
	/**
	 * permet de modifier une categorie dans la bdd 
	 * invoqu�e au click sur le bouton 'modifier' de 'modification-categorie-utilisateur.xhtml'
	 * @param event
	 */
	public String modifierCategorie() {
		
		// r�cup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//modif de la categorie dans la bdd
		if (categorieService.modifier(categorie)) {
			
			//envoi d'un msg vers la vue
			FacesMessage messageOK = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification r�ussie ", " - la cat�gorie a �t� modifi�e avec succ�s");
			
			//envoi du msg
			contextJSF.addMessage(null, messageOK);
			
		} else {

			//envoi d'un msg vers la vue
			FacesMessage messageNOTOK = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec de la modification ", " - la modification de la cat�gorie a �chou�e");
			
			//envoi du msg
			contextJSF.addMessage(null, messageNOTOK);
			
		}//end else
		
		//redirection vers gestion-categories-utilisateur.xhtml
		return "gestion-categories-utilisateur.xhtml";
		
	}//end modifierCategorie	
	
	/*___________ getters/setters _____________*/
	
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Categorie> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}
	
}//end class
