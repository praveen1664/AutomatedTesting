package com.optum.acoe.DBFunctionLib;

import java.sql.*;
import java.util.HashMap;

public class DBHandler {
	private Connection connection;

	public DBHandler() throws Exception {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (Exception e) {
			System.err.println("Could not load microsoft.sqlserver driver \n");
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(8);
		}
	}

	public Connection SQLServerConnect(String url, String uid, String pwd) {

		try {
			connection = DriverManager.getConnection(url, uid, pwd);
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("The reason: " + e.getMessage());
			return connection;
		}
		return connection;
	}

	public Connection SQLServerConnectURL(String url) {
		try {
			connection = DriverManager.getConnection(url);
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("Unable to make a connection to the database\n" + "The reason: " + e.getMessage());
			return connection;
		}
		return connection;
	}

	public String insert(Connection connect, String table, String names, String values) {

		java.sql.Connection conn = connect;
		
		String todo = ("INSERT into " + table + "(" + names + ") values (" + values + ")");

		try {
			java.sql.Statement s = conn.createStatement();
			s.executeUpdate(todo);
			conn.commit();
		} catch (Exception e) {
			return ("insert Failed\n Reason :" + e);
		}

		return (todo);
	}
	public int insertMyUhcValues(Connection connect,String table,HashMap<String,String> values){
		java.sql.Connection conn=connect;
		int insert=0;
		try {
			PreparedStatement query=conn.prepareStatement("Insert into myuhc2 values(?,?,?,?,?,?,?,?)");
			//Add Property Table Name.
			
		//	query.setString(1, table);
			
			if(values.get("INN_Deductible_Bal").equals(""))
				query.setNull(1, Types.VARCHAR);
			else{
				query.setString(1,values.get("INN_Deductible_Bal"));
				System.out.println(values.get("INN_Deductible_Bal"));
			}

			
			if(!values.get("INN_Deductible_Limit").equals(""))
				query.setString(2,values.get("INN_Deductible_Limit"));
			else
				query.setNull(2, Types.VARCHAR);
			
			if(!values.get("INN_OOP_Bal").equals(""))
				query.setString(3,values.get("INN_Deductible_Bal"));
			else
				query.setNull(3, Types.VARCHAR);
			
			if(!values.get("INN_OOP_Limit").equals(""))
				query.setString(4,values.get("INN_Deductible_Bal"));
			else
				query.setNull(4, Types.VARCHAR);
			
			if(!values.get("OON_Deductible_Bal").equals(""))
				query.setString(5,values.get("INN_Deductible_Bal"));
			else
				query.setNull(5, Types.VARCHAR);
			
			if(!values.get("OON_Deductible_Limit").equals(""))
				query.setString(6,values.get("INN_Deductible_Bal"));
			else
				query.setNull(6, Types.VARCHAR);
			
			if(!values.get("OON_OOP_Bal").equals(""))
				query.setString(7,values.get("INN_Deductible_Bal"));
			else
				query.setNull(7, Types.VARCHAR);
			
			if(!values.get("INN_OOP_Limit").equals(""))
				query.setString(8,values.get("INN_Deductible_Bal"));
			else
				query.setNull(8, Types.VARCHAR);
			
			insert=query.executeUpdate();
			conn.commit();
			//conn.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				System.out.println("Insertion failed: "+e.toString());
				e.printStackTrace();
				return insert;
		}
		return insert;
	}

	public String update(Connection connect, String table, String column, String values, String condition) {

		java.sql.Connection conn = connect;

		String todo = ("UPDATE " + table + " SET "+ column + " = " + values  + " WHERE "  + condition );

		try {
			java.sql.Statement s = conn.createStatement();
			s.executeUpdate(todo);
			
			conn.commit();
		} catch (Exception e) {
			return ("Oh oops - code 003\n" + e);
		}

		return (todo);

	}

	public ResultSet select(Connection connect, String qurey) {

		java.sql.Connection conn = connect;

		ResultSet rs = null;

		try {

			java.sql.Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = s.executeQuery(qurey);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return rs;
		}

	}

	public void SQLServerClose() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(" Close Failed The reason: " + e.getMessage());
			e.printStackTrace();
		}

	}

}
