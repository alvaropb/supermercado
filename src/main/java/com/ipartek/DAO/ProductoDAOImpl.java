package com.ipartek.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ipartek.formacion.modelo.ConnectionManager;
import com.ipartek.formacion.modelo.Producto;

public class ProductoDAOImpl implements DAO<Producto> {

	// patron singleton
	private static ProductoDAOImpl INSTANCE = null;

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
	public Producto insert(Producto pojo) {
		String SQL = "INSERT INTO producto(nombre,id_usuario) VALUES(?,?)";
		try (Connection conn = ConnectionManager.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(SQL,PreparedStatement.RETURN_GENERATED_KEYS);) {
			pst.setString(1, pojo.getNombre());
			pst.setInt(2, 1);
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				System.out.println("Insert correcto");
				
			}
			try (ResultSet rs =pst.getGeneratedKeys();){
				if (rs.next()) {
					pojo.setId(rs.getInt(1));
					
				}
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pojo;
	}

	@Override
	public Producto getById(int id) {
		Producto p = new Producto();
		String SQL = "SELECT nombre, id FROM producto WHERE id=?;";
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(SQL);) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				rs.next();
				String nombreObtenido = rs.getString("nombre");
				int idObtenido = rs.getInt("id");
				p.setId(idObtenido);
				p.setNombre(nombreObtenido);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public ArrayList<Producto> getAll() {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		String SQL = "SELECT id,nombre FROM producto;";

		try (// obtener la conexion
				Connection conn = ConnectionManager.getConnection();
				// obtener el preparedStatement
				PreparedStatement pst = conn.prepareStatement(SQL);) {

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					String nombre = rs.getString("nombre");
					int id = rs.getInt("id");
					Producto p = new Producto(nombre);
					p.setId(id);
					productos.add(p);

				} // while

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return productos;
	}

	@Override
	public Producto update(Producto pojo) {
		Producto productoReturn = new Producto();
		String SQL = "UPDATE producto SET nombre=? WHERE id=?;";

		try (// establecer la conexion
				Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL);
		// establecer el preparedStatement
		) {
			pst.setString(1, pojo.getNombre());
			pst.setInt(2, pojo.getId());
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				System.out.println("update correcto");
				productoReturn=getById(pojo.getId());
			} else {
				System.out.println("Hubo un problema con el update.¿Existe el id= " + pojo.getId() + "?");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productoReturn;
	}

	@Override
	public Producto delete(int id) {
		String SQL="DELETE FROM producto WHERE id=?";
		Producto producto=new Producto();
		try (Connection conn=ConnectionManager.getConnection();
				PreparedStatement pst=conn.prepareStatement(SQL);){
			pst.setInt(1, id);
			producto=getById(id);
			int affectedRows=pst.executeUpdate();
			if (affectedRows==1) {
				System.out.println("Borrado de producto con id "+id+" correcto");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}//try catch
		
		return producto;
	}

}
