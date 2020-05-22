package com.ipartek.formacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.ipartek.formacion.modelo.ConnectionManager;
import com.ipartek.formacion.modelo.Producto;

public class BuscarProductosNombre {

	public static void main(String[] args) {

		String sql = "SELECT id, nombre FROM producto WHERE nombre LIKE ? ;";

		try (Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(sql);

				Scanner sc = new Scanner(System.in);) {

			System.out.println("Introduzca el nombre a buscar");
			String nombre = sc.nextLine();
			pst.setString(1, "%" + nombre + "%");
			
			try(ResultSet rs = pst.executeQuery();) {
				System.out.println("lista de productos");
				while (rs.next()) {
					Producto p=new Producto();
					p.setId(Integer.valueOf(rs.getString("id")));
					p.setNombre(rs.getString("nombre"));
					System.out.println(p);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
