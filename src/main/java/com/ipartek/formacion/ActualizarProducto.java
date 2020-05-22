package com.ipartek.formacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.ipartek.formacion.modelo.ConnectionManager;
import com.ipartek.formacion.modelo.Producto;

public class ActualizarProducto {

	public static void main(String[] args) {


		String sql = "UPDATE producto SET nombre=? WHERE id=?";
		final String SQL_UPDATE = "SELECT id, nombre FROM producto ORDER BY id DESC";
		try (Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(sql);

				Scanner sc = new Scanner(System.in);) {
			
			//listar productos
			PreparedStatement ps=conexion.prepareStatement(SQL_UPDATE);
			try (ResultSet rs= ps.executeQuery();){
				//pintar los resultados
				System.out.println("Listado de productos");
				System.out.println("--------------------------------------------");
				while (rs.next()) {
					int id=rs.getInt("id");
					String nombre=rs.getString("nombre");
					System.out.println("id="+id+" nombre= " +nombre);
					
				}
				
			} catch (Exception e) {
				// TODO: handle exce12ption
			}
			
			
			

			System.out.println("Introduzca el id del producto que desea cambiar");
			int id =Integer.parseInt(sc.nextLine());
			System.out.println("Introduzca el nombre a editar");
			String nombre = sc.nextLine();
			pst.setInt(2, id);
			pst.setString(1,  nombre);
			
			int filasAfectadas= pst.executeUpdate();
				if (filasAfectadas==1) {
					System.out.println("modificado con exito");
				}else {
					System.out.println("Ocurrió un error a la hora de modificar. ID= +id+ existe?");
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
