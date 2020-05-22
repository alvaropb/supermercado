package com.ipartek.formacion.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 * @see https://github.com/alvaropb/supermercado
 * @author Alvaro
 *
 */
public class ProductoDAOImpl implements ICrudable<Producto> {

	// patron singleton
	private static ProductoDAOImpl INSTANCE = null;
	private final String SQL_INSERT = "INSERT INTO producto(nombre,id_usuario) VALUES(?,?)";
	private final String SQL_SELECT_ID = "SELECT nombre, id FROM producto WHERE id=?;";
	private final String SQL_SELECT_ALL = "SELECT id,nombre FROM producto ORDER BY id DESC;";
	private final String SQL_UPDATE = "UPDATE producto SET nombre=? WHERE id=?;";
	private final String SQL_DELETE = "DELETE FROM producto WHERE id=?";

	private ProductoDAOImpl() {
		super();

	}

	public synchronized static ProductoDAOImpl getInstace() {
		if (INSTANCE == null) {
			INSTANCE = new ProductoDAOImpl();
		}
		return INSTANCE;
	}

	@Override
	public Producto insert(Producto pojo) throws Exception {
		if (pojo == null) {
			throw new Exception("producto es null");
		}

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);) {
			pst.setString(1, pojo.getNombre());
			// le pasamos el id_usuario 1
			pst.setInt(2, 1);
			// se ejecuta la query
			int affectedRows = pst.executeUpdate();
			if (affectedRows != 1) {
				throw new Exception(
						"Hubo un problema a la hora de insertar producto. El nombre " + pojo.getNombre() + " Existe?");

			}
			try (ResultSet rs = pst.getGeneratedKeys();) {
				if (rs.next()) {
					// seteamos el id en el pojo
					pojo.setId(rs.getInt(1));

				}
			} // try

		} // try

		return pojo;
	}

	@Override
	public Producto getById(int id) throws Exception {
		Producto p = new Producto();
		if (id<1) {
			throw new Exception("El id: "+id+" no es valido"); 
		}

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_SELECT_ID);) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				rs.next();
				String nombreObtenido = rs.getString("nombre");
				int idObtenido = rs.getInt("id");
				p.setId(idObtenido);
				p.setNombre(nombreObtenido);

			}//try 

		} //try
		return p;
	}

	@Override
	public ArrayList<Producto> getAll() throws Exception {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		try (// obtener la conexion
				Connection conn = ConnectionManager.getConnection();
				// obtener el preparedStatement
				PreparedStatement pst = conn.prepareStatement(SQL_SELECT_ALL);
				ResultSet rs = pst.executeQuery()) {

			
				while (rs.next()) {
					String nombre = rs.getString("nombre");
					int id = rs.getInt("id");
					Producto p = new Producto(nombre);
					p.setId(id);
					productos.add(p);

				} // while

			

		} catch (Exception e) {
			e.printStackTrace();
		}//try

		return productos;
	}

	@Override
	public Producto update(Producto pojo) throws Exception {


		if (pojo==null) {
			throw new Exception("El producto es nulo");
		}
		try (// establecer la conexion
				Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_UPDATE);
		// establecer el preparedStatement
		) {
			pst.setString(1, pojo.getNombre());
			pst.setInt(2, pojo.getId());
			int affectedRows = pst.executeUpdate();
			if (affectedRows != 1) {
				throw new Exception("Hubo un problema con el update.¿Existe el id= " + pojo.getId() + "?");
				
			} 
		} catch (Exception e) {
			throw new Exception("El nombre del producto ya existe: "+pojo.getNombre());
		}
		return pojo;
	}

	@Override
	public Producto delete(int id) throws Exception {

		Producto producto = new Producto();
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_DELETE);) {
			pst.setInt(1, id);
			//recuperamos el producto altes de eliminar
			producto = getById(id);
			int affectedRows = pst.executeUpdate();
			if (affectedRows != 1) {
				throw new Exception("No se pudo eliminar el producto. ¿id "+id+" existe?");
			}

		} //try

		return producto;
	}

}
