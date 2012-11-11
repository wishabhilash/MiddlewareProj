package com.db.model;

import java.io.*;
import java.sql.*;

public class DBConnect {
	String host = "jdbc:mysql://", db = "", username = "", password = "";
	Connection conn = null;
	
	public DBConnect() throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("config"));
		boolean flag = false;
		String line = "";
		while((line = br.readLine()) != null){
			if(line.startsWith("[[ ")){
				if(line.equalsIgnoreCase("[[ DBDETAILS ]]")){
					flag = true;
				}
				continue;
			}
			if(flag){
				String lineSplit[] = line.split("=");
				if(lineSplit[0].trim().equalsIgnoreCase("host")){
					host += lineSplit[1].trim() + "/"; 
				}else if(lineSplit[0].trim().equalsIgnoreCase("db")){
					db += lineSplit[1].trim(); 
				}else if(lineSplit[0].trim().equalsIgnoreCase("username")){
					username += lineSplit[1].trim(); 
				}else if(lineSplit[0].trim().equalsIgnoreCase("password")){
					password += lineSplit[1].trim(); 
				}
			}else{
				continue;
			}
		}
				
	}
	
	public Connection connect() throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		return DriverManager.getConnection(host+db, username, password);
	}
}
