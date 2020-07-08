package com.intiformation.ecommerce.test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.intiformation.ecommerce.dao.CategorieDAOImpl;
import com.intiformation.ecommerce.dao.ICategorieDAO;
import com.intiformation.ecommerce.modeles.Categorie;

/**
 * classe de test pour la classe CategorieDAOImpl
 * @author marle
 *
 */
public class CategorieDAOImplTest {
	
	//d�claration de la couche DAO
	private static ICategorieDAO categorieDAO;
	
	//d�claration de variables de type de la classe Categorie
	private static Categorie categorie1;
	private static Categorie categorie2;
	private static Categorie categorie3;
	
	/**
	 * appel�e avant l'execution du premier cas de test
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		
		//instanciation de la couche DAO
		categorieDAO = new CategorieDAOImpl();
		
		//instanciation de categories
		categorie1 = new Categorie(1, "categorie1", "photo1", "description1");
		categorie2 = new Categorie(2, "categorie2", "photo2", "description2");
		categorie3 = new Categorie(3, "categorie3", "photo3", "description3");
		
		//ajout de la categorie1 � la bdd
		categorieDAO.add(categorie1);
	
	}//end setUpBeforeClass
	
	/**
	 * appel�e avant l'appel (execution) de chaque m�thode contenant un 
	 * cas de test
	 */
	@Before
	public void setUp() {

	}//end setUp()
	
	/**
	 * appel�e apr�s l'appel (execution) de chaque m�thode contenant un 
	 * cas de test
	 */
	@After
	public void tearDown() {

	}//end tearDown()
	
	/**
	 * cas de test pour la r�cup�ration d'un utilisateur dans la bdd par son id
	 */
	@Test
	public void testGetById() {
		assertSame("erreur dans la m�thode getById de categorie", categorie1, categorieDAO.getById(1));
	}//end testGetById()
	
	/**
	 * cas de test pour la r�cup�ration de la liste des categories dans la bdd 
	 */
	@Test
	public void testGetAll() {
		
		List<Categorie> listeCategories = new ArrayList<>();
		listeCategories.add(categorie1);
		listeCategories.add(categorie2);
		
		categorieDAO.add(categorie2);
		
		assertTrue("erreur dans la m�thode getAll de categorie", listeCategories == categorieDAO.getAll());
	
	}//end testGetAll()
	
	/**
	 * cas de test pour l'ajout d'une categorie dans la bdd
	 */
	@Test
	public void testAdd() {
		assertTrue("l'ajout de la categorie a �chou�", categorieDAO.add(categorie3));
	}//end testAdd()
	
	/**
	 * cas de test pour la modification d'une categorie dans la bdd
	 */
	@Test
	public void testUpdate() {
		Categorie categorie4 = new Categorie(1, "categorieupdate", "photoupdate", "descriptionupdate");
		assertTrue("la modification de la categorie a �chou�e", categorieDAO.update(categorie4));
	}//end testUpdate()
	
	/**
	 * cas de test pour la suppression d'une categorie dans la bdd 
	 */
	@Test
	public void testDeleteById() {		
		assertTrue("la suppression de la categorie a �chou�e", categorieDAO.deleteById(2));
	}//end testDeleteById()
		
	/**
	 * appel�e apr�s l'execution du dernier cas de test
	 */
	@AfterClass
	public static void tearDownAfterClass() {
		
		//destruction des objets 
		categorieDAO = null;
		categorie1 = null;
		categorie2 = null;
	
	}//end tearDownAfterClass
	
}//end class
