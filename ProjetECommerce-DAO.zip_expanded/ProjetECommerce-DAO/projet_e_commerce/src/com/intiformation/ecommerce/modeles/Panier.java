package com.intiformation.ecommerce.modeles;

/**
 * mod�le de donn�es pour un panier mapp� sur la table 'panier' de la bdd
 * @author marle
 *
 */
public class Panier {

	/*_____________ propri�t�s ______________*/
	private int idPanier;
	
	/*_____________ ctors ______________*/
	public Panier() {
		
	}

	public Panier(int idPanier) {
		this.idPanier = idPanier;
	}
	
	/*_____________ getters/setters ______________*/

	public int getIdPanier() {
		return idPanier;
	}

	public void setIdPanier(int idPanier) {
		this.idPanier = idPanier;
	}
	
}//end class
