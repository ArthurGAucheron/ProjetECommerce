package com.intiformation.ecommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.ecommerce.modeles.Client;

/**
 * implémentation concrète de la couche DAO pour les clients
 * implémente l'interface IClientDAO
 * @author marle
 *
 */
public class ClientDAOImpl implements IClientDAO{

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * get all clients bdd
	 */
	@Override
	public List<Client> getAll() {
		
		try {
			
			ps = this.connection.prepareStatement("SELECT * FROM clients");

			rs = ps.executeQuery();
		
			List<Client> listeClientBDD = new ArrayList<>();
			Client client = null;
			
			while(rs.next()) {
				
				client = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));	
				listeClientBDD.add(client);
				
			}//end while
			
			return listeClientBDD;
		
		} catch (SQLException e) {
			System.out.println("...(ClientDAOImpl) erreur de l'execution de getAll()...");
			e.printStackTrace();
			
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end finally
		
		return null;
		
	}//end getAll()

	/**
	 * get clients by id client bdd
	 * @param pIdClient : l'id du client à rechercher
	 */
	@Override
	public Client getById(Integer pIdClient) {
		try {
			ps = this.connection.prepareStatement("SELECT * FROM clients WHERE id_client = ?");
			
			ps.setInt(1, pIdClient);
			
			rs = ps.executeQuery();
			
			Client client = null;
			
			while(rs.next()) {
				
				client = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));	

			}//end while
			
			return client;
			
		} catch (SQLException e) {
			System.out.println("...(ClientDAOImpl) erreur de l'execution de getById()...");
			e.printStackTrace();
			
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end finally
		
		return null;
		
	}//end getById

	/**
	 * ajouter client bdd
	 */
	@Override
	public boolean add(Client pClient) {
		
		try {
			ps = this.connection.prepareStatement("INSERT INTO clients (nom_client, adresse, email, tel, password_client) VALUES (?,?,?,?,?)");
			
			ps.setString(1, pClient.getNomClient());
			ps.setString(2, pClient.getAdresse());
			ps.setString(3, pClient.getEmail());
			ps.setString(4, pClient.getTelephone());
			ps.setString(5, pClient.getPasswordClient());
	
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(ClientDAOImpl) erreur de l'execution de add()...");
			e.printStackTrace();
			
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end finally	
		
		return false;
		
	}//end add()

	/**
	 * modifier client bdd
	 */
	@Override
	public boolean update(Client pClient) {
		
		try {
			ps = this.connection.prepareStatement("UPDATE clients SET nom_client=?, adresse=?, email=?, tel=?, password_client=? WHERE id_client=?");

			ps.setString(1, pClient.getNomClient());
			ps.setString(2, pClient.getAdresse());
			ps.setString(3, pClient.getEmail());
			ps.setString(4, pClient.getTelephone());
			ps.setString(5, pClient.getPasswordClient());
			ps.setInt(6, pClient.getIdClient());
			
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(ClientDAOImpl) erreur de l'execution de update()...");
			e.printStackTrace();
			
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end finally			
		
		return false;
		
	}//end update()

	/**
	 * supprimer client bdd
	 * @param pIdClient : l'id du client à supprimer
	 */
	@Override
	public boolean deleteById(Integer pIdClient) {
		
		try {
			ps = this.connection.prepareStatement("DELETE FROM clients WHERE id_client=?");
			
			ps.setInt(1, pIdClient);
			
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(ClientDAOImpl) erreur de l'execution de deleteByID()...");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end finally			
		
		return false;
		
	}//end deleteById()

	/**
	 * vérifie l'existence du client (avec email et password) dans la bdd
	 */
	@Override
	public boolean isClientExists(String pMail, String pPassword) {
		
		try {
			ps = this.connection.prepareStatement("SELECT COUNT(*) FROM clients WHERE email = ? AND password_client = ?");
			
			ps.setString(1, pMail);
			ps.setString(2, pPassword);
			
			rs = ps.executeQuery();
			
			rs.next();
			
			int verif = rs.getInt(1);
			
			return (verif == 1);
			
		} catch (SQLException e) {
			System.out.println("...(ClientDAOImpl) erreur de l'execution de isClientExists()...");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end finally
		
		return false;
		
	}//end isClientExists

	@Override
	public int getIdByEmail(String pmail) {
		
		try {
			ps = this.connection.prepareStatement("SELECT id_client FROM clients WHERE email = ?");
		
			ps.setString(1,pmail);
			
			rs = ps.executeQuery();
			
			rs.next();
			
			return rs.getInt(1);
		
			
		} catch (SQLException e) {
			System.out.println("...(ClientDAOImpl) erreur de l'execution de getIdByEmail()...");
			e.printStackTrace();
			
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end finally
		
		return 0;
	}
	
}//end class
