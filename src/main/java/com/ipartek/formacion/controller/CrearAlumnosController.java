package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.modelo.Rol;
import com.ipartek.formacion.modelo.RolDAOImpl;
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
	private static final String UPDATE_OK = "Se actualizó correctamente el alumno en el sistema";
	private static final String INSERT_KO = "Hubo un error a la hora de insertar el alumno";
	private static final String CREAR_ALUMNOS_JSP = "crear-alumnos.jsp";
	private static final String LISTAR_ALUMNOS_JSP = "alumnos.jsp";
	
	private ArrayList<Usuario>usuarios;
	private ArrayList<Rol>roles;
	private RolDAOImpl rolDao;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("id");
		String view="";
		
		int idBuscar=0;
		Usuario usuario=new Usuario();
		usuarios=new ArrayList<Usuario>();
		roles=new ArrayList<Rol>();
		
		UsuarioDAOImpl dao=UsuarioDAOImpl.getInstance();
		rolDao=RolDAOImpl.getInstance();
		try {
			if (id!=null &&!id.isEmpty()&&!"0".equals(id)) {// editamos uno existente
				idBuscar=Integer.parseInt(id);
				
				usuario=dao.getById(idBuscar);
				request.setAttribute("usuario", usuario);
				
				roles=rolDao.getAll();
				request.setAttribute("roles", roles);
				
				view=CREAR_ALUMNOS_JSP;
				
			}else if("0".equals(id)) {// creamos uno nuevo
				
				roles=rolDao.getAll();
				
				request.setAttribute("roles", roles);
				request.setAttribute("usuario", new Usuario());
				
				view=CREAR_ALUMNOS_JSP;
			}			
			else {// vamos al listado
				
				usuarios=dao.getAll();
				request.setAttribute("alumnos", usuarios);
				// setear la redireccion a la vista listado de usuarios
				view=LISTAR_ALUMNOS_JSP;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			request.getRequestDispatcher(view).forward(request, response);
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mensaje = "";
		Usuario usuario=new Usuario();
		try {
		// 											recoger el valor del atributo a insertar en la bbdd
		String nombre = request.getParameter(NOMBRE);
		String id = request.getParameter("id");
		String pass=request.getParameter("pass");
		

		
		String idRol=request.getParameter("id_rol");
		int idBuscar=0;
		// 											seteamos valores en el pojo usuario
		
		usuario.setNombre(nombre);
		
		
		Rol rol=new Rol();
		rol.setId(Integer.parseInt(idRol));
		
		usuario.setRol(rol);
				
		UsuarioDAOImpl dao=UsuarioDAOImpl.getInstance();
		rolDao=RolDAOImpl.getInstance();
		
		roles=rolDao.getAll();
			
		request.setAttribute("roles", roles);
		
		if ("contraseinas no coinciden".equals(pass)) {
			throw new Exception("contraseinas no coinciden");
		}			
		usuario.setContrasenia(pass);	
		
			if (!"0".equals(id)) {
				//editamos
				idBuscar=Integer.parseInt(id);
				usuario.setId(idBuscar);
				
				// llamar al dao
				dao.update(usuario);
				mensaje =UPDATE_OK;
			}else {
				//creamos
				// llamar al dao
				
				dao.insert(usuario);
				mensaje = INSERT_OK;
			}
			
		} catch (Exception e) {
			mensaje = INSERT_KO + e.getMessage();
			e.printStackTrace();
		}finally {
			//redireccionar y pasar el mensaje
			request.setAttribute("roles", roles);
			
			request.setAttribute("usuario", usuario);
			request.setAttribute(MENSAJE, mensaje);
			request.setAttribute("alerta", new Alerta(mensaje, "success"));
			request.getRequestDispatcher("crear-alumnos.jsp").forward(request, response);
		}
		
		


	

	

	}

}
