package com.ipartek.formacion.controller;

public class Alerta {

	private String texto;
	private String tipo;

	public Alerta() {
		super();
		this.texto = "";
		this.tipo = "";
	}

	public Alerta(String texto, String tipo) {
		this();
		this.texto = texto;
		this.tipo = tipo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
