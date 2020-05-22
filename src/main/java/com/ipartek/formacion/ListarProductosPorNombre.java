package com.ipartek.formacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ListarProductosPorNombre {

	public static void main(String[] args) {

		final String URL_CONEXION = "jdbc:mysql://localhost:3306/supermercado";
		final String USUARIO = "debian-sys-maint";
		final String PASS = "o8lAkaNtX91xMUcV";
		final String SQL = "SELECT id, nombre FROM producto WHERE nombre LIKE ? ORDER BY id DESC";

		try(Scanner sc=new Scanner(System.in);
			Connection conexion = DriverManager.getConnection (URL_CONEXION,USUARIO, PASS);
			PreparedStatement ps=conexion.prepareStatement(SQL);) {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Comprobar que Existe el .jar de mysql");
			// conectarse a la bbdd supermercado			
			
			System.out.println("conexion con exito");
			//realizar una consulta
			
			System.out.println("Intro nombre a buscar");
			String nombreABuscar=sc.nextLine();
			ps.setString(1, "%"+nombreABuscar+"%");
			try(ResultSet rs= ps.executeQuery();) {
				//pintar los resultados
				System.out.println("Listado de productos");
				System.out.println("--------------------------------------------");
				while (rs.next()) {
					int id=rs.getInt("id");
					String nombre=rs.getString("nombre");
					System.out.println("id="+id+" nombre= " +nombre);
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}



	}

}
