package com.intiformation.ecommerce.modeles;

/**
 * modèle de données pour un utilisateur mappé sur la table 'utilisateurs' de la bdd
 * @author marle
 *
 */
public class Utilisateur {

	/*_____________ propriétés ______________*/
	private int idUtilisateur;
	private String nomUtilisateur;
	private String passwordUtilisateur;
	private int actived;
	
	/*_____________ ctors ______________*/
	public Utilisateur() {
		
	}
	
	public Utilisateur(String nomUtilisateur, String passwordUtilisateur, int actived) {
		this.nomUtilisateur = nomUtilisateur;
		this.passwordUtilisateur = passwordUtilisateur;
		this.actived = actived;
	}

	public Utilisateur(int idUtilisateur, String nomUtilisateur, String passwordUtilisateur, int actived) {
		this.idUtilisateur = idUtilisateur;
		this.nomUtilisateur = nomUtilisateur;
		this.passwordUtilisateur = passwordUtilisateur;
		this.actived = actived;
	}

	/*_____________ getters/setters ______________*/

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getPasswordUtilisateur() {
		return passwordUtilisateur;
	}

	public void setPasswordUtilisateur(String passwordUtilisateur) {
		this.passwordUtilisateur = passwordUtilisateur;
	}

	public int getActived() {
		return actived;
	}

	public void setActived(int actived) {
		this.actived = actived;
	}
	
}//end class
