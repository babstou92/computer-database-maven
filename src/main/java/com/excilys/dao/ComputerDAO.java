package com.excilys.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.models.Company;
import com.excilys.models.Computer;


public class ComputerDAO {
	
	private static ComputerDAO computerDAO = null;
	
	private static final String SELECT_ALL_COMPUTER = "SELECT  computer.id, computer.name, computer.introduced, computer.discontinued, "


														+ "computer.company_id, company.name AS company_name "
														+ "FROM computer, company "
														+ "WHERE computer.company_id = company.id;";
	
	private static final String SELECT_ONE_COMPUTER  = "SELECT  computer.id, computer.name, computer.introduced, computer.discontinued, "
														+ "computer.company_id, company.name AS company_name "
														+ "FROM computer, company "
														+ "WHERE computer.company_id = company.id "
														+ "AND computer.id = ? ;";
	
	private static final String CREATE_ONE_COMPUTER = "INSERT into computer (name,introduced,discontinued,company_id) "



														+ "VALUES (?,?,?,?)";
	
	private static final String UPDATE_ONE_COMPUTER = "UPDATE computer "
			
														+ "SET name = ? "
														+ "SET introduced = ? "
														+ "SET discontinued = ? "
														+ "SET company_id = ? "
														+ "WHERE id = idSearch ;";
	
	private static final String DELETE_ONE_COMPUTER = "DELETE  from computer "
														+ "WHERE id = ?";
	
	private ComputerDAO() {};
	
	
	public static ComputerDAO getComputerDAO() {

		if(computerDAO == null) { 
			computerDAO = new ComputerDAO();
		}
		return computerDAO;
	}
												
	
	public List<Computer> findAll() {
		
		List<Computer> computerList = new ArrayList<>();
		
		try (Connection connect = ConnectionSQL.seConnecter()){
			
			PreparedStatement statement = connect.prepareStatement(SELECT_ALL_COMPUTER);
			ResultSet resultat = statement.executeQuery(SELECT_ALL_COMPUTER);
			
			while (resultat.next()) {
				
				//call mapper
				int id = resultat.getInt("id");
				Date dateSQLDis = resultat.getDate("discontinued");
				LocalDate dateDis = (dateSQLDis == null ? null : dateSQLDis.toLocalDate());
				Date dateSQLInt = resultat.getDate("introduced");
				LocalDate dateInt = (dateSQLInt == null ? null : dateSQLInt.toLocalDate());
				String name = resultat.getString("name");
				int company_id = resultat.getInt("company_id");
				String company_name = resultat.getString("company_name");				

				Computer computer = new Computer.ComputerBuilder().idComputer(id).name(name).introducedDate(dateInt).discontinuedDate(dateDis)
						.company(new Company.CompanyBuilder().idCompany(company_id).nameCompany(company_name).build()).build();
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
			
			//call mapper
			Connection connect = ConnectionSQL.seConnecter();
			PreparedStatement prepState = connect.prepareStatement(SELECT_ONE_COMPUTER);
			prepState.setInt(1, idSearch);		
			ResultSet resultat = prepState.executeQuery();	
			resultat.next();
			int company_id = resultat.getInt("company_id");
			Date dateSQLDis = resultat.getDate("discontinued");
			LocalDate dateDis = (dateSQLDis == null ? null : dateSQLDis.toLocalDate());
			Date dateSQLInt = resultat.getDate("introduced");
			LocalDate dateInt = (dateSQLInt == null ? null : dateSQLInt.toLocalDate());
			String name = resultat.getString("name");				
			String company_name = resultat.getString("company_name");
		
			computer = new Computer.ComputerBuilder().idComputer(idSearch).name(name).introducedDate(dateInt).discontinuedDate(dateDis)
									.company(new Company.CompanyBuilder().idCompany(company_id).nameCompany(company_name).build()).build(); 

			
		} catch (SQLException e ) {
			e.printStackTrace();
		}
		
		return computer;
	}


	public void create(String computerName, LocalDate introduced, LocalDate discontinued, int company_id) {
		try (Connection connect = ConnectionSQL.seConnecter()){
			
			
			PreparedStatement prepState = connect.prepareStatement(CREATE_ONE_COMPUTER);
			prepState.setString(1, computerName);
			prepState.setTimestamp(2,Timestamp.valueOf(introduced.atStartOfDay()));
			prepState.setTimestamp(3,Timestamp.valueOf(discontinued.atStartOfDay()));
			prepState.setInt(4, company_id);
			prepState.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}


	public void update(String computerName, LocalDate introduced, LocalDate discontinued, int company_id, int idSearch) {
		try (Connection connect = ConnectionSQL.seConnecter()){ 
			
			PreparedStatement prepState = connect.prepareStatement(UPDATE_ONE_COMPUTER);
			prepState.setString(1,computerName );
			prepState.setTimestamp(2, Timestamp.valueOf(introduced.atStartOfDay()));
			prepState.setTimestamp(3, Timestamp.valueOf(discontinued.atStartOfDay()));
			prepState.setInt(4, 2);
			
			prepState.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	public void delete(int idSearch) {
		try (Connection connect = ConnectionSQL.seConnecter()){
			
			
			
			PreparedStatement prepState = connect.prepareStatement(DELETE_ONE_COMPUTER);
			prepState.setInt(1, idSearch);
			prepState.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
