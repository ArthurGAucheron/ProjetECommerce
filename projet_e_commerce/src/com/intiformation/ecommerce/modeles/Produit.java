package com.intiformation.ecommerce.modeles;

/**
 * modèle de données pour un produit mappé sur la table 'produits' de la bdd
 * @author marle
 *
 */
public class Produit {

	/*_____________ propriétés ______________*/
	private int idProduit;
	private String designation;
	private String description;
	private double prix;
	private int quantite;
	private boolean selectionne;
	private String photo;
	private int categorieID;
	
	/*_____________ ctors ______________*/
	public Produit() {
		
	}

	public Produit(String designation, String description, double prix, int quantite,
			boolean selectionne, String photo, int categorieID) {
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
		this.categorieID = categorieID;
	}
	
	public Produit(int idProduit, String designation, String description, double prix, int quantite,
			boolean selectionne, String photo, int categorieID) {
		this.idProduit = idProduit;
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
		this.categorieID = categorieID;
	}
	
	/*_____________ getters/setters ______________*/

	public int getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public boolean isSelectionne() {
		return selectionne;
	}

	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getCategorieID() {
		return categorieID;
	}

	public void setCategorieID(int categorieID) {
		this.categorieID = categorieID;
	}
	
}//end class
