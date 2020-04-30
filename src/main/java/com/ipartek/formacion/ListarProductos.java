package com.ipartek.formacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ListarProductos {

	public static void main(String[] args) {

		final String URL_CONEXION = "jdbc:mysql://localhost:3306/supermercado";
		final String USUARIO = "debian-sys-maint";
		final String PASS = "o8lAkaNtX91xMUcV";
		final String SQL = "SELECT id, nombre FROM producto ORDER BY nombre ASC";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Comprobar que Existe el .jar de mysql");
			// conectarse a la bbdd supermercado			
			Connection conexion = DriverManager.getConnection (URL_CONEXION,USUARIO, PASS);
			System.out.println("conexion con exito");
			//realizar una consulta
			PreparedStatement ps=conexion.prepareStatement(SQL);
			ResultSet rs= ps.executeQuery();
			
			//pintar los resultados
			System.out.println("Listado de productos");
			System.out.println("--------------------------------------------");
			while (rs.next()) {
				int id=rs.getInt("id");
				String nombre=rs.getString("nombre");
				System.out.println("id="+id+" nombre= " +nombre);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}



	}

}
