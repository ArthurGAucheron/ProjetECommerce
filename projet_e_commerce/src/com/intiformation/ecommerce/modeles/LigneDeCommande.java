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
	private int commandeID;
	private int panierID;
	
	/*_____________ ctors ______________*/
	public LigneDeCommande() {
		
	}

	public LigneDeCommande(int quantite, double prix, int produitID, int commandeID,
			int panierID) {
		this.quantite = quantite;
		this.prix = prix;
		this.produitID = produitID;
		this.commandeID = commandeID;
		this.panierID = panierID;
	}
	
	public LigneDeCommande(int idLigneDeCommande, int quantite, double prix, int produitID, int commandeID,
			int panierID) {
		this.idLigneDeCommande = idLigneDeCommande;
		this.quantite = quantite;
		this.prix = prix;
		this.produitID = produitID;
		this.commandeID = commandeID;
		this.panierID = panierID;
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

	public int getCommandeID() {
		return commandeID;
	}

	public void setCommandeID(int commandeID) {
		this.commandeID = commandeID;
	}

	public int getPanierID() {
		return panierID;
	}

	public void setPanierID(int panierID) {
		this.panierID = panierID;
	}
	
}//end class
