package com.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.excilys.models.Company;
import com.excilys.models.Computer;


public class ComputerDAO {
	
	
	public Connection connect = ConnectionSQL.seConnecter();
	private static ComputerDAO computerDAO = null;
	
	private static final String SELECT_ALL_COMPUTER = "SELECT  computer.id, computer.name, computer.introduced, computer.discontinued, "


														+ "computer.company_id, company.name AS company_name "
														+ "FROM computer, company "
														+ "WHERE computer.company_id = company.id;";
	
	private static final String SELECT_ONE_COMPUTER  = "SELECT  computer.id, computer.name, computer.introduced, computer.discontinued, "
														+ "computer.company_id, company.name AS company_name "
														+ "FROM computer, company "
														+ "WHERE computer.company_id = company.id "
														+ "AND company.id = ? ;";
	
	private static final String CREATE_ONE_COMPUTER = "INSERT into computer (id,name,introduced,discontinued,company_id) "



														+ "VALUES (63,?,?,?,?)";
	
	private static final String UPDATE_ONE          = "UPDATE computer "
			
														+ "SET name = ? "
														+ "SET introduced = ? "
														+ "SET discontinued = ? "
														+ "SET company_id = ? "
														+ "WHERE id = idSearch ;";
	
	private static final String DELETE_ONE_COMPUTER = "DELETE  from computer "
														+ "WHERE id = ?";
	
	private ComputerDAO() {

		System.out.println("Singleton ComputerDAO créé");
	};
	
	
	public static ComputerDAO getComputerDAO() {

		if(computerDAO == null) { 
			computerDAO = new ComputerDAO();
		}
		return computerDAO;
	}
												
	
	public List<Computer> findAll() {
		
		List<Computer> computerList = new ArrayList<>();
		
		try {
			
			PreparedStatement statement = connect.prepareStatement(SELECT_ALL_COMPUTER);
			ResultSet resultat = statement.executeQuery(SELECT_ALL_COMPUTER);
			
			while (resultat.next()) {
				
				int id = resultat.getInt("id");
				Date dateDis = resultat.getDate("discontinued");
				Date dateInt = resultat.getDate("introduced");
				String name = resultat.getString("name");
				int company_id = resultat.getInt("company_id");
				String company_name = resultat.getString("company_name");
				
				//call mapper
				Computer computer = new Computer(id, name, dateInt, dateDis, new Company(company_id, company_name) );
				computerList.add(computer);
			    }
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return computerList;
	}
	
	public Computer findOne(int idSearch) {
			
		Computer computer = null;
		
		try {
			
			PreparedStatement prepState = connect.prepareStatement(SELECT_ONE_COMPUTER);
			prepState.setInt(1, idSearch);
			
			ResultSet resultat = prepState.executeQuery();	
			resultat.next();
				int company_id = resultat.getInt("company_id");
				Date dateDis = resultat.getDate("discontinued");
				Date dateInt = resultat.getDate("introduced");
				String name = resultat.getString("name");				
				String company_name = resultat.getString("company_name");

				//call mapper
				computer = new Computer(idSearch, name, dateInt, dateDis, new Company(company_id, company_name)); 

			
		} catch (SQLException e ) {
			e.printStackTrace();
		}
		
		return computer;
	}


	public void create(String computerName, Date introduced, Date discontinued, int company_id) {
		try {
			
			
			PreparedStatement prepState = connect.prepareStatement(CREATE_ONE_COMPUTER);
			prepState.setString(1, computerName);
			prepState.setTimestamp(2, new Timestamp(introduced.getTime()));
			prepState.setTimestamp(3, new Timestamp(discontinued.getTime()));
			prepState.setInt(4, company_id);
			prepState.executeUpdate();
			
			System.out.println("Creation d'un nouveau computer avec succès.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}


	public void update(String computerName, Date introduced, Date discontinued, int company_id, int idSearch) {
		try { 
			
			PreparedStatement prepState = connect.prepareStatement(UPDATE_ONE);
			prepState.setString(1,computerName );
			prepState.setTimestamp(2, new Timestamp(introduced.getTime()));
			prepState.setTimestamp(3, new Timestamp(discontinued.getTime()));
			prepState.setInt(4, 2);
			
			prepState.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	public void delete(int idSearch) {
		try {
			
			
			
			PreparedStatement prepState = connect.prepareStatement(DELETE_ONE_COMPUTER);
			prepState.setInt(1, idSearch);
			prepState.executeUpdate();
			//use logger instead of println
			System.out.println("le computeur avec l'id "+idSearch+" a été supprimé avec succès.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
