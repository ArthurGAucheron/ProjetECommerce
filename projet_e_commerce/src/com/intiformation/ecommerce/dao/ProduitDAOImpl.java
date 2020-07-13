package com.intiformation.ecommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.ecommerce.modeles.Produit;


/**
 * implémentation concrète de la couche DAO pour les produits
 * implémente l'interface IProduitDAO
 * @author marle
 *
 */
public class ProduitDAOImpl implements IProduitDAO{

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * get all produits bdd
	 */
	@Override
	public List<Produit> getAll() {
		
		try {
			
			ps = this.connection.prepareStatement("SELECT * FROM produits");

			rs = ps.executeQuery();
		
			List<Produit> listeProduitsBDD = new ArrayList<>();
			Produit produit = null;
			
			while(rs.next()) {
				
				produit = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getBoolean(6), rs.getString(7), rs.getInt(8));
				listeProduitsBDD.add(produit);
				
			}//end while
			
			return listeProduitsBDD;
		
		} catch (SQLException e) {
			System.out.println("...(ProduitDAOImpl) erreur de l'execution de getAll()...");
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
	 * get produits by id produit bdd
	 * @param pIdProduit : l'id du produit à rechercher
	 */
	@Override
	public Produit getById(Integer pIdProduit) {
		try {
			ps = this.connection.prepareStatement("SELECT * FROM produits WHERE id_produit = ?");
			
			ps.setInt(1, pIdProduit);
			
			rs = ps.executeQuery();
			
			Produit produit = null;
			
			while(rs.next()) {
				
				produit = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getBoolean(6), rs.getString(7), rs.getInt(8));

			}//end while
			
			return produit;
			
		} catch (SQLException e) {
			System.out.println("...(ProduitDAOImpl) erreur de l'execution de getById()...");
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
	 * ajouter produit bdd
	 */
	@Override
	public boolean add(Produit pProduit) {
		
		try {
			ps = this.connection.prepareStatement("INSERT INTO produits (designation, description, prix, quantite, selectionne, photo, categorie_id) VALUES (?,?,?,?,?,?,?)");
			
			ps.setString(1, pProduit.getDesignation());
			ps.setString(2, pProduit.getDescription());
			ps.setDouble(3, pProduit.getPrix());
			ps.setInt(4, pProduit.getQuantite());
			ps.setBoolean(5, pProduit.isSelectionne());
			ps.setString(6, pProduit.getPhoto());
			ps.setInt(7, pProduit.getCategorieID());
	
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(ProduitDAOImpl) erreur de l'execution de add()...");
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
	 * modifier produit bdd
	 */
	@Override
	public boolean update(Produit pProduit) {
		
		try {
			ps = this.connection.prepareStatement("UPDATE produits SET designation=?, description=?, prix=?, quantite=?, selectionne=?, photo=?, categorie_id=? WHERE id_produit=?");

			ps.setString(1, pProduit.getDesignation());
			ps.setString(2, pProduit.getDescription());
			ps.setDouble(3, pProduit.getPrix());
			ps.setInt(4, pProduit.getQuantite());
			ps.setBoolean(5, pProduit.isSelectionne());
			ps.setString(6, pProduit.getPhoto());
			ps.setInt(7, pProduit.getCategorieID());
			ps.setInt(8, pProduit.getIdProduit());
			
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(ProduitDAOImpl) erreur de l'execution de update()...");
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
	 * supprimer produit bdd
	 * @param pIdProduit : l'id du produit à supprimer
	 */
	@Override
	public boolean deleteById(Integer pIdProduit) {
		
		try {
			ps = this.connection.prepareStatement("DELETE FROM produits WHERE id_produit=?");
			
			ps.setInt(1, pIdProduit);
			
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(ProduitDAOImpl) erreur de l'execution de deleteByID()...");
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
	 * permet de consulter les produits d'une catégorie
	 * @param pIDCategorie : l'ID de la catégorie dont on souhaite consulter les produits 
	 */
	@Override
	public List<Produit> getProduitsByIDCategorie(Integer pIDCategorie) {

		try {
			
			ps = this.connection.prepareStatement("SELECT * FROM produits WHERE categorie_id=?");

			ps.setInt(1, pIDCategorie);
			
			rs = ps.executeQuery();
		
			List<Produit> listeProduitsByCategorieBDD = new ArrayList<>();
			Produit produit = null;
			
			while(rs.next()) {
				
				produit = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getBoolean(6), rs.getString(7), rs.getInt(8));
				listeProduitsByCategorieBDD.add(produit);
				
			}//end while
			
			return listeProduitsByCategorieBDD;
		
		} catch (SQLException e) {
			System.out.println("...(ProduitDAOImpl) erreur de l'execution de getProduitsByIDCategorie()...");
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
		
	}//end getProduitsByIDCategorie

	/**
	 * permet de rechercher un produit par mot clé
	 * @pMotCle : le mot clé 
	 */
	@Override
	public List<Produit> getProduitsByMotCle(String pMotCle) {

		try {
			
			ps = this.connection.prepareStatement("SELECT * FROM produits WHERE designation like ?");

			ps.setString(1, "%" + pMotCle + "%");
			
			rs = ps.executeQuery();
		
			List<Produit> listeProduitsByCategorieBDD = new ArrayList<>();
			Produit produit = null;
			
			while(rs.next()) {
				
				produit = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getBoolean(6), rs.getString(7), rs.getInt(8));
				listeProduitsByCategorieBDD.add(produit);
				
			}//end while
			
			return listeProduitsByCategorieBDD;
		
		} catch (SQLException e) {
			System.out.println("...(ProduitDAOImpl) erreur de l'execution de getProduitsByIDCategorie()...");
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
		
	}//end getProduitsByMotCle

	/**
	 * permet de consulter la liste des produits selectionnés
	 */
	@Override
	public List<Produit> getProduitsSelectionnes() {

		try {
			
			ps = this.connection.prepareStatement("SELECT * FROM produits WHERE selectionne=1");

			rs = ps.executeQuery();
		
			List<Produit> listeProduitsByCategorieBDD = new ArrayList<>();
			Produit produit = null;
			
			while(rs.next()) {
				
				produit = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getBoolean(6), rs.getString(7), rs.getInt(8));
				listeProduitsByCategorieBDD.add(produit);
				
			}//end while
			
			return listeProduitsByCategorieBDD;
		
		} catch (SQLException e) {
			System.out.println("...(ProduitDAOImpl) erreur de l'execution de getProduitsByIDCategorie()...");
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
		
	}//end getProduitsSelectionnes
	
}//end class
