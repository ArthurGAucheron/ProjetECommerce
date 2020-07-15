package com.intiformation.ecommerce.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

import com.intiformation.ecommerce.modeles.Produit;
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

	/*___________ propri�t�s _____________*/
	private Produit produit;
	private List<Produit> listeProduits;
	private int idCat;
	
    // file upload de l'API servlet
	private Part uploadedFile;
	
	//liste filtr�e du tableau
	private List<Produit> filteredProduits;
	
	//d�claration de la couche service
	private IProduitService produitService;
		
	/*___________ ctors _____________*/

	/**
	 * ctor vide pour instanciation du MB par le Serveur
	 */
	public GestionProduitsBean() {
		produitService = new ProduitServiceImpl();
	}//end ctor
	
	/*___________ m�thodes _____________*/
	
	/**
	 * permet d'afficher la liste des produits par cat�gorie
	 * @return
	 */
	public void afficherProduitsParCategorie(ActionEvent event){
		
		//1. r�cup du param�tre pass� dans le composant au click sur la cat�gorie
		UIParameter uip = (UIParameter) event.getComponent().findComponent("idCat");
						
		//2. r�cup de la valeur du parma�tre
		int categorieID = (int) uip.getValue();
						
		//3. r�cup�ration des produits de la categorie dans la bdd via l'id
							
			//r�cup contexte JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
							
			//recup des produits
		List<Produit> listeProduitsParCategorie = produitService.findProduitsByIDCategorie(categorieID);
		System.out.println("ID cat�gorie = " + categorieID);
		listeProduits.addAll(listeProduitsParCategorie);
		System.out.println("listeProduits = " + listeProduits);					
		
			//redirection vers gestion-produits-utilisateur.xhtml (r�f : les cl�s d'outcom dans faces-config.xml)
									
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
	 * invoqu�e au click sur le bouton 'ajouter' de 'gestion-produits-utilisateur.xhtml' 
	 * @return
	 */
	public String initialiserProduit() {
		
		//instanciation nouvel objet produit
		Produit produitToAdd = new Produit();
		
		//affectation du produit � ajouter � la variable produit du MB -> va r�ceptionner infos envoy�es depuis formulaire d'ajout
		setProduit(produitToAdd);
		
		return "ajout-produit-utilisateur.xhtml";
		
	}//end initialiserCategorie()
	
	/**
	 * permet d'ajouter un nouveau produit dans la bdd
	 * m�thode invoqu�e au click sur le bouton 'enregistrer' de 'ajout-produit-utilisateur.xhtml'
	 * @return
	 */
	public String ajouterProduit() {
		
		//r�cup du contexte de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		 // traitement du fileUpload : recup du nom de l'image
        String fileName = uploadedFile.getSubmittedFileName();
        
        // affectation du nom � la prop photo du produit
        produit.setPhoto(fileName);
				
		//ajout du produit dans la bdd via la couche service
		if(produitService.ajouter(produit)) {
			
			 //----------------------------------------------
            // ajout de la photo dans le dossier images produits
            //-----------------------------------------------
            
			try {
            // recup du contenu de l'image
            InputStream imageContent = uploadedFile.getInputStream();

            // recup de la valeur du param d'initialisation context-param de web.xml
            FacesContext fContext = FacesContext.getCurrentInstance();
            String pathTmp = fContext.getExternalContext().getInitParameter("file-upload-produit");
            
            String filePath = fContext.getExternalContext().getRealPath(pathTmp);

            // cr�ation du fichier image (conteneur de l'image) 
            File targetFile = new File(filePath, fileName);

            // instanciation du flux de sortie vers le fichier image
            OutputStream outStream = new FileOutputStream(targetFile);
			
            byte[] buf = new byte[1024];
            int len;

            while ((len = imageContent.read(buf)) > 0) {
                outStream.write(buf, 0, len);
            }
            
            outStream.close();
            
            } catch (IOException ex) {
            	System.out.println("erreur chargement photo");
            }

			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ajout produit ", " - le nouveau produit a �t� ajout� avec succ�s"));

			return "gestion-produits-utilisateur.xhtml";
					
		} else {
					
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec ajout produit ", " - l'ajout du produit a �chou�"));
			return "ajout-produit-utilisateur.xhtml";
	
		}//end else
			
	}//end ajouterProduit
	
	/**
	 * permet de supprimer un produit dans la bdd
	 * m�thode invoqu�e au click sur le bouton 'supprimer' de 'gestion-produits-utilisateur.xhtml'
	 * @return
	 */
	public void supprimerProduit(ActionEvent event) {
		
		// 1. r�cup du param�tre pass� dans le composant au click sur le lien 'supprimer'
		UIParameter uip = (UIParameter) event.getComponent().findComponent("deleteID");
				
		//2. r�cup de la valeur du parma�tre
		int produitID = (int) uip.getValue();
				
		//3. suppression du produit dans la bdd via l'id
					
			//r�cup contexte JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
					
			//suppression produit
		if (produitService.supprimerById(produitID)) {
					
			//envoi d'un msg vers la vue
		contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Suppression produit ", " - le produit a �t� supprim�"));
					
			//redirection vers gestion-categories-utilisateur.xhtml (r�f : les cl�s d'outcom dans faces-config.xml)
						
		} else {

			//envoi d'un msg vers la vue
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec suppression produit ", " - la suppression du produit a �chou�e"));
					
			//redirection vers gestion-produits-utilisateur.xhtml (r�f : les cl�s d'outcom dans faces-config.xml)
					
		}//end else
					
	}//end supprimerProduit
	
	/**
	 * permet d'�diter un produit dans la bdd 
	 * invoqu�e au click sur le lien 'modifier' de 'gestion-produits-utilisateur.xhtml' 
	 */
	public void selectionnerProduit(ActionEvent event){
		
		//r�cup du param�tre pass� dans le composant au click
		UIParameter uip = (UIParameter) event.getComponent().findComponent("updateID");
		
		//r�cup de la valeur du param�tre
		int produitID = (int) uip.getValue();
		
		//recup du produit � editer via l'id dans la bdd
		Produit produitToUpdate = produitService.findById(produitID);
		
		//affectation du produit � �diter � la variable categorie du MB
		setProduit(produitToUpdate);
		
		//redirection vers la page d'�dition 'modification-produit-utilisateur.xhtml' (ref : cl� d'outcum dans faces-config.xml)
		
	}//end selectionnerProduit
	
	/**
	 * permet de modifier un produit dans la bdd 
	 * invoqu�e au click sur le bouton 'modifier' de 'modification-produit-utilisateur.xhtml'
	 * @param event
	 */
	public String modifierProduit() {
		
		// r�cup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if (uploadedFile != null) {

            String fileNameToUpdate = uploadedFile.getSubmittedFileName();

            if (!"".equals(fileNameToUpdate) && fileNameToUpdate != null) {

                // affectation du nouveau nom � la prop photo de la categorie 
            	produit.setPhoto(fileNameToUpdate);
            }
		}
		
		//modif du produit dans la bdd
		if (produitService.modifier(produit)) {
			
			//envoi d'un msg vers la vue
			FacesMessage messageOK = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification r�ussie ", " - le produit a �t� modifi� avec succ�s");
			
			//envoi du msg
			contextJSF.addMessage(null, messageOK);
			
		} else {

			//envoi d'un msg vers la vue
			FacesMessage messageNOTOK = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec de la modification ", " - la modification du produit a �chou�e");
			
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

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public List<Produit> getFilteredProduits() {
		return filteredProduits;
	}

	public void setFilteredProduits(List<Produit> filteredProduits) {
		this.filteredProduits = filteredProduits;
	}

	
}//end class
