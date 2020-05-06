package com.ipartek.formacion.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAOImpl implements UsuarioDAO {

	private final String SQL_GET_ALL_BY_NAME = "SELECT nombre,id FROM usuario WHERE nombre LIKE ?";
	private final String SQL_UPDATE_USUARIO = "UPDATE usuario SET nombre=? WHERE id=?";
	private final String SQL_DELETE_USUARIO = "DELETE FROM usuario WHERE id=?";
	private final String SQL_GET_ALL_USUARIO = "SELECT nombre,id FROM usuario ORDER BY id DESC";
	private final String SQL_INSERT_USUARIO = "INSERT INTO usuario (nombre,contrasenia,id_rol) VALUES(?,?,?)";
	private final String SQL_GET_BY_ID_USUARIO = "SELECT nombre, id FROM usuario WHERE id=?";
	// INICIO patron singleton
	private static UsuarioDAOImpl INSTANCE = null;

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAOImpl();
		}
	}

	public static UsuarioDAOImpl getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	private UsuarioDAOImpl() {
		super();

	}

	// FIN patron singleton

	@Override
	public Usuario insert(Usuario pojo) throws Exception {
		// comprobar que usuario no es null
		if (pojo == null) {
			throw new Exception("El Usuario es null");
		}

		// abrir el preparedStatement y la conexion

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_INSERT_USUARIO,
						PreparedStatement.RETURN_GENERATED_KEYS)) {
			// nombre,contrasenia,id_rol
			pst.setString(1, pojo.getNombre());
			pst.setString(2, pojo.getNombre());
			// 1-usuario normal 2- admin
			pst.setInt(3, 1);
			int affectedRows = pst.executeUpdate();
			if (affectedRows != 1) {
				throw new Exception("No ha sido posible insertar " + pojo + " .¿Quizas nombre ya existia?");
			}
			try (ResultSet rsKeys = pst.getGeneratedKeys()) {
				if (rsKeys.next()) {
					pojo.setId(rsKeys.getInt(1));
				}
			}

		}

		return pojo;
	}

	@Override
	public Usuario getById(int id) throws Exception {
		Usuario usuarioReturn = new Usuario();
		// comprobar que el id es mayor que 0
		if (id < 1) {
			throw new Exception("El id ha de ser mayor que 1");
		}
		// stablecer la conexion y el prepared statement

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GET_BY_ID_USUARIO)) {
			// setear el id usuario
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					usuarioReturn.setId(rs.getInt("id"));
					usuarioReturn.setNombre(rs.getString("nombre"));
				} else {
					throw new Exception("No hay usuario con id " + id);
				}
			} // try ResultSet

		} // try preparedStatement

		return usuarioReturn;
	}

	@Override
	public ArrayList<Usuario> getAll() throws Exception {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		// abrir conexion, preparedStatement y resultSet
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GET_ALL_USUARIO);
				ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				Usuario aux = new Usuario();
				aux.setId(rs.getInt("id"));
				aux.setNombre(rs.getString("nombre"));
				usuarios.add(aux);
			}

		}

		return usuarios;
	}

	@Override
	public Usuario update(Usuario pojo) throws Exception {

		// comprobar que el usuario no es null
		if (pojo == null) {
			throw new Exception("El usuario es null");
		}
		// Preparar la conexion y el prepared statement
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_UPDATE_USUARIO)) {
			// setear el nuevo nombre y la id del usuario
			pst.setString(1, pojo.getNombre());
			pst.setInt(2, pojo.getId());
			int affectedRows = pst.executeUpdate();
			if (affectedRows != 1) {
				throw new Exception("No se ha podido actualizar el registro. ¿Existe el id " + pojo.getId() + "?");
			}
			// Se recupera el objeto de la bbdd
			pojo = getById(pojo.getId());
		}

		return pojo;
	}

	@Override
	public Usuario delete(int id) throws Exception {
		//recuperar el usuario de la bbdd
		Usuario usuario=new Usuario();
		usuario=getById(id);
		// comprobar que el id es mayor que 0
		if (id < 1) {
			throw new Exception("El id a borrar no existe");
		}
		// cargar conexion y prepared statement
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_DELETE_USUARIO)) {
			pst.setInt(1, id);
			int affectedRows =pst.executeUpdate();
			if (affectedRows!=1) {
				throw new Exception("No se pudo borrar el usuario. La id "+id+ " es correcta?");
			}
			
		}
		return usuario;
	}

	@Override
	public ArrayList<Usuario> getAllByName(String nombre) throws Exception{
		//comprobar que el nombre enviado es distinto de null
		if (nombre ==null || nombre.isEmpty()) {
			throw new Exception("nombre es nulo o vacio =>"+nombre);
		}
		ArrayList<Usuario>usuarios=new ArrayList<Usuario>();
		//preparar el statement y la conexion
		try(Connection conn=ConnectionManager.getConnection();
				PreparedStatement pst=conn.prepareStatement(SQL_GET_ALL_BY_NAME)) {
			
			pst.setString(1, "%"+nombre+"%");
			try (ResultSet rs=pst.executeQuery()){
				while (rs.next()  ) {
					Usuario aux=new Usuario();
					aux.setId(rs.getInt("id"));
					aux.setNombre(rs.getString("nombre"));
					usuarios.add(aux);
					
				}if (usuarios.isEmpty()) {
					throw new Exception("no hubo resultados con ese nombre:"+nombre);
				}
			}
		}
		return usuarios;
	}

}
