package com.ipartek.formacion.ejercicios.bbdd;

import java.util.Scanner;

import com.ipartek.formacion.modelo.Usuario;
import com.ipartek.formacion.modelo.UsuarioDAOImpl;

public class GestionAlumnos {

	public static void main(String[] args) {

		//insertUsuario();
		
		getByIdUsuario();

	}

	private static void getByIdUsuario() {
		System.out.println("-----------------------------INICIO GET BY ID USUARIO-----------------------------");
		UsuarioDAOImpl usuarioDAOImpl = UsuarioDAOImpl.getInstance();
		Usuario usuarioGetById = new Usuario();
		try (Scanner sc = new Scanner(System.in);) {
			System.out.println("intro id a buscar");
			int id = Integer.parseInt(sc.nextLine());
			usuarioGetById=usuarioDAOImpl.getById(id);
			System.out.println(usuarioGetById);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-----------------------------FIN GET BY ID USUARIO-----------------------------");
	}

	private static void insertUsuario() {
		System.out.println("-----------------------------INICIO INSERT USUARIO-----------------------------");
		UsuarioDAOImpl usuarioDAOImpl = UsuarioDAOImpl.getInstance();
		Usuario usuario = new Usuario();
		try (Scanner sc = new Scanner(System.in);) {
			System.out.println("Intro nombre");
			usuario.setNombre(sc.nextLine());
			usuarioDAOImpl.insert(usuario);
			System.out.println(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("-----------------------------FIN INSERT USUARIO-----------------------------");
	}

}
