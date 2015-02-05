package com.Tesis.commons;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	private String user = "mgiardina";
	private String pass = "123123";
	private String bd = "Database1";

	private Connection conexion = null;

	public DataBase() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
	}

	public void close() throws SQLException {
		this.conexion.close();
	}

	private ResultSet select(String sql) throws SQLException {
		Statement s = this.conexion.createStatement();
		return s.executeQuery(sql);
	}




}
