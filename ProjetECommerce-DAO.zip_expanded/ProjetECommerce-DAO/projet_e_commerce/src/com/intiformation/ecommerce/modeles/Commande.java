package com.intiformation.ecommerce.modeles;

import java.sql.Date;

/**
 * modèle de données pour une commande mappé sur la table 'commandes' de la bdd
 * @author marle
 *
 */
public class Commande {

	/*_____________ propriétés ______________*/
	private int idCommande;
	private Date dateCommande;
	private int clientID;
	
	/*_____________ ctors ______________*/
	public Commande() {

	}

	public Commande(Date dateCommande, int clientID) {
		this.dateCommande = dateCommande;
		this.clientID = clientID;
	}
	
	public Commande(int idCommande, Date dateCommande, int clientID) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
		this.clientID = clientID;
	}
	
	/*_____________ getters/setters ______________*/

	public int getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	
}//end class
