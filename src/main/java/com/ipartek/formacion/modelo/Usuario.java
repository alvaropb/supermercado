package com.ipartek.formacion.modelo;

public class Usuario {

	protected String nombre;
	protected int id;

	public Usuario() {
		super();
		this.nombre="";
		this.id=0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", id=" + id + "]";
	}
	
}
