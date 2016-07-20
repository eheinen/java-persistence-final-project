package com.adventure_time.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Dao {

	protected Connection conn;
	protected PreparedStatement stmt;
	protected ResultSet rs;
	
	protected void openConn() throws SQLException, ClassNotFoundException {
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/hotel", "root", "*****");	
		/*
		Seta o nível de isolamento padrão para esta aplicação
		*/
		conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	}
	
	protected void closeConn() throws SQLException {
		if(conn != null && !conn.isClosed()){
			conn.close();
		}
	}
}
