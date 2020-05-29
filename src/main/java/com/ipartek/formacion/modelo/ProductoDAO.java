package com.ipartek.formacion.modelo;

import java.util.ArrayList;

public interface ProductoDAO extends ICrudable<Producto> {

	public ArrayList<Producto> getAllByPrice(Float min, Float max)throws Exception;
}
