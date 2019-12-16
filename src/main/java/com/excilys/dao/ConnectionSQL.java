package com.excilys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionSQL {
	
	private static String user="admincdb";
	private static String mdp="qwerty1234";
	private static String url = "jdbc:mysql://localhost/computer-database-db?useSSL=false";
	

	private static Connection connection;
	

	public static Connection seConnecter() {
		
		if(connection == null) {
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url, user, mdp);

			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			} 
		}
		
		return connection;
	}
	
}
