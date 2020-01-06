package com.excilys.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.stereotype.Component;

@Component
public class ConnectionSQL {
	
	private DataSource datasource;
	public ConnectionSQL (DataSource datasource) {
		this.datasource = datasource;
	}

	private static Connection connection;
	
	public Connection seConnecter() {	
		
		try {
			connection = datasource.getConnection();

		} catch (SQLException  sqle) {
			for(Throwable e : sqle) {
				System.err.println("Problèmes rencontrés: " + e);
			}
		} 
		
		return connection;
	}
	
	public Connection disconnectDB() {
		if(connection!=null) {
			try {
				connection.close();
				connection=null;
			} catch (SQLException sqle) {
				for(Throwable e : sqle) {
					System.err.println("Problèmes rencontrés: " + e);
				}
			}
		}
		return connection;
	}
	
}
