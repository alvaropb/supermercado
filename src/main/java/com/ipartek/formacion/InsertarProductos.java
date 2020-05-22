package com.ipartek.formacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class InsertarProductos {

	public static void main(String[] args) {

		final String URL_CONEXION = "jdbc:mysql://localhost:3306/supermercado";
		final String USUARIO = "debian-sys-maint";
		final String PASS = "o8lAkaNtX91xMUcV";
		final String SQL = "INSERT INTO producto (nombre,id_usuario) VALUES (?,1)";
		Scanner sc = new Scanner(System.in);
		boolean seguir = true;

		do {

			try (Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO, PASS);
				PreparedStatement ps = conexion.prepareStatement(SQL);){
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Comprobar que Existe el .jar de mysql");
				// conectarse a la bbdd supermercado
				
				System.out.println("conexion con exito");
				// realizar una insercion
				

				System.out.println("Introduzca el nombre del producto a insertar");

				ps.setString(1, sc.nextLine());
				
				int affectedRows = ps.executeUpdate();
				System.out.println("Se actualizaron " + affectedRows + " filas");
				seguir=false;

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("El nombre esta repetido, pruebe con otro");
			}
		} while (seguir);

	}

}
