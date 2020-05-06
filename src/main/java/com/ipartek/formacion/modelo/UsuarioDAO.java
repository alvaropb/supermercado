package com.ipartek.formacion.modelo;

import java.util.ArrayList;

public interface UsuarioDAO extends ICrudable<Usuario> {

	
	public ArrayList<Usuario> getAllByName(String nombre)throws Exception;
}
