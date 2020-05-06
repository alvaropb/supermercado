package com.ipartek.formacion.ejercicios.bbdd;

import java.util.ArrayList;
import java.util.Scanner;

import com.ipartek.formacion.modelo.Usuario;
import com.ipartek.formacion.modelo.UsuarioDAOImpl;

public class GestionAlumnos {

	public static void main(String[] args) {

		// insertUsuario();

		// getByIdUsuario();
		//getAllUsuario();
		// updateUsuario();
		// deleteUsuario();
		getAllByName();

	}

	private static void getAllByName() {
		System.out.println("-----------------------------INICIO GET_ALL_BY_NAME USUARIO-----------------------------");
		ArrayList<Usuario>usuarios=new ArrayList<Usuario>();
		UsuarioDAOImpl usuarioDAOImpl=UsuarioDAOImpl.getInstance();
		// pedir por pantalla nombre a buscar
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Intro nombre a buscar");
			String nombreABuscar=sc.nextLine();
			usuarios=usuarioDAOImpl.getAllByName(nombreABuscar);
			//pintar registros por pantalla
			for (Usuario usuario : usuarios) {
				System.out.println(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-----------------------------FIN GET_ALL_BY_NAME USUARIO-----------------------------");

	}

	private static void deleteUsuario() {
		System.out.println("-----------------------------INICIO DELETE USUARIO-----------------------------");
		UsuarioDAOImpl usuarioDADaoImpl = UsuarioDAOImpl.getInstance();
		Usuario usuario = new Usuario();
		int idUsuario = 0;
		try (Scanner sc = new Scanner(System.in)) {
			// listamos primero los usuarios
			getAllUsuario();
			System.out.println("Intro id del usuario a borrar");
			idUsuario = Integer.valueOf(sc.nextLine());
			usuario = usuarioDADaoImpl.delete(idUsuario);
			System.out.println("El usuario eliminado es: " + usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("-----------------------------FIN DELETE USUARIO-----------------------------");

	}

	private static void updateUsuario() {
		System.out.println("-----------------------------INICIO UPDATE USUARIO-----------------------------");
		UsuarioDAOImpl usuarioDAOImpl = UsuarioDAOImpl.getInstance();
		Usuario usuario = new Usuario();
		// pintamos los usuarios
		getAllUsuario();
		try (Scanner sc = new Scanner(System.in)) {
			// setear campos del usuario a modificar
			System.out.println("Intro id del usuario:");
			usuario.setId(Integer.valueOf(sc.nextLine()));
			System.out.println("Intro nombre que quiere establecer:");
			usuario.setNombre(sc.nextLine());
			// lanzamos la consulta
			usuario = usuarioDAOImpl.update(usuario);
			// pintamos usuario
			System.out.println("usuario modificado :" + usuario);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("-----------------------------FIN UPDATE USUARIO-----------------------------");

	}

	private static void getAllUsuario() {
		System.out.println("-----------------------------INICIO GET ALL USUARIO-----------------------------");
		UsuarioDAOImpl usuarioDAOImpl = UsuarioDAOImpl.getInstance();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			usuarios = usuarioDAOImpl.getAll();
			for (Usuario usuario : usuarios) {
				System.out.println(usuario);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println("-----------------------------FIN GET ALL USUARIO-----------------------------");

	}

	private static void getByIdUsuario() {
		System.out.println("-----------------------------INICIO GET BY ID USUARIO-----------------------------");
		UsuarioDAOImpl usuarioDAOImpl = UsuarioDAOImpl.getInstance();
		Usuario usuarioGetById = new Usuario();
		try (Scanner sc = new Scanner(System.in);) {
			System.out.println("intro id a buscar");
			int id = Integer.parseInt(sc.nextLine());
			usuarioGetById = usuarioDAOImpl.getById(id);
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
