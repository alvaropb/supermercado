package com.ipartek.formacion.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet(description = "controlador para logout", urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// desconectar de la sesion
		HttpSession sesion=request.getSession();
		sesion.invalidate();
		sesion=null;
		// sacar el idioma de la cookie idioma
		Cookie[] cookies=request.getCookies();
		Cookie cookieIdioma=null;
		String msj="";
		for (int i = 0; i < cookies.length; i++) {
			if ("idioma".equals(cookies[i].getName())) {
				cookieIdioma=cookies[i];
			}
		}
		switch (cookieIdioma.getValue()) {
		case "en":
			msj="Goodbye";
			break;
		case "es":
			msj="Adios";
			break;
		case "eu":
			msj="Agur";
			break;

		default:
			break;
		}
		
		Alerta alerta=new Alerta(msj, "success");
		request.setAttribute("alerta", alerta);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
