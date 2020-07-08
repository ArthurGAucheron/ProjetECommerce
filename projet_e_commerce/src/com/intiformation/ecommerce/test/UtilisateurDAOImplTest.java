package com.intiformation.ecommerce.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.intiformation.ecommerce.dao.IUtilisateurDAO;
import com.intiformation.ecommerce.dao.UtilisateurDAOImpl;
import com.intiformation.ecommerce.modeles.Utilisateur;

/**
 * classe de test pour la classe UtilisateurDAOImpl
 * @author marle
 *
 */
public class UtilisateurDAOImplTest {
	
	//déclaration de la couche DAO
	private static IUtilisateurDAO utilisateurDAO;
	
	//déclaration de variables de type de la classe Utilisateur
	private static Utilisateur utilisateur1;
	private static Utilisateur utilisateur2;
	private static Utilisateur utilisateur3;
	
	//ajout d'une liste utilisateurs
	private static List<Utilisateur> listeUtilisateurs = new ArrayList<>();
	
	/**
	 * appelée avant l'execution du premier cas de test
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		
		//instanciation de la couche DAO
		utilisateurDAO = new UtilisateurDAOImpl();
		
		//instanciation d'utilisateur
		utilisateur1 = new Utilisateur("nom1", "mdp1", 1);
		utilisateur2 = new Utilisateur("nom2", "mdp2", 1);
		utilisateur3 = new Utilisateur("nom3", "mdp3", 1);
		
		//ajout d'une liste utilisateurs
		listeUtilisateurs.add(utilisateur1);
		listeUtilisateurs.add(utilisateur2);
		
		//ajout de l'utilisateur à la bdd
		utilisateurDAO.add(utilisateur1);
		utilisateurDAO.add(utilisateur2);
	
	}//end setUpBeforeClass
	
	/**
	 * appelée avant l'appel (execution) de chaque méthode contenant un 
	 * cas de test
	 */
	@Before
	public void setUp() {

	}//end setUp()
	
	/**
	 * appelée après l'appel (execution) de chaque méthode contenant un 
	 * cas de test
	 */
	@After
	public void tearDown() {

	}//end tearDown()
	
	/**
	 * cas de test pour la récupération d'un utilisateur dans la bdd par son id
	 */
	@Test
	public void testGetById() {
		assertEquals(utilisateur1.getIdUtilisateur(), utilisateurDAO.getById(1).getIdUtilisateur());	
		assertEquals(utilisateur1.getNomUtilisateur(), utilisateurDAO.getById(1).getNomUtilisateur());	
		assertEquals(utilisateur1.getPasswordUtilisateur(), utilisateurDAO.getById(1).getPasswordUtilisateur());	
		assertEquals(utilisateur1.getActived(), utilisateurDAO.getById(1).getActived());	
	}//end testGetById()
	
	/**
	 * cas de test pour la récupération de la liste des utilisateurs dans la bdd 
	 */
	@Test
	public void testGetAll() {
		for(int i=0;i<listeUtilisateurs.size();i++) {
			assertEquals(listeUtilisateurs.get(i).getIdUtilisateur(), utilisateurDAO.getAll().get(i).getIdUtilisateur());	
			assertEquals(listeUtilisateurs.get(i).getNomUtilisateur(), utilisateurDAO.getAll().get(i).getNomUtilisateur());	
			assertEquals(listeUtilisateurs.get(i).getPasswordUtilisateur(), utilisateurDAO.getAll().get(i).getPasswordUtilisateur());	
			assertEquals(listeUtilisateurs.get(i).getActived(), utilisateurDAO.getAll().get(i).getActived());			
		}
	}//end testGetAll()
	
	/**
	 * cas de test pour l'ajout d'un utilisateur dans la bdd
	 */
	@Test
	public void testAdd() {
		assertTrue("l'ajout de l'utilisateur a échoué", utilisateurDAO.add(utilisateur3));
	}//end testAdd()
	
	/**
	 * cas de test pour la modification d'un utilisateur dans la bdd
	 */
	@Test
	public void testUpdate() {
		Utilisateur utilisateur4 = new Utilisateur(2, "nom2update", "mdp2update", 0);
		assertTrue("la modification de l'utilisateur a échouée", utilisateurDAO.update(utilisateur4));
	}//end testUpdate()
	
	/**
	 * cas de test pour l'authentification d'un utilisateur dans la bdd 
	 */
	@Test
	public void TestIsUtilisateurExists() {		
		assertTrue("l'authentification de l'utilisateur a échouée", utilisateurDAO.isUtilisateurExists("nom1", "mdp1"));
	}//end testDeleteById()
	
	/**
	 * cas de test pour la suppression d'un utilisateur dans la bdd 
	 */
	@Test
	public void testDeleteById() {		
		assertTrue("la suppression de l'utilisateur a échouée", utilisateurDAO.deleteById(3));
	}//end testDeleteById()
		
	/**
	 * appelée après l'execution du dernier cas de test
	 */
	@AfterClass
	public static void tearDownAfterClass() {
		
		//destruction des objets 
		utilisateurDAO = null;
		utilisateur1 = null;
		utilisateur2 = null;
		utilisateur3 = null;
		
		utilisateurDAO.deleteById(1);
		utilisateurDAO.deleteById(2);
	
	}//end tearDownAfterClass
	
}//end class
