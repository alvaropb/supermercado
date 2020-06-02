package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.modelo.Producto;
import com.ipartek.formacion.modelo.ProductoDAOImpl;

/**
 * Servlet implementation class ProductosController
 */
@WebServlet("/productos")
public class ProductosController extends HttpServlet {
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
		String redireccionCards = request.getParameter("cards");

		// llamar a dao
		ProductoDAOImpl productoDAOImpl = ProductoDAOImpl.getInstace();
		ArrayList<Producto> productos = new ArrayList<Producto>();

		try {
			productos = productoDAOImpl.getAll();
		} catch (Exception e) {

			e.printStackTrace();
		}
		// enviar datos a la vista
		request.setAttribute("productos", productos);

		// ir a la nueva vista o jsp
		if (redireccionCards == null) {
			request.getRequestDispatcher("producto.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("productos-card.jsp").forward(request, response);
		}

	}

}
