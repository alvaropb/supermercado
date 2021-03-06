package com.ipartek.formacion.modelo;

import java.util.ArrayList;

public interface ICrudable<P> {

	//CRUD
	public P insert(P pojo) throws Exception;

	public P getById(int id)throws Exception;
	
	public ArrayList<P>  getAll()throws Exception;
	
	public P update (P pojo)throws Exception;
	
	public P delete(int id)throws Exception;
}

