package com.excilys.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class ConnectionSQL {
	
	//initialiser HikariConfig avec un fichier de propriétés placé dans le répertoire resources :
	private static HikariConfig config = new HikariConfig("/database.properties" );
	//creation d'une instance unique d'une source de donnée
	private static HikariDataSource ds = new HikariDataSource( config );

	private static Connection connection;
	
	public static Connection seConnecter() {
		
		if(connection == null) {
			
			try {
				connection = ds.getConnection();

			} catch (SQLException  e) {
				e.printStackTrace();
			} 
		}
		
		return connection;
	}
	
	public static Connection disconnectDB() {
		if(connection!=null) {
			try {
				connection.close();
				connection=null;
			} catch (SQLException se) {
				for(Throwable e : se) {
					System.err.println("Problèmes rencontrés: " + e);
				}
			}
		}
		return connection;
	}
	
}
