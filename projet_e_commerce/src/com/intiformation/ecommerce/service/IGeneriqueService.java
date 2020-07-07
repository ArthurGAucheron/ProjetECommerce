package com.intiformation.ecommerce.service;

import java.util.List;

/**
 * interface générique de base de la couche Service
 * @author marle
 *
 */
public interface IGeneriqueService<T> {

	public List<T> findAll();
	
	public T findById(Integer id);
	
	public boolean ajouter(T t);
	
	public boolean modifier(T t);

	public boolean supprimerById(Integer id);
	
}//end interface
