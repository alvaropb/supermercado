package com.ipartek.formacion.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.modelo.Producto;
import com.ipartek.formacion.modelo.ProductoDAOImpl;

/**
 * Servlet implementation class CrearProductosController
 */
@WebServlet("/productos-crear")
public class CrearProductosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NOMBRE = "nombre";
	private static final String PRECIO = "precio";
	private static final String IMAGEN = "imagen";
	private static final String ALERTA = "alerta";
	private static final String INSERT_OK = "Se inserto correctamente el producto en el sistema.";
	private static final String EDIT_OK = "Se edito correctamente el producto en el sistema.";
	private static final String INSERT_KO = "Hubo un error a la hora de insertar el producto.";


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recoger el id y el nombre para pintarlo oculto en la vista
		
		
		
	
		try {
			Producto p=new Producto();
			String idParametro=request.getParameter("id");
			if (idParametro!=null) {
				int id=Integer.parseInt(idParametro);
				// estamos editando, hay que recuperar el producto de la bbdd para enviarlo a la vista
				ProductoDAOImpl DAO = ProductoDAOImpl.getInstace();
				p=DAO.getById(id);
				request.setAttribute("producto", p);	
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			
			
			request.getRequestDispatcher("crear-productos.jsp").forward(request, response);
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		// recoger parametros de la vista
		String nombre = request.getParameter(NOMBRE);
		String precio = request.getParameter(PRECIO);
		String foto = request.getParameter(IMAGEN);

		// quitar blancos
		nombre = nombre.trim();
		
		Alerta alerta = new Alerta();
		Producto producto = null;
		try {
			producto = new Producto(nombre);
			producto.setNombre(nombre);
			producto.setFoto(foto);
			// recoger la id de la vista, será null si venimos de crear desde el link del index
			
			String idP = request.getParameter("id");
			int id=0;
			if(!idP.isEmpty()&&idP!=null ) {
				id=Integer.parseInt(idP);	
			}
			producto.setId(id);
			//TODO hacer validaciones del precio
			Float precioFloat=Float.parseFloat(precio);
			// llamar al DAO para hacer el insert o el update
			ProductoDAOImpl DAO = ProductoDAOImpl.getInstace();
			
			
			
			
			producto.setPrecio(precioFloat);
			
			
			if(id>0){
				// estamos editando, hay que llamar a editar del dao
				
				DAO.update(producto);
				alerta = new Alerta(EDIT_OK,"success");
			}else {
				// estamos creando, hay que llamar a insertar
				
				if (nombre.isEmpty()) {
					throw new Exception(" El nombre del producto no debe estar vacio");
				}
				DAO.insert(producto);
				alerta = new Alerta(INSERT_OK,"success");
				
				
			}//else
		} catch (Exception e) {
			alerta = new Alerta(INSERT_KO + e.getMessage(),"danger");
			
			e.printStackTrace();
			
		}finally {
			request.setAttribute("producto", producto);
			request.setAttribute(ALERTA, alerta);
			// devolver mensaje a la vista
			request.getRequestDispatcher("crear-productos.jsp").forward(request, response);
		}


	}

}
