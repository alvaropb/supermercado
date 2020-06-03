package com.ipartek.formacion.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApartadoA
 */
@WebServlet(description = "servlet del apartado a", urlPatterns = { "/apartado-a" })
public class ApartadoA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recoger parametros
		String  nombre=request.getParameter("nombre");
		String  color=request.getParameter("color");
		
		//crear las cookies y añadirlas
		
		Cookie cNombre=new Cookie("nombre", nombre);
		cNombre.setMaxAge(1*60*60*24*365);//un año
		response.addCookie(cNombre);
		
		Cookie cColor=new Cookie("color", color);
		cColor.setMaxAge(1*60*60*24*365);//un año
		response.addCookie(cColor);
		
		// redirigir a resultado-a.jsp
		request.getRequestDispatcher("resultado-a.jsp").forward(request, response);
		
	}

}
