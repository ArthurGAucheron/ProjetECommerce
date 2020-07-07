package com.intiformation.ecommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.ecommerce.modeles.LigneDeCommande;

/**
 * implémentation concrète de la couche DAO pour les lignes de commande
 * implémente l'interface ILigneDeCommandeDAO
 * @author marle
 *
 */
public class LigneDeCommandeDAOImpl implements ILigneDeCommandeDAO{

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * get all lignes de commande bdd
	 */
	@Override
	public List<LigneDeCommande> getAll() {
		
		try {
			
			ps = this.connection.prepareStatement("SELECT * FROM lignes_commandes");

			rs = ps.executeQuery();
		
			List<LigneDeCommande> listeLigneDeCommandeBDD = new ArrayList<>();
			LigneDeCommande ligneDeCommande = null;
			
			while(rs.next()) {
				
				ligneDeCommande = new LigneDeCommande(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
				listeLigneDeCommandeBDD.add(ligneDeCommande);
				
			}//end while
			
			return listeLigneDeCommandeBDD;
		
		} catch (SQLException e) {
			System.out.println("...(LigneDeCommandeDAOImpl) erreur de l'execution de getAll()...");
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
	 * get lignes de commandes by id ligne de commande bdd
	 * @param pIdLigneDeCommande : l'id de la ligne de commande à rechercher
	 */
	@Override
	public LigneDeCommande getById(Integer pIdLigneDeCommande) {
		try {
			ps = this.connection.prepareStatement("SELECT * FROM lignes_commandes WHERE id_ligne_commande = ?");
			
			ps.setInt(1, pIdLigneDeCommande);
			
			rs = ps.executeQuery();
			
			LigneDeCommande ligneDeCommande = null;
			
			while(rs.next()) {
				
				ligneDeCommande = new LigneDeCommande(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
				
			}//end while
			
			return ligneDeCommande;
			
		} catch (SQLException e) {
			System.out.println("...(LigneDeCommandeDAOImpl) erreur de l'execution de getById()...");
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
	 * ajouter ligne de commande bdd
	 */
	@Override
	public boolean add(LigneDeCommande pLigneDeCommande) {
		
		try {
			ps = this.connection.prepareStatement("INSERT INTO lignes_commandes (quantite, prix, produit_id, commande_id, panier_id) VALUES (?,?,?,?,?)");
			
			ps.setInt(1, pLigneDeCommande.getQuantite());
			ps.setDouble(2, pLigneDeCommande.getPrix());
			ps.setInt(3, pLigneDeCommande.getProduitID());
			ps.setInt(4, pLigneDeCommande.getCommandeID());
			ps.setInt(5, pLigneDeCommande.getPanierID());
	
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(LigneDeCommandeDAOImpl) erreur de l'execution de add()...");
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
	 * modifier ligne de commande bdd
	 */
	@Override
	public boolean update(LigneDeCommande pLigneDeCommande) {
		
		try {
			ps = this.connection.prepareStatement("UPDATE lignes_commandes SET quantite=?, prix=?, produit_id=?, commande_id=?, panier_id=? WHERE id_ligne_commande=?");
			
			ps.setInt(1, pLigneDeCommande.getQuantite());
			ps.setDouble(2, pLigneDeCommande.getPrix());
			ps.setInt(3, pLigneDeCommande.getProduitID());
			ps.setInt(4, pLigneDeCommande.getCommandeID());
			ps.setInt(5, pLigneDeCommande.getPanierID());
			ps.setInt(6, pLigneDeCommande.getIdLigneDeCommande());
			
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(LigneDeCommandeDAOImpl) erreur de l'execution de update()...");
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
	 * supprimer ligne de commande bdd
	 * @param pIdLigneDeCommande : l'id de la ligne de commande à supprimer
	 */
	@Override
	public boolean deleteById(Integer pIdLigneDeCommande) {
		
		try {
			ps = this.connection.prepareStatement("DELETE FROM lignes_commandes WHERE id_ligne_commande=?");
			
			ps.setInt(1, pIdLigneDeCommande);
			
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(LigneDeCommandeDAOImpl) erreur de l'execution de deleteByID()...");
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
