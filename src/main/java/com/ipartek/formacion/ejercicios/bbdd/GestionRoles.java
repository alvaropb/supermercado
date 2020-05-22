package com.ipartek.formacion.ejercicios.bbdd;

import java.util.Scanner;

import com.ipartek.formacion.modelo.Rol;
import com.ipartek.formacion.modelo.RolDAOImpl;

public class GestionRoles {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int opcion = 0;

		do {

			System.out.println("Introduzca una opcion");
			menu();

			opcion = Integer.valueOf(sc.nextLine());

			switch (opcion) {
			case 1:
				insertRol();
				break;
			case 2:
				getByIdRol();
				break;
			case 3:
//				getAllRol();
				break;
			case 4:
//				updateRol();
				break;
			case 5:
//				deleteRol();
				break;

			default:
				break;
			}

		} while (opcion != 7);

		sc.close();
		System.out.println("Agur");

	}

	private static void getByIdRol() {
		System.out.println("-----------------------------INICIO GET BY ID ROL-----------------------------");
		RolDAOImpl rolDAOImpl = RolDAOImpl.getInstance();
		Rol rolGetById = new Rol();
		try (Scanner sc = new Scanner(System.in);) {
			System.out.println("intro id a buscar");
			int id = Integer.parseInt(sc.nextLine());
			rolGetById = rolDAOImpl.getById(id);
			System.out.println(rolGetById);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-----------------------------FIN GET BY ID ROL-----------------------------");

	}

	private static void insertRol() {
		System.out.println("-----------------------------INICIO INSERT ROL-----------------------------");
		RolDAOImpl rolDAOImpl = RolDAOImpl.getInstance();
		Rol rol = new Rol();
		try {
			System.out.println("Intro nombre");
			rol.setNombre(sc.nextLine());
			rolDAOImpl.insert(rol);
			System.out.println(rol);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("-----------------------------FIN INSERT ROL-----------------------------");

	}

	private static void menu() {
		System.out.println("1.-insertarRol");
		System.out.println("2.-getByIdRol");
		System.out.println("3.-getAllRol");
		System.out.println("4.-updateRol");
		System.out.println("5.-deleteRol");

	}

}
