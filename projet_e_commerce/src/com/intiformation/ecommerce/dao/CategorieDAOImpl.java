package com.intiformation.ecommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.ecommerce.modeles.Categorie;

/**
 * implémentation concrète de la couche DAO pour les catégories
 * implémente l'interface ICategorieDAO
 * @author marle
 *
 */
public class CategorieDAOImpl implements ICategorieDAO{

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * get all categories bdd
	 */
	@Override
	public List<Categorie> getAll() {
		
		try {
			
			ps = this.connection.prepareStatement("SELECT * FROM categories");

			rs = ps.executeQuery();
		
			List<Categorie> listeCategoriesBDD = new ArrayList<>();
			Categorie categorie = null;
			
			while(rs.next()) {
				
				categorie = new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				listeCategoriesBDD.add(categorie);
				
			}//end while
			
			return listeCategoriesBDD;
		
		} catch (SQLException e) {
			System.out.println("...(CategorieDAOImpl) erreur de l'execution de getAll()...");
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
	 * get categorie by idCategorie bdd
	 * @param pIdCategorie : l'id de la categorie à rechercher
	 */
	@Override
	public Categorie getById(Integer pIdCategorie) {
		try {
			ps = this.connection.prepareStatement("SELECT * FROM categories WHERE id_categorie = ?");
			
			ps.setInt(1, pIdCategorie);
			
			rs = ps.executeQuery();
			
			Categorie categorie = null;
			
			while(rs.next()) {
				
				categorie = new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				
			}//end while
			
			return categorie;
			
		} catch (SQLException e) {
			System.out.println("...(CategorieDAOImpl) erreur de l'execution de getById()...");
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
	 * ajouter categorie bdd
	 */
	@Override
	public boolean add(Categorie pCategorie) {
		
		try {
			ps = this.connection.prepareStatement("INSERT INTO categories (nom_categorie, photo, description) VALUES (?,?,?)");
			
			ps.setString(1, pCategorie.getNomCategorie());
			ps.setString(2, pCategorie.getPhoto());
			ps.setString(3, pCategorie.getDescription());
			
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(CategorieDAOImpl) erreur de l'execution de add()...");
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
	 * modifier categorie bdd
	 */
	@Override
	public boolean update(Categorie pCategorie) {
		
		try {
			ps = this.connection.prepareStatement("UPDATE categories SET nom_categorie=?, photo=?, description=? WHERE id_categorie=?");
			
			ps.setString(1, pCategorie.getNomCategorie());
			ps.setString(2, pCategorie.getPhoto());
			ps.setString(3, pCategorie.getDescription());
			ps.setInt(4, pCategorie.getIdCategorie());
			
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(CategorieDAOImpl) erreur de l'execution de update()...");
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
	 * supprimer categorie bdd
	 * @param pIdCategorie : l'id de la catégorie à supprimer
	 */
	@Override
	public boolean deleteById(Integer pIdCategorie) {
		
		try {
			ps = this.connection.prepareStatement("DELETE FROM categories WHERE id_categorie=?");
			
			ps.setInt(1, pIdCategorie);
			
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(CategorieDAOImpl) erreur de l'execution de deleteByID()...");
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
