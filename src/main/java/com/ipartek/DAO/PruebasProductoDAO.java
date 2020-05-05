package com.ipartek.DAO;

import java.util.ArrayList;
import java.util.Scanner;

import com.ipartek.formacion.modelo.Producto;

public class PruebasProductoDAO {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("--------------------inicio getAll--------------------");
		ProductoDAOImpl productoDAO=ProductoDAOImpl.getInstace();

		ArrayList<Producto>productos=productoDAO.getAll();
		System.out.println(productos);
		System.out.println("---------------------fin getAll--------------------");
		
		System.out.println("--------------------inicio getById--------------------");
		Producto p=new Producto();
		int idABuscar=8;
		p=productoDAO.getById(idABuscar);
		System.out.println(p);
		System.out.println("--------------------fin getById--------------------");
		System.out.println("--------------------inicio update--------------------");
		
		Producto pUpdate=new Producto("Patatas Lays Ali-Oli");
		pUpdate.setId(23);
		productoDAO.update(pUpdate);
		System.out.println(productoDAO.getAll());
		System.out.println("--------------------fin update--------------------");
		System.out.println("--------------------Inicio insert--------------------");
		Producto pInsert=new Producto("brocoli fresco");
		productoDAO.insert(pInsert);
		for (Producto producto : productoDAO.getAll()) {
			System.out.println(producto);
		}
		
		System.out.println("--------------------fin insert--------------------");
		System.out.println("--------------------inicio delete--------------------");
		System.out.println("Introduzca id a eliminar");
		int id=Integer.parseInt(sc.nextLine());
		Producto productoDelete=new Producto();
		productoDelete=productoDAO.delete(id);
		System.out.println(productoDelete);
		System.out.println("--------------------fin delete--------------------");
	}

}
