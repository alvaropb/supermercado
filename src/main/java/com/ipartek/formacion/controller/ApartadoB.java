package com.ipartek.formacion.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ApartadoB
 */
@WebServlet(description = "Ejercicio apartado b", urlPatterns = { "/apartado-b" })
public class ApartadoB extends HttpServlet {
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
		// recoger el nombre de la vista
		String nombre = request.getParameter("nombre");
		// Hora de inicio: cogida del sistema
		LocalDateTime tiempo = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		String horaSistema = tiempo.format(formatter);
		// navegador
		String navegador = request.getHeader("User-Agent");

		// setear en la sesion los datos
		HttpSession sesion = request.getSession();
		sesion.setAttribute("nombre", nombre);
		sesion.setAttribute("horaSistema", horaSistema);
		sesion.setAttribute("navegador", navegador);
		sesion.setMaxInactiveInterval(60*5);// duracion 5 mins
 
		// redirigir
		request.getRequestDispatcher("resultado-b.jsp").forward(request, response);

	}

}
