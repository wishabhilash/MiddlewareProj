package com.library.model;

import com.db.model.DBConnect;
import com.db.model.DbModel;

import java.sql.*;


public class BookMaster extends DbModel{
	
	/*	Follow the pattern below while passing arguments for insert
	 * 	keys preceeded by "s-" mean that they are String variables for database
	 * 
	 * LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("book_id", "");
		map.put("s-name", "");
		map.put("s-publisher", "");
		map.put("copies", "");
		map.put("price", "");
		map.put("s-version", "");
		map.put("issued_copies", "");
	 */
	
	Connection conn = null;
	Statement stm = null;
	ResultSet resSet = null;
	DBConnect db = new DBConnect();
	String tableName = "book_master"; 
	
	public BookMaster() throws Exception{
		conn = db.connect();
	}
	
	@Override
	protected String getTableName(){
		return tableName;
	}
	
	@Override
	protected Connection getConn(){
		return conn;
	}
	
	
}
