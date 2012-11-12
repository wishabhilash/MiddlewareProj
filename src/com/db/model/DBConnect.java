package com.db.model;

import java.sql.*;

import com.library.config.ConfigLibrary;

public class DBConnect {
	String host = "jdbc:mysql://", db = "", username = "", password = "";
	Connection conn = null;
	
	public DBConnect() throws Exception{
		ConfigLibrary config = new ConfigLibrary();
		db = config.dbName;
		username = config.dbUsername;
		password = config.dbPassword;
		host += config.dbHost + "/";
	}
	
	public Connection connect() throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		return DriverManager.getConnection(host+db, username, password);
	}
}
