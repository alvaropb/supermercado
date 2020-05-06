package com.ipartek.formacion.ejercicios.bbdd;

import java.util.ArrayList;
import java.util.Scanner;

import com.ipartek.formacion.modelo.Producto;
import com.ipartek.formacion.modelo.ProductoDAOImpl;

public class PruebasProductoDAO {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("--------------------inicio getAll--------------------");
		ProductoDAOImpl productoDAO=ProductoDAOImpl.getInstace();

		ArrayList<Producto> productos=new ArrayList<Producto>();
		try {
			productos = productoDAO.getAll();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(productos);
		System.out.println("---------------------fin getAll--------------------");
		
		System.out.println("--------------------inicio getById--------------------");
		Producto p=new Producto();
		int idABuscar=8;
		try {
			p=productoDAO.getById(idABuscar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(p);
		System.out.println("--------------------fin getById--------------------");
		System.out.println("--------------------inicio update--------------------");
		
		Producto pUpdate=new Producto("Patatas Lays Ali-Oli");
		pUpdate.setId(23);
		try {
			productoDAO.update(pUpdate);
			System.out.println(productoDAO.getAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("--------------------fin update--------------------");
		System.out.println("--------------------Inicio insert--------------------");
		Producto pInsert=new Producto("brocoli fresco");
		try {
			productoDAO.insert(pInsert);
			for (Producto producto : productoDAO.getAll()) {
				System.out.println(producto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("--------------------fin insert--------------------");
		System.out.println("--------------------inicio delete--------------------");
		System.out.println("Introduzca id a eliminar");
		int id=Integer.parseInt(sc.nextLine());
		Producto productoDelete=new Producto();
		try {
			productoDelete=productoDAO.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(productoDelete);
		System.out.println("--------------------fin delete--------------------");
	}

}
