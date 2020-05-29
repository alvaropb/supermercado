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
public class ProductoDAOImpl implements ProductoDAO{

	// patron singleton
	private static ProductoDAOImpl INSTANCE = null;
	private final String SQL_INSERT = "INSERT INTO producto(nombre,id_usuario,precio,imagen) VALUES(?,?,?,?)";
	private final String SQL_SELECT_ID = "SELECT nombre, id, imagen, precio FROM producto WHERE id=?;";
	private final String SQL_SELECT_ALL = "SELECT id,nombre,imagen,precio FROM producto ORDER BY id DESC;";
	private final String SQL_SELECT_BY_PRICE = "SELECT id,nombre,imagen,precio FROM producto WHERE precio BETWEEN ? AND ? ORDER BY precio DESC;";
	private final String SQL_UPDATE = "UPDATE producto SET nombre=?,precio=?,imagen=? WHERE id=?;";
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
			// se le pasa precio e imagen
			pst.setFloat(3, pojo.getPrecio());
			pst.setString(4, pojo.getFoto());
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
				String foto = rs.getString("imagen");
				float precio = rs.getFloat("precio");
				
				
				p.setId(idObtenido);
				p.setNombre(nombreObtenido);
				p.setFoto(foto);
				p.setPrecio(precio);
				

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
					String foto = rs.getString("imagen");
					float precio = rs.getFloat("precio");
					
					Producto p = new Producto(nombre);
					p.setId(id);
					p.setFoto(foto);
					p.setPrecio(precio);
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
		// establecer el preparedStatement UPDATE producto SET nombre=?,precio=?,imagen=? WHERE id=?;"
		) {
			pst.setString(1, pojo.getNombre());
			
			pst.setFloat(2, pojo.getPrecio());
			pst.setString(3, pojo.getFoto());
			pst.setInt(4, pojo.getId());
			
			
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

	@Override
	public ArrayList<Producto> getAllByPrice(Float min, Float max) throws Exception {
		ArrayList<Producto> productosReturn=new ArrayList<Producto>();
			
		try (Connection conn=ConnectionManager.getConnection();
			PreparedStatement pst=conn.prepareStatement(SQL_SELECT_BY_PRICE)	
				){
			//setear los valores max y min sobre lo que buscar
			pst.setFloat(1, min);
			pst.setFloat(2, max);
			try (ResultSet rs=pst.executeQuery()){
				while (rs.next()) {
					Producto p=new Producto();
					p.setId(rs.getInt("id"));
					p.setNombre(rs.getString("nombre"));
					p.setPrecio(rs.getFloat("precio"));
					p.setFoto(rs.getString("imagen"));
					productosReturn.add(p);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return productosReturn;
	}


}
