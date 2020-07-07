package com.intiformation.ecommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.ecommerce.modeles.Commande;

/**
 * implémentation concrète de la couche DAO pour les commandes
 * implémente l'interface ICommandeDAO
 * @author marle
 *
 */
public class CommandeDAOImpl implements ICommandeDAO {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * get all commandes bdd
	 */
	@Override
	public List<Commande> getAll() {
		
		try {
			
			ps = this.connection.prepareStatement("SELECT * FROM commandes");

			rs = ps.executeQuery();
		
			List<Commande> listeCommandesBDD = new ArrayList<>();
			Commande commande = null;
			
			while(rs.next()) {
				
				commande = new Commande(rs.getInt(1), rs.getDate(2), rs.getInt(3));
				listeCommandesBDD.add(commande);
				
			}//end while
			
			return listeCommandesBDD;
		
		} catch (SQLException e) {
			System.out.println("...(CommandeDAOImpl) erreur de l'execution de getAll()...");
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
	 * get commandes by id commande bdd
	 * @param pIdCommande : l'id de la commande à rechercher
	 */
	@Override
	public Commande getById(Integer pIdCommande) {
		try {
			ps = this.connection.prepareStatement("SELECT * FROM commandes WHERE id_commande = ?");
			
			ps.setInt(1, pIdCommande);
			
			rs = ps.executeQuery();
			
			Commande commande = null;
			
			while(rs.next()) {
				
				commande = new Commande(rs.getInt(1), rs.getDate(2), rs.getInt(3));

			}//end while
			
			return commande;
			
		} catch (SQLException e) {
			System.out.println("...(CommandeDAOImpl) erreur de l'execution de getById()...");
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
	 * ajouter commande bdd
	 */
	@Override
	public boolean add(Commande pCommande) {
		
		try {
			ps = this.connection.prepareStatement("INSERT INTO commandes (date_commande, client_id) VALUES (?,?)");
			
			ps.setDate(1, pCommande.getDateCommande());
			ps.setInt(2, pCommande.getClientID());
	
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(CommandeDAOImpl) erreur de l'execution de add()...");
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
	 * modifier commande bdd
	 */
	@Override
	public boolean update(Commande pCommande) {
		
		try {
			ps = this.connection.prepareStatement("UPDATE commandes SET date_commande=?, client_id=? WHERE id_commande=?");
			
			ps.setDate(1, pCommande.getDateCommande());
			ps.setInt(2, pCommande.getClientID());
			ps.setInt(3, pCommande.getIdCommande());
			
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(CommandeDAOImpl) erreur de l'execution de update()...");
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
	 * supprimer commande bdd
	 * @param pIdCommande : l'id de la commande à supprimer
	 */
	@Override
	public boolean deleteById(Integer pIdCommande) {
		
		try {
			ps = this.connection.prepareStatement("DELETE FROM commandes WHERE id_commande=?");
			
			ps.setInt(1, pIdCommande);
			
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(CommandeDAOImpl) erreur de l'execution de deleteByID()...");
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
	
}//end class
