package com.ipartek.formacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;





public class HelloWorld {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conexion = DriverManager.getConnection(
					   "jdbc:mysql://localhost:3306/supermercado",
					   "debian-sys-maint",
					   "o8lAkaNtX91xMUcV"); 
			Statement statement=conexion.createStatement();
	
			conexion.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		

	}

}
