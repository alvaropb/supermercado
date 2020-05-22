package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormularioCompletoController
 */
@WebServlet("/formulario-completo")
public class FormularioCompletoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FormularioCompletoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recoger los valores del formulario.jsp
		ArrayList<String>  errores=new ArrayList<String>();
		try {
			//validar nombre, ape etc
			String nombre=request.getParameter("nombre");
			if ("".equalsIgnoreCase(nombre)) {
				errores.add("El nombre esta vacio");
			}
			
			String apellidos=request.getParameter("apellidos");
			if ("".equalsIgnoreCase(apellidos)) {
				errores.add("El apellido esta vacio");
			}
			String contraseina=request.getParameter("contraseina");
			if ("".equalsIgnoreCase(contraseina)) {
				errores.add("La contraseina esta vacia");
			}
			String dni=request.getParameter("dni");
			if ("".equalsIgnoreCase(dni)) {
				errores.add("El dni esta vacia");
			}			
			String sexo=request.getParameter("sexo");
			
			String estudios=request.getParameter("estudios");
			
			String notas=request.getParameter("notas");
			
			String subscribirse=request.getParameter("subscribirse");
			
			//Setear lso valores del formulario jsp
			request.setAttribute("nombre", nombre);
			request.setAttribute("apellidos", apellidos);
			request.setAttribute("contraseina", contraseina);
			request.setAttribute("dni", dni);
			
			request.setAttribute("sexo", sexo);
			
			estudios = estudiosToString(estudios);
			
			request.setAttribute("estudios", estudios);
			
			request.setAttribute("notas", notas);
			
			subscribirse= ("on".equals(subscribirse))?"Subscrito":" No subscrito";
			
			request.setAttribute("subscribirse", subscribirse);
			//errores
			request.setAttribute("errores", errores);
			
		} catch (Exception e) {
				errores.add("Ocurrio un error:"+e.getMessage());
				request.setAttribute("errores", errores);
		
		}finally {
			//Redireccionar a formulario-resumen.jsp o a formulario si es que falta algun dato
			if(errores.size()==0) {
				request.getRequestDispatcher("./cv/formulario-resumen.jsp").forward(request, response);	
			}else {
				request.getRequestDispatcher("./cv/formulario.jsp").forward(request, response);
			}
		}
		
		
		


	}

	/**
	 * Obtener un string con el nombre de los estudios a partir del valor
	 * seleciconado en el select menu de la vista formulario-resumen.jsp
	 * 
	 * @param estudios
	 * @return
	 */
	private String estudiosToString(String estudios) {
		switch (estudios) {
		case "u":
			estudios = "Universitarios";
			break;
		case "p":
			estudios = "FP";
			break;
		case "o":
			estudios = "Otros";
			break;

		default:
			break;
		}
		return estudios;
	}

}
