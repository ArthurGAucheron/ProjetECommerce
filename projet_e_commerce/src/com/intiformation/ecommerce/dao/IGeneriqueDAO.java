package com.intiformation.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.intiformation.ecommerce.tool.DBConnection;

/**
 * interface g�n�rique de base de la couche DAO
 * @author marle
 *
 * @param <T>
 */
public interface IGeneriqueDAO<T> {

	//r�cup�ration de la connection
	public Connection connection = DBConnection.getInstance();
	
	public List<T> getAll();
	
	public T getById(Integer id);
	
	public boolean add(T t);
	
	public boolean update(T t);

	public boolean deleteById(Integer id);
	
}//end interface
