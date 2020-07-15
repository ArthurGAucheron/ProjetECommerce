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

import com.intiformation.ecommerce.modeles.Categorie;
import com.intiformation.ecommerce.service.CategorieServiceImpl;
import com.intiformation.ecommerce.service.ICategorieService;

/**
 * managed bean pour la gesion des catégories
 * @author marle
 *
 */
@ManagedBean(name="gestionCategoriesBean")
@SessionScoped
public class GestionCategoriesBean implements Serializable{

	/*___________ propriétés _____________*/
	private Categorie categorie;
	private List<Categorie> listeCategories;
	private List<Integer> listeNomsCategories;
	private Categorie categorieRecherche;
	private int idCat;
	
    // file upload de l'API servlet
	private Part uploadedFile;
	
	//déclaration de la couche service
	private ICategorieService categorieService;
		
	/*___________ ctors _____________*/

	/**
	 * ctor vide pour instanciation du MB par le Serveur
	 */
	public GestionCategoriesBean() {
		categorieService = new CategorieServiceImpl();
	}//end ctor
	
	/*___________ méthodes _____________*/
	/**
	 * permet de récupérer et afficher la liste de toutes les catégories de la bdd
	 * @return
	 */
	public List<Categorie> afficherCategories() {
		
		listeCategories = categorieService.findAll();		
		return listeCategories;
			
	}//end afficher()
	
	/**
	 * permet d'initialiser un nouvel objet categorie
	 * invoquée au click sur le bouton 'ajouter' de 'gestion-categories-utilisateur.xhtml' 
	 * @return
	 */
	public String initialiserCategorie() {
		
		//instanciation nouvel objet categorie
		Categorie categorieToAdd = new Categorie();
		
		//affectation de la catégorie à ajouter à la variable categorie du MB -> va réceptionner infos envoyées depuis formulaire d'ajout
		setCategorie(categorieToAdd);
		
		return "ajout-categorie-utilisateur.xhtml";
		
	}//end initialiserCategorie()

	/**
	 * permet d'ajouter une nouvelle catégorie dans la bdd
	 * méthode invoquée au click sur le bouton 'ajouter' de 'ajout-categorie-utilisateur.xhtml'
	 * @return
	 */
	public String ajouterCategorie() {
		
		//récup du contexte de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		 // traitement du fileUpload : recup du nom de l'image
        String fileName = uploadedFile.getSubmittedFileName();
        
        // affectation du nom à  la prop photo de la categorie
        categorie.setPhoto(fileName);
				
		//ajout de la catégorie dans la bdd via la couche service
		if(categorieService.ajouter(categorie)) {
			
			 //----------------------------------------------
            // ajout de la photo dans le dossier images categories
            //-----------------------------------------------
            
			try {
            // recup du contenu de l'image
            InputStream imageContent = uploadedFile.getInputStream();

            // recup de la valeur du param d'initialisation context-param de web.xml
            FacesContext fContext = FacesContext.getCurrentInstance();
            String pathTmp = fContext.getExternalContext().getInitParameter("file-upload");
            
            String filePath = fContext.getExternalContext().getRealPath(pathTmp);

            // création du fichier image (conteneur de l'image) 
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
			
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ajout catégorie ", " - la nouvelle catégorie a été ajoutée avec succès"));

			return "gestion-categories-utilisateur.xhtml";
					
		} else {
					
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec ajout catégorie ", " - l'ajout de la catégorie a échoué"));
			return "ajout-categorie-utilisateur.xhtml";
	
		}//end else
			
	}//end ajouterCategorie
	
	/**
	 * permet de supprimer une catégorie dans la bdd
	 * méthode invoquée au click sur le bouton 'supprimer' de 'gestion-categories-utilisateur.xhtml'
	 * @return
	 */
	public void supprimerCategorie(ActionEvent event) {
		
		// 1. récup du paramètre passé dans le composant au click sur le lien 'supprimer'
		UIParameter uip = (UIParameter) event.getComponent().findComponent("deleteID");
				
		//2. récup de la valeur du parmaètre
		int categorieID = (int) uip.getValue();
				
		//3. suppression de la categorie dans la bdd via l'id
					
			//récup contexte JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
					
			//suppression categorie
		if (categorieService.supprimerById(categorieID)) {
					
			//envoi d'un msg vers la vue
		contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Suppression catégorie ", " - la catégorie a été supprimée"));
					
			//redirection vers gestion-categories-utilisateur.xhtml (réf : les clés d'outcom dans faces-config.xml)
						
		} else {

			//envoi d'un msg vers la vue
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec suppression catégorie ", " - la suppression de la catégorie a échouée"));
					
			//redirection vers gestion-categories-utilisateur.xhtml (réf : les clés d'outcom dans faces-config.xml)
					
		}//end else
					
	}//end supprimerCategorie
	
	/**
	 * permet d'éditer une categorie dans la bdd 
	 * invoquée au click sur le lien 'modifier' de 'gestion-categories-utilisateur.xhtml' 
	 */
	public void selectionnerCategorie(ActionEvent event){
		
		//récup du paramètre passé dans le composant au click
		UIParameter uip = (UIParameter) event.getComponent().findComponent("updateID");
		
		//récup de la valeur du paramètre
		int categorieID = (int) uip.getValue();
		
		//recup de la catégorie à editer via l'id dans la bdd
		Categorie categorieToUpdate = categorieService.findById(categorieID);
		
		//affectation de la catégorie à éditer à la variable categorie du MB
		setCategorie(categorieToUpdate);
		
		//redirection vers la page d'édition 'modification-categorie-utilisateur.xhtml' (ref : clé d'outcum dans faces-config.xml)
		
	}//end selectionnerCategorie
	
	/**
	 * permet de modifier une categorie dans la bdd 
	 * invoquée au click sur le bouton 'modifier' de 'modification-categorie-utilisateur.xhtml'
	 * @param event
	 */
	public String modifierCategorie() {
		
		// récup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if (uploadedFile != null) {

            String fileNameToUpdate = uploadedFile.getSubmittedFileName();

            if (!"".equals(fileNameToUpdate) && fileNameToUpdate != null) {

                // affectation du nouveau nom à la prop photo de la categorie 
            	categorie.setPhoto(fileNameToUpdate);
            }
		}
		
		//modif de la categorie dans la bdd
		if (categorieService.modifier(categorie)) {
			
			//envoi d'un msg vers la vue
			FacesMessage messageOK = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modification réussie ", " - la catégorie a été modifiée avec succès");
			
			//envoi du msg
			contextJSF.addMessage(null, messageOK);
			
		} else {

			//envoi d'un msg vers la vue
			FacesMessage messageNOTOK = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Echec de la modification ", " - la modification de la catégorie a échouée");
			
			//envoi du msg
			contextJSF.addMessage(null, messageNOTOK);
			
		}//end else
		
		//redirection vers gestion-categories-utilisateur.xhtml
		return "gestion-categories-utilisateur.xhtml";
		
	}//end modifierCategorie	
	
	/**
	 * permet de récupérer le nom de la catégorie à partir de son Id
	 * @param idCategorie
	 * @return
	 */
	public String getNomCategorieById(Integer idCategorie) {
		
		Categorie categorieToFind = categorieService.findById(idCategorie);
		String nomCategorie = categorieToFind.getNomCategorie();
		
		return nomCategorie;
		
	}//end getNomCategorieById
	
	/**
	 * permet de recherche une catégorie par son nom
	 * @return
	 */
	public Categorie afficherCategorieRecherche(){
				
		Categorie categorieToSearch = categorieService.findById(idCat);
				
		setCategorieRecherche(categorieToSearch);
				
		return categorieRecherche;
		
	}//end afficherCategorieRecherche
	
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

	public List<Integer> getListeNomsCategories() {
		return listeNomsCategories;
	}

	public void setListeNomsCategories(List<Integer> listeNomsCategories) {
		this.listeNomsCategories = listeNomsCategories;
	}

	public Categorie getCategorieRecherche() {
		return categorieRecherche;
	}

	public void setCategorieRecherche(Categorie categorieRecherche) {
		this.categorieRecherche = categorieRecherche;
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
	
}//end class
