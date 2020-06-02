package com.ipartek.formacion.controller;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(description = "controlador de Log In", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
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
		// recoger parametros desde la request
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		String idioma = request.getParameter("idioma");
		String email=request.getParameter("email");
		String recordar=request.getParameter("recordar");
		HttpSession sesion=request.getSession();
		// TODO validar parametros contra la bbdd
		
		if ("admin".equals(nombre)&&"123456".equals(pass)) {
			
			// si login ok,
			// 1.-guardar en la session el nombre y un boleano isLogeado
			
				// setear atributo de ok en Alerta
			sesion.setAttribute("isLogeado", true);
			sesion.setAttribute("nombre", nombre);
			sesion.setMaxInactiveInterval(60*5);
			//setear mensaje de login ok
			request.setAttribute("alerta", new Alerta("Se logueo correctamente","success"));
			
			
			// idioma
				// recoger  idioma del select one de la vista
					
				// Se setea en una cookie
			if ("elija un idioma".equals(idioma)) {
				idioma="es";
			}
			Cookie cookieIdioma=new Cookie("idioma", idioma);
				// Se le da una duracion a la cookie
			cookieIdioma.setMaxAge(1*60*60*24*365);//1 año
				// se añade la cookie a la response
			response.addCookie(cookieIdioma);
			// Ultima conexion
				// se crea cookie con parametro sysdate
			Date fechaUltimaConexion=new Date();
			Cookie cookieUltimaConexion=new Cookie("ultima_conexion", fechaUltimaConexion.toString().replace(" ", "_"));
				// se le dá una duracion a la cookie
			cookieUltimaConexion.setMaxAge(1*60*60*24*365);
				//se añade la cookie a la response
			response.addCookie(cookieUltimaConexion);
			// cookie recordar
			Cookie cookieRecordar=new Cookie("recordar", recordar);
			// Se le da una duracion a la cookie
			cookieRecordar.setMaxAge(1*60*60*24*365);//1 año
				// se añade la cookie a la response
			response.addCookie(cookieRecordar);
			
			if ("on".equals(recordar)) {
				// cookie email
			Cookie cookieEmail=new Cookie("email", email);
			// Se le da una duracion a la cookie
			cookieEmail.setMaxAge(1*60*60*24*365);//1 año
				// se añade la cookie a la response
			response.addCookie(cookieEmail);
			}else {
				Cookie cookieEmail=new Cookie("email", email);
				// Se le da una duracion a la cookie
				cookieEmail.setMaxAge(0);//0 segundo sdeberia borrar la cookie
					// se añade la cookie a la response
				response.addCookie(cookieEmail);
			}
			
			
			// 2.-redireccionar a index  de login si isLogeado es ok
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			// si login ko
			// 1.- borrar session
			sesion.invalidate();
			sesion=null;
			// 2.- setear atributo de error en Alerta
			request.setAttribute("alerta", new Alerta("Hubo un error en la autentificacion","danger"));
			// redireccionar a login.jsp
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
