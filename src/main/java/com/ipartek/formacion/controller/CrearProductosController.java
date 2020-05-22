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
	private static final String MENSAJE = "mensaje";
	private static final String INSERT_OK = "Se inserto correctamente el producto en el sistema.";
	private static final String INSERT_KO = "Hubo un error a la hora de insertar el producto.";

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

		// recoger nombre de la vista
		String nombre = request.getParameter(NOMBRE);
		// quitar blancos
		nombre = nombre.trim();

		String mensaje = "";
		// llamar al DAO para hacer el insert
		ProductoDAOImpl DAO = ProductoDAOImpl.getInstace();
		Producto producto = new Producto(nombre);
		try {

			if (nombre.isEmpty()) {
				throw new Exception(" El nombre del producto no debe estar vacio");
			}
			DAO.insert(producto);
			mensaje = INSERT_OK;

		} catch (Exception e) {

			mensaje = INSERT_KO + e.getMessage();
			e.printStackTrace();

		} finally {

			request.setAttribute(MENSAJE, mensaje);
			// devolver mensaje a la vista
			request.getRequestDispatcher("crear-productos.jsp").forward(request, response);
		}

	}

}
