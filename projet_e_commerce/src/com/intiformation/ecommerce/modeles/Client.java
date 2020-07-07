package com.intiformation.ecommerce.modeles;

/**
 * modèle de données pour un client mappé sur la table 'clients' de la bdd
 * @author marle
 *
 */
public class Client {

	/*_____________ propriétés ______________*/
	private int idClient;
	private String nomClient;
	private String adresse;
	private String email;
	private String telephone;
	private String passwordClient;
	
	/*_____________ ctors ______________*/
	public Client() {
		
	}

	public Client(String nomClient, String adresse, String email, String telephone,
			String passwordClient) {
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
		this.passwordClient = passwordClient;
	}

	public Client(int idClient, String nomClient, String adresse, String email, String telephone,
			String passwordClient) {
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
		this.passwordClient = passwordClient;
	}
	
	/*_____________ getters/setters ______________*/

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPasswordClient() {
		return passwordClient;
	}

	public void setPasswordClient(String passwordClient) {
		this.passwordClient = passwordClient;
	}
	
}//end class
