package com.intiformation.ecommerce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.ecommerce.modeles.Panier;


/**
 * implémentation concrète de la couche DAO pour le panier
 * implémente l'interface IPanierDAO
 * @author marle
 *
 */
public class PanierDAOImpl implements IPanierDAO{


	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * get all panier bdd
	 */
	@Override
	public List<Panier> getAll() {
		
		try {
			
			ps = this.connection.prepareStatement("SELECT * FROM panier");

			rs = ps.executeQuery();
		
			List<Panier> listePanierBDD = new ArrayList<>();
			Panier panier = null;
			
			while(rs.next()) {
				
				panier = new Panier(rs.getInt(1));
				listePanierBDD.add(panier);
				
			}//end while
			
			return listePanierBDD;
		
		} catch (SQLException e) {
			System.out.println("...(PanierDAOImpl) erreur de l'execution de getAll()...");
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
	 * get panier by id panier bdd
	 * @param pIdProduit : l'id du panier à rechercher
	 */
	@Override
	public Panier getById(Integer pIdPanier) {
		try {
			ps = this.connection.prepareStatement("SELECT * FROM panier WHERE id_panier = ?");
			
			ps.setInt(1, pIdPanier);
			
			rs = ps.executeQuery();
			
			Panier panier = null;
			
			while(rs.next()) {
				
				panier = new Panier(rs.getInt(1));
				
			}//end while
			
			return panier;
			
		} catch (SQLException e) {
			System.out.println("...(PanierDAOImpl) erreur de l'execution de getById()...");
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
	 * ajouter panier bdd
	 */
	@Override
	public boolean add(Panier pPanier) {
		
		try {
			ps = this.connection.prepareStatement("INSERT INTO panier () values ()");
			
			
			
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(PanierDAOImpl) erreur de l'execution de add()...");
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
	 * modifier panier bdd
	 */
	@Override
	public boolean update(Panier pPanier) {		
		
		return false;
		
	}//end update()

	/**
	 * supprimer panier bdd
	 * @param pIdProduit : l'id du produit à supprimer
	 */
	@Override
	public boolean deleteById(Integer pIdPanier) {
		
		try {
			ps = this.connection.prepareStatement("DELETE FROM panier WHERE id_panier=?");
			
			ps.setInt(1, pIdPanier);
			
			int verif = ps.executeUpdate();
			
			return (verif == 1 );
			
		} catch (SQLException e) {
			System.out.println("...(PanierDAOImpl) erreur de l'execution de deleteByID()...");
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

	@Override
	public boolean panierIsExist(int pIdPanier) {

		try {
			ps = this.connection.prepareStatement("SELECT COUNT(*) FROM panier WHERE id_panier = ?");
			
			ps.setInt(1, pIdPanier);
		
			
			rs = ps.executeQuery();
			
			rs.next();
			
			int verif = rs.getInt(1);
			
			return (verif == 1);
			
		} catch (SQLException e) {
			System.out.println("...(PanierDAOImpl) erreur de l'execution de panierIsExist()...");
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
		
		
	} // panierIsExist()

	@Override
	public int getIdLastAdd() {
		try {
			ps = this.connection.prepareStatement("SELECT * FROM panier ORDER BY id_panier DESC");
			
			
		
			
			rs = ps.executeQuery();
			
			rs.next();
			

			
			return rs.getInt(1);
			
		} catch (SQLException e) {
			System.out.println("...(PanierDAOImpl) erreur de l'execution de getIdLastAdd()...");
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
