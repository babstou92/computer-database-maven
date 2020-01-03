package com.excilys.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class ConnectionSQL {
	
	//initialiser HikariConfig avec un fichier de propriétés placé dans le répertoire resources :
	private static HikariConfig config = new HikariConfig("/database.properties" );
	//creation d'une instance unique d'une source de donnée
	private static HikariDataSource ds = new HikariDataSource( config );

	private static Connection connection;
	
	public Connection seConnecter() {	
		
		try {
			connection = ds.getConnection();

		} catch (SQLException  e) {
				e.printStackTrace();
		} 
		
		return connection;
	}
	
	public Connection disconnectDB() {
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
