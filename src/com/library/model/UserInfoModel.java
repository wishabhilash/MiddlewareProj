package com.library.model;

import java.sql.*;

import com.db.model.*;

public class UserInfoModel extends DbModel {
	Connection conn = null;
	String tableName = "user_info";
	Statement stm = null;
	ResultSet resSet = null;
	DBConnect db = new DBConnect();
	
	public UserInfoModel() throws Exception{
		conn = db.connect(); 
	}
	
	protected Connection getConn(){
		return conn;
	}
	
	protected String getTableName() {
		return tableName;
	}

	
}
