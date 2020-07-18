package com.intiformation.ecommerce.modeles;

/**
 * modèle de données pour une ligne de commande mappé sur la table 'lignes_commandes' de la bdd
 * @author marle
 *
 */
public class LigneDeCommande {

	/*_____________ propriétés ______________*/
	private int idLigneDeCommande;
	private int quantite;
	private double prix;
	private int produitID;
	private int panierID;
	private int commandeID;
	
	/*_____________ ctors ______________*/
	public LigneDeCommande() {
		
	}
	
	
	
	public LigneDeCommande(int quantite, double prix, int produitID, int panierID) {
		this.quantite = quantite;
		this.prix = prix;
		this.produitID = produitID;
		this.panierID = panierID;
	}
	
	public LigneDeCommande(int idLigneDeCommande, int quantite, double prix, int produitID,	int panierID) {
		this.idLigneDeCommande = idLigneDeCommande;
		this.quantite = quantite;
		this.prix = prix;
		this.produitID = produitID;
		this.panierID = panierID;
	}
	
	
	
	public LigneDeCommande(int quantite, double prix, int produitID, int panierID, int commandeID) {
		super();
		this.quantite = quantite;
		this.prix = prix;
		this.produitID = produitID;
		this.panierID = panierID;
		this.commandeID = commandeID;
	}



	/*_____________ getters/setters ______________*/

	public int getIdLigneDeCommande() {
		return idLigneDeCommande;
	}

	public void setIdLigneDeCommande(int idLigneDeCommande) {
		this.idLigneDeCommande = idLigneDeCommande;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getProduitID() {
		return produitID;
	}

	public void setProduitID(int produitID) {
		this.produitID = produitID;
	}


	public int getPanierID() {
		return panierID;
	}

	public void setPanierID(int panierID) {
		this.panierID = panierID;
	}



	public int getCommandeID() {
		return commandeID;
	}



	public void setCommandeID(int commandeID) {
		this.commandeID = commandeID;
	}
	
}//end class
