package com.intiformation.ecommerce.modeles;

/**
 * modèle de données pour une catégorie mappé sur la table 'categories' de la bdd
 * @author marle
 *
 */
public class Categorie {

	/*_____________ propriétés ______________*/
	private int idCategorie;
	private String nomCategorie;
	private String photo;
	private String description;
	
	/*_____________ ctors ______________*/
	public Categorie() {
		
	}

	public Categorie(String nomCategorie, String photo, String description) {
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
	}
	
	public Categorie(int idCategorie, String nomCategorie, String photo, String description) {
		this.idCategorie = idCategorie;
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
	}

	/*_____________ getters/setters ______________*/
	
	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
}//end class
