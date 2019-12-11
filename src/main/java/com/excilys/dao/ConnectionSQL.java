package com.excilys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionSQL {
	
	private static String user="admincdb";
	private static String mdp="qwerty1234";
	private static String url = "jdbc:mysql://localhost/computer-database-db?useSSL=false";
	
	//variable d'instance - instanciation de la classe Connection
	private static Connection connection;
	
	// methode static accessible sans instanciation - retourne objet connection
	public static Connection seConnecter() {
		
		if(connection == null) {
			
			try {
				//DriverManager charge et config le driver de la BDD 
				connection = DriverManager.getConnection(url, user, mdp);
				System.out.println("bien connecté");
				
				// la methode getConnection peut lever une exception SQLException
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		return connection;
	}
	
}
