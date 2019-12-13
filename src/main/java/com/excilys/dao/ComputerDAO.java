package com.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mapper.ComputerMapper;
import com.excilys.models.Computer;



public class ComputerDAO {
	
	
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
														+ "SET name = ? ,"
														+ "introduced = ? ,"
														+ "discontinued = ? ,"
														+ "company_id = ? "
														+ "WHERE id = ? ;";
	
	private static final String DELETE_ONE_COMPUTER = "DELETE  from computer "
														+ "WHERE id = ?";
	

	//a ne pas laisser
	public Connection connect = ConnectionSQL.seConnecter();
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class); 
	
	private ComputerDAO() {};
	private static ComputerDAO computerDAO = null;
	
	public static ComputerDAO getComputerDAO() {

		if(computerDAO == null) { 
			computerDAO = new ComputerDAO();
		}
		return computerDAO;
	}
	
	private static ComputerMapper computerMapper = ComputerMapper.getComputerMapper();
												
	
	public List<Computer> findAll() {
		
		List<Computer> computerList = new ArrayList<>();
		
		try (Connection connect = ConnectionSQL.seConnecter()){
			
			PreparedStatement statement = connect.prepareStatement(SELECT_ALL_COMPUTER);
			ResultSet resultat = statement.executeQuery(SELECT_ALL_COMPUTER);
			
			while (resultat.next()) {
				

				computerList.add(computerMapper.ResultSetToComputer(resultat));
				
			    }
		
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}

		return computerList;
	}
	
	public Computer findOne(int idSearch) {
			
		Computer computer = null;
		
		try {
			
			Connection connect = ConnectionSQL.seConnecter();
			PreparedStatement prepState = connect.prepareStatement(SELECT_ONE_COMPUTER);
			prepState.setInt(1, idSearch);		
			ResultSet resultat = prepState.executeQuery();	
			resultat.next();

			computer = computerMapper.ResultSetToComputer(resultat);

		} catch (SQLException e ) {
			LOGGER.error(e.getMessage());
		}
		
		return computer;
	}


	public void create(Computer computer) {
		try (Connection connect = ConnectionSQL.seConnecter()){
			
			
			PreparedStatement prepState = connect.prepareStatement(CREATE_ONE_COMPUTER);
			prepState.setString(1, computer.getNameComputer());
			prepState.setTimestamp(2,Timestamp.valueOf(computer.getIntroducedDateComputer().atStartOfDay()));
			prepState.setTimestamp(3,Timestamp.valueOf(computer.getDiscontinuedDateComputer().atStartOfDay()));
			prepState.setInt(4, computer.getCompany().getIdCompany());
			prepState.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return;
	}



	public void update(Computer computer) {
		try (Connection connect = ConnectionSQL.seConnecter()){ 
			
			PreparedStatement prepState = connect.prepareStatement(UPDATE_ONE_COMPUTER);
			prepState.setString(1, computer.getNameComputer() );
			prepState.setTimestamp(2, Timestamp.valueOf(computer.getIntroducedDateComputer().atStartOfDay()));
			prepState.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinuedDateComputer().atStartOfDay()));
			prepState.setInt(4, computer.getCompany().getIdCompany());
			prepState.setInt(5,  computer.getIdComputer());
			
			prepState.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		
	}


	public void delete(int idSearch) {
		try (Connection connect = ConnectionSQL.seConnecter()){
								
			PreparedStatement prepState = connect.prepareStatement(DELETE_ONE_COMPUTER);
			prepState.setInt(1, idSearch);
			prepState.executeUpdate();
			
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		
	}

}
