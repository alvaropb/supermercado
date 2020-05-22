package com.ipartek.formacion.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.modelo.Usuario;
import com.ipartek.formacion.modelo.UsuarioDAOImpl;

/**
 * Servlet implementation class CrearAlumnosController
 */
@WebServlet("/alumnos-crear")
public class CrearAlumnosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NOMBRE = "nombre";
	private static final String MENSAJE = "mensaje";
	private static final String INSERT_OK = "Se inserto correctamente el alumno en el sistema";
	private static final String INSERT_KO = "Hubo un error a la hora de insertar el alumno";

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
		// recoger el valor del atributo a insertar en la bbdd
		String nombre = request.getParameter(NOMBRE);
		String mensaje = "";
		// llamar al dao
		UsuarioDAOImpl dao = UsuarioDAOImpl.getInstance();
		Usuario pojo = new Usuario();
		pojo.setNombre(nombre);

		try {
			dao.insert(pojo);
			mensaje = INSERT_OK;
		} catch (Exception e) {
			mensaje = INSERT_KO + e.getMessage();
			e.printStackTrace();
		}

		//redireccionar y pasar el mensaje
		request.setAttribute(MENSAJE, mensaje);
		request.getRequestDispatcher("crear-alumnos.jsp").forward(request, response);
	}

}
