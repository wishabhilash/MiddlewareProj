package com.db.model;

import java.io.PrintWriter;
import java.sql.*;
import java.util.*;


public abstract class DbModel {
	Connection conn = null;
	Statement stm = null;
	ResultSet resSet = null;
	
	public DbModel() {
		
	}
	
	protected abstract Connection getConn();
	
	protected abstract String getTableName();
	
	public boolean insert(LinkedHashMap<String, String> data){
		String query = "INSERT INTO " + getTableName();
		String params = "(", args = "VALUES (";
		int len = data.size(), count = 0;
		
		//	Generate query
		for(Map.Entry<String, String> entry : data.entrySet()){
			count++;
			String param = entry.getKey();
			String arg = entry.getValue();
			
			//	Add params and args
			if(param.startsWith("s-")){
				String paramSplit[] = param.split("-");
				params += paramSplit[1];
				args += "\"" + arg + "\"";
			}else{
				params += param;
				args += arg;
			}
			
			if(count < len){
				params += ",";
				args += ",";
			}else{
				params += ")";
				args += ")";
			}
		}
		
		query += " " + params + " " + args + ";";
		System.out.println(query);
		return executeCode(query, true);
	}
	
	public boolean update(LinkedHashMap<String, String> fieldData, LinkedHashMap<String, String> whereData){
		String query = "UPDATE " + getTableName() + " SET ";
		String fields = "", wheres = "WHERE ";
		int fieldLen = fieldData.size(), whereLen = whereData.size(), countF = 0;
		
		//	Generate field
		for(Map.Entry<String, String> entry : fieldData.entrySet()){
			countF++;
			String param = entry.getKey();
			String arg = entry.getValue();
			
			//	Add params and args
			if(param.startsWith("s-")){
				String paramSplit[] = param.split("-");
				fields += paramSplit[1] + "=" + "\"" + arg + "\"";
			}else{
				fields += param + "=" + arg;
			}
			
			if(countF < fieldLen){
				fields += ",";
			}
		}
		
		int countW = 0;
		//	Generate where
		for(Map.Entry<String, String> entry : whereData.entrySet()){
			countW++;
			String param = entry.getKey();
			String arg = entry.getValue();
			
			//	Add params and args
			if(param.startsWith("s-")){
				String paramSplit[] = param.split("-");
				wheres += paramSplit[1] + "=" + "\"" + arg + "\"";
			}else{
				wheres += param + "=" + arg;
			}
			
			if(countW < whereLen){
				wheres += " AND ";
			}
		}
		
		query += fields + " " + wheres + ";";
		System.out.println(query);
		return executeCode(query, true);		
	}
	
	public boolean delete(LinkedHashMap<String, String> data){
		String query = "DELETE FROM " + getTableName();
		String wheres = "WHERE ";
		int len = data.size(), count = 0;
		
		//	Generate query
		for(Map.Entry<String, String> entry : data.entrySet()){
			count++;
			String param = entry.getKey();
			String arg = entry.getValue();
			
			//	Add params and args
			if(param.startsWith("s-")){
				String paramSplit[] = param.split("-");
				wheres += paramSplit[1] + "=" + "\"" + arg + "\"";
			}else{
				wheres += param + "=" + arg;
			}
			
			if(count < len){
				wheres += " AND ";
			}
		}
		
		query += " " + wheres  + ";";
		System.out.println(query);
//		return false;
		return executeCode(query, true);
	}
	
	public Vector<LinkedHashMap<String, String>> select(LinkedHashMap<String, String> fieldData, LinkedHashMap<String, String> whereData, String condition){
		String query = "SELECT ";
		String fields = "", wheres = "FROM " + getTableName() + " WHERE ";
		int fieldLen = fieldData.size(), whereLen = whereData.size(), countF = 0;
		String keySet[] = new String[fieldLen];
		
		//	Generate field
		for(Map.Entry<String, String> entry : fieldData.entrySet()){
			countF++;
			String param = entry.getKey();
			String arg = entry.getValue();
			
			//	Add params and args
			if(param.startsWith("s-")){
				String paramSplit[] = param.split("-");
				fields += paramSplit[1];
				keySet[countF-1] = paramSplit[1];
			}else{
				fields += param;
				keySet[countF-1] = param;
			}
			
			if(countF < fieldLen){
				fields += ",";
			}
		}
		
		int countW = 0;
		//	Generate where
		for(Map.Entry<String, String> entry : whereData.entrySet()){
			countW++;
			String param = entry.getKey();
			String arg = entry.getValue();
			
			//	Add params and args
			if(param.startsWith("s-")){
				String paramSplit[] = param.split("-");
				wheres += paramSplit[1] + "=" + "\"" + arg + "\"";
			}else{
				wheres += param + "=" + arg;
			}
			
			if(countW < whereLen){
				wheres += " " + condition + " ";
			}
		}
		
		query += fields + " " + wheres + ";";
		System.out.println(query);

		// Transact with DB
		Vector<LinkedHashMap<String, String>> vec = new Vector<LinkedHashMap<String,String>>();
		try{
			stm = getConn().createStatement();
			resSet = stm.executeQuery(query);
			int count = 0;
			
			
			
			while(resSet.next()){
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				map.put(keySet[count], resSet.getString(keySet[count]));
				vec.add(map);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}

		return vec;
	}
	
	
	//	Raw update execution like: UPDATE, DELETE, INSERT
	public void genericUpdate(String query){
		try{
			stm = getConn().createStatement();
			stm.executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	//	Raw query execution like: SELECT	
	public ResultSet genericQuery(String query) throws Exception{
		stm = getConn().createStatement();
		return stm.executeQuery(query);
	}
	
	
	// Generic code to execute queries
	protected boolean executeCode(String query, boolean flag){
		try{
			stm = getConn().createStatement();
			stm.executeUpdate(query);
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
}
