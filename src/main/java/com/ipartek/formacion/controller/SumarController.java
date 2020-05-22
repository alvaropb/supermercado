package com.ipartek.formacion.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SumarController
 */
@WebServlet("/sumar")
public class SumarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametro1=request.getParameter("op1");
		String parametro2=request.getParameter("op2");
		//TODO parsear y sumar enteros
		String resultado=""+(Integer.parseInt(parametro1) +Integer.parseInt(parametro2));
		
		request.setAttribute("resultado", resultado);
		request.getRequestDispatcher("getYpost.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String parametro1 = null;
		String parametro2 = null;
		String resultado="";

		try {
			
			parametro1 = request.getParameter("op1");
			parametro2 = request.getParameter("op2");
			request.setAttribute("op1", parametro1);
			request.setAttribute("op2", parametro2);
			resultado=""+(Integer.parseInt(parametro1)+Integer.parseInt(parametro2));
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			resultado="ocurrio un error inesperado con parametro1= "+parametro1+" parametro2"+parametro2+e.getMessage();

		}finally {
			
			request.setAttribute("resultado", resultado);
			request.getRequestDispatcher("getYpost.jsp").forward(request, response);
		}


	}

}
