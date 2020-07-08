package com.intiformation.ecommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.ecommerce.modeles.Utilisateur;

/**
 * implémentation concrète de la couche DAO pour les utilisateurs
 * implémente l'interface IUtilisateurDAO
 * @author marle
 *
 */
public class UtilisateurDAOImpl implements IUtilisateurDAO {


	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * get all utilisateurs bdd
	 */
	@Override
	public List<Utilisateur> getAll() {
		
		try {
			
			ps = this.connection.prepareStatement("SELECT * FROM utilisateurs");

			rs = ps.executeQuery();
		
			List<Utilisateur> listeUtilisateursBDD = new ArrayList<>();
			Utilisateur utilisateur = null;
			
			while(rs.next()) {
				
				utilisateur = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				listeUtilisateursBDD.add(utilisateur);
				
			}//end while
			
			return listeUtilisateursBDD;
		
		} catch (SQLException e) {
			System.out.println("...(UtilisateurDAOImpl) erreur de l'execution de getAll()...");
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
	 * get utilisateur by id utilisateur bdd
	 * @param pIdUtilisateur : l'id de l'utilisateur à rechercher
	 */
	@Override
	public Utilisateur getById(Integer pIdUtilisateur) {
		try {
			ps = this.connection.prepareStatement("SELECT * FROM utilisateurs WHERE id_utilisateur=?");
			
			ps.setInt(1, pIdUtilisateur);
			
			rs = ps.executeQuery();
			
			Utilisateur utilisateur = null;
			
			while(rs.next()) {
				
				utilisateur = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));

			}//end while
			
			return utilisateur;
			
		} catch (SQLException e) {
			System.out.println("...(UtilisateurDAOImpl) erreur de l'execution de getById()...");
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
	 * ajouter utilisateur bdd
	 */
	@Override
	public boolean add(Utilisateur pUtilisateur) {
		
		try {
			ps = this.connection.prepareStatement("INSERT INTO utilisateurs (nom_utilisateur, password, actived) VALUES (?,?,?)");
			
			ps.setString(1, pUtilisateur.getNomUtilisateur());
			ps.setString(2, pUtilisateur.getPasswordUtilisateur());
			ps.setInt(3, pUtilisateur.getActived());
	
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(UtilisateurDAOImpl) erreur de l'execution de add()...");
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
	 * modifier utilisateur bdd
	 */
	@Override
	public boolean update(Utilisateur pUtilisateur) {
		
		try {
			ps = this.connection.prepareStatement("UPDATE utilisateurs SET nom_utilisateur=?, password=?, actived=? WHERE id_utilisateur=?");
			
			ps.setString(1, pUtilisateur.getNomUtilisateur());
			ps.setString(2, pUtilisateur.getPasswordUtilisateur());
			ps.setInt(3, pUtilisateur.getActived());
			ps.setInt(4, pUtilisateur.getIdUtilisateur());
			
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(UtilisateurDAOImpl) erreur de l'execution de update()...");
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
	 * supprimer utilisateur bdd
	 * @param pIdUtilisateur : l'id de l'utilisateur à supprimer
	 */
	@Override
	public boolean deleteById(Integer pIdUtilisateur) {
		
		try {
			ps = this.connection.prepareStatement("DELETE FROM utilisateurs WHERE id_utilisateur=?");
			
			ps.setInt(1, pIdUtilisateur);
			
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(UtilisateurDAOImpl) erreur de l'execution de deleteByID()...");
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
	 * vérification de l'existance de l'utilisateur (nom utilisateur + password) dans la bdd 
	 */
	@Override
	public boolean isUtilisateurExists(String pUserName, String pPassword) {
		
		try {
			ps = this.connection.prepareStatement("SELECT COUNT(*) FROM utilisateurs WHERE nom_utilisateur = ? AND password = ?");
			
			ps.setString(1, pUserName);
			ps.setString(2, pPassword);
			
			rs = ps.executeQuery();
			
			rs.next();
			
			int verif = rs.getInt(1);
			
			return (verif == 1);
			
		} catch (SQLException e) {
			System.out.println("...(UtilisateurDAOImpl) erreur de l'execution de isUtilisateurExists()...");
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

	}//end isUtilisateurExists

}//end class
