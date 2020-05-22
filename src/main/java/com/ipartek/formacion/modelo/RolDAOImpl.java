package com.ipartek.formacion.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RolDAOImpl implements RolDAO{


	private final String SQL_INSERT="INSERT INTO rol(nombre) VALUES(?)";
	private final String SQL_GET_BY_ID="SELECT id, nombre FROM rol WHERE id=?";
	private final String SQL_GET_ALL="";
	private final String SQL_UPDATE="";
	private final String SQL_DELETE="";
	
	private static RolDAOImpl INSTANCE=null;
	//Singleton
	private RolDAOImpl() {
		super();
	}
	private synchronized static RolDAOImpl getRolDAOImpl() {
		if (INSTANCE==null) {
			INSTANCE=new RolDAOImpl();
		} 
		return INSTANCE;
		
	}
	public static RolDAOImpl getInstance() {
		return getRolDAOImpl();
	}
	
	
	
	@Override
	public Rol insert(Rol pojo) throws Exception {
		Rol rol=new Rol();
		
		try(Connection conn=ConnectionManager.getConnection();
				PreparedStatement pst=conn.prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS)) {
			pst.setString(1, pojo.getNombre());
			int affectedRows =pst.executeUpdate();
			if (affectedRows!=1) {
				throw new Exception("Hubo un problema  a la hora de insertar.Nombre duplicado? nombre="+pojo.getNombre());
			}
			try (ResultSet rs=pst.getGeneratedKeys()){
				if(rs.next()) {
					pojo.setId(rs.getInt(1));
				}
			}
			
		}
		return rol;
	}

	@Override
	public Rol getById(int id) throws Exception {
		Rol rol=new Rol();
		try (Connection conn=ConnectionManager.getConnection();
				PreparedStatement pst=conn.prepareStatement(SQL_GET_BY_ID)){
			pst.setInt(1, id);
			try(ResultSet rs=pst.executeQuery()) {
				if (rs.next()) {
					rol.setId(rs.getInt("id"));
					rol.setNombre(rs.getString("nombre"));
				}else {
					throw new Exception("No hubo resultados con esta id:"+id);
				}
			}
			
		}
		return rol;
	}

	@Override
	public ArrayList<Rol> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rol update(Rol pojo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rol delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
