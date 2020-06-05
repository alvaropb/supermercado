package com.ipartek.formacion.modelo;

public class Usuario {

	protected String nombre;
	protected int id;
	protected String contrasenia;
	protected Rol rol;
	
	public Usuario() {
		super();
		this.nombre="";
		this.id=0;
		this.contrasenia="";
		this.rol=new Rol();
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

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", id=" + id + ", contrasenia=" + contrasenia + ", rol=" + rol + "]";
	}




	
}
