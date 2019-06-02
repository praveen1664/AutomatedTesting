package com.optum.acoe.Runner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.optum.acoe.DBFunctionLib.DBHandler;

public class Test {

	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
		String url = "jdbc:mysql://dbsrd2928:3306/ocdv01";
		String username = "ocdv_own";
		String password = "sk6ahPcq";
//		DBHandler db = new DBHandler();
//		Connection conn = db.SQLServerConnect(url, username, password);
//		ResultSet rs = db.select(conn, "select count(*) from MC_ROADMAPCONT where PADUSTATUS='P'");
//		int j = rs.getInt("count(*)");
//		int i = rs.getInt(1);
//		System.out.println();
		
		System.out.println("Connecting database...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
		    System.out.println("Database connected!");
		    Statement stmt = connection.createStatement();
		    ResultSet rt = stmt.executeQuery("select count(*) as number from MC_ROADMAPCONT where PADUSTATUS='P'");
//		    System.out.println(rt.next());
//		    System.out.println(rt.);
//		    System.out.println(rt.getInt("number"));
		    
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

}
