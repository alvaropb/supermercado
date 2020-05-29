package com.ipartek.formacion.modelo;

public class Producto {
	private String nombre;
	private int id;
	private String foto;
	private float precio;
	
	public Producto() {
		super();
		this.id = 0;
		this.nombre = "";
		this.precio = 0;
		this.foto = "https://picsum.photos/100/100";
	}
	

	public Producto(String nombre) {
		this();
		this.id = 0;
		this.nombre = nombre;
		this.precio=0;
		this.foto="https://picsum.photos/200/300";
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


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	public float getPrecio() {
		return precio;
	}


	public void setPrecio(float precio) {
		this.precio = precio;
	}


	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", id=" + id + ", foto=" + foto + ", precio=" + precio + "]";
	}




	
}
