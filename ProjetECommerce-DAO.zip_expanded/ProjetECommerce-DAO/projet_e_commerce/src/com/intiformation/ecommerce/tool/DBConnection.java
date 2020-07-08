package com.intiformation.ecommerce.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * utilitaire pour se connecter à la bdd
 * @author marle
 *
 */
public class DBConnection {

	//infos connection bdd
	private static final String DB_URL = "jdbc:mysql://localhost:3306/e_commerce";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	private static final String MYSQL_JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";

	//objet connexion
	private static Connection connection;
	
	/**
	 * ctor en private pour interdire l'instanciation d'un objet DBConnection
	 */
	private DBConnection() {		
	}
	
	/**
	 * récup d'un connexion vers la bdd
	 * @return
	 */
	public static Connection getInstance() {
		
		if (connection == null) {
		
			try {
				
				//chargement du pilote jdbc de mysql
				Class.forName(MYSQL_JDBC_DRIVER_CLASS);
				
				//recup de la connecion
				connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				
			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
				
			}//end catch
	
		}//end if
		
		return connection;
		
	}//end getInstance	
	
}//end class
