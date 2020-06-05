package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.modelo.Usuario;
import com.ipartek.formacion.modelo.UsuarioDAOImpl;

/**
 * Servlet implementation class AlumnosEliminarController
 */
@WebServlet(description = "Eliminar alumno", urlPatterns = { "/alumnos-eliminar" })
public class AlumnosEliminarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LISTADO = "ver-tabla-alumnos";
	private ArrayList<Usuario>usuarios=null;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		int idEliminar=0;
		UsuarioDAOImpl dao=UsuarioDAOImpl.getInstance();
		Alerta alerta=null;
		String view="";
		usuarios=new ArrayList<Usuario>();
		try {
			if (null!=id) {
				// parsear el id
				idEliminar=Integer.valueOf(id);
				
				Usuario usuario=new Usuario();
				usuario=dao.delete(idEliminar);
				usuarios=dao.getAll();
				
				alerta=new Alerta("Eliminado correctamente"+usuario,"success");
				
				view=LISTADO;

			}	
		} catch (Exception e) {
			alerta=new Alerta("Ocurrio un error a la hora de eliminar id="+idEliminar,"danger");
			e.printStackTrace();
			
		}finally {
			
			HttpSession sesion= request.getSession();
			
			
			sesion.setAttribute("alerta", alerta);
			response.sendRedirect(view);
			
		}
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
