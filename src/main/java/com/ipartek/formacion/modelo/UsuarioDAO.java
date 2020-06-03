package com.ipartek.formacion.modelo;

import java.util.ArrayList;

public interface UsuarioDAO extends ICrudable<Usuario> {

	
	public ArrayList<Usuario> getAllByName(String nombre)throws Exception;
	/**
	 * Método que retorna un usuario si existe en la bbdd y su contraseña concuerda
	 * @param nombre
	 * @param password
	 * @return Usuario
	 * @throws Exception
	 */
	
	public Usuario existe(String nombre,String password)throws Exception;
}
