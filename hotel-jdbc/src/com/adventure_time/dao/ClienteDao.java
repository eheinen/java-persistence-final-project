package com.adventure_time.dao;

import com.adventure_time.entity.Cliente;

public class ClienteDao extends Dao{

	public void add(Cliente cliente) throws Exception{
		try{
			openConn();
			String sql = "INSERT INTO CLIENTES (USERNAME, VALOR_TOTAL, NUM_RESERVAS) VALUES (?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cliente.getUsername());
			stmt.setString(2, cliente.getValorTotal());
			stmt.setInt(3, cliente.getNumReservas());
			stmt.executeUpdate();
		}catch(Exception e){
			throw e;
		}finally{
			closeConn();
		}
	}
	
	public void delete(Cliente cliente) throws Exception{
		try {
			openConn();
			String sql = "DELETE FROM CLIENTES WHERE ID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cliente.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally{
			closeConn();
		}
	}
	
	public Cliente findByUsername(String username) throws Exception{
		try {
			openConn();
			String sql = "SELECT * FROM CLIENTES WHERE USERNAME=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			Cliente cliente = null;
			if(rs.next()){
				cliente = new Cliente();
				cliente.setId(rs.getInt("ID"));
				cliente.setUsername(rs.getString("USERNAME"));
				cliente.setNumReservas(rs.getInt("NUM_RESERVAS"));
				cliente.setValorTotal(rs.getString("VALOR_TOTAL"));
			}
			return cliente;
		} catch (Exception e) {
			throw e;
		}finally{
			closeConn();
		}
	}
}
