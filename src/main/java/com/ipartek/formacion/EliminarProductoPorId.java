package com.ipartek.formacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.ipartek.formacion.modelo.ConnectionManager;


public class EliminarProductoPorId {

	public static void main(String[] args) {


		String sql = " DELETE FROM producto WHERE id=?;";

		try (Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(sql);

				Scanner sc = new Scanner(System.in);) {

			System.out.println("Introduzca el id a eliminar");
			int id= Integer.valueOf(sc.nextLine());
			pst.setInt(1, id);
			
			
				pst.executeUpdate();
		
			
						
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
