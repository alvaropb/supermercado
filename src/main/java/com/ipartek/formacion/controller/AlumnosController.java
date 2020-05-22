package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.modelo.Usuario;
import com.ipartek.formacion.modelo.UsuarioDAOImpl;

/**
 * Servlet implementation class AlumnosController
 */
@WebServlet("/ver-tabla-alumnos")
public class AlumnosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// cargar alumnops 
		ArrayList<Usuario> usuarios=new ArrayList<Usuario>();
		try {
			UsuarioDAOImpl dao=UsuarioDAOImpl.getInstance();
			usuarios=dao.getAll();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		//mandar alumnos en la request
		request.setAttribute("alumnos", usuarios);
		//redireccionar
		request.getRequestDispatcher("alumnos.jsp").forward(request, response);
		
	}

}
