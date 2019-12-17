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
														+ "FROM computer "
														+ "LEFT JOIN company ON computer.company_id = company.id "
														+ "ORDER BY computer.id ;";
	
	private static final String SELECT_ALL_COMPUTER_PAGINATION = "SELECT  computer.id, computer.name, computer.introduced, computer.discontinued, "
														+ "computer.company_id, company.name AS company_name "
														+ "FROM computer "
														+ "LEFT JOIN company ON computer.company_id = company.id "														
														+ "LIMIT ? OFFSET ? ;";
	
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
	
	private static final String COUNT_COMPUTER 		= "SELECT COUNT(id) AS nbComputer "
														+ "FROM computer ";
														
	

	private Connection connect;
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
		this.connect = ConnectionSQL.seConnecter();
		
		try (PreparedStatement statement = connect.prepareStatement(SELECT_ALL_COMPUTER);){
			
			ResultSet resultat = statement.executeQuery();			
			while (resultat.next()) {
				
				computerList.add(computerMapper.ResultSetToComputer(resultat));
				
			    }
		
		} catch (SQLException e) {
			
			e.getMessage();
			
		} finally {
			this.connect = ConnectionSQL.disconnectDB();
		}

		return computerList;
	}
	
	
	public List<Computer> findAll(int limite, int offset) {
		
		List<Computer> computerList = new ArrayList<>();
		this.connect = ConnectionSQL.seConnecter();
		
		try (PreparedStatement statement = connect.prepareStatement(SELECT_ALL_COMPUTER_PAGINATION);){
						
			statement.setInt(1, limite);
			statement.setInt(2, offset);
			ResultSet resultat = statement.executeQuery();			
			while (resultat.next()) {
				
				computerList.add(computerMapper.ResultSetToComputer(resultat));
				
			    }
		
		} catch (SQLException e) {
			
			LOGGER.error(e.getMessage());
			
		} finally {
			this.connect = ConnectionSQL.disconnectDB();
		}

		return computerList;
	}
	
	public Computer findOne(int idSearch) {
			
		Computer computer = null;
		Connection connect = ConnectionSQL.seConnecter();
		
		try (PreparedStatement statement = connect.prepareStatement(SELECT_ONE_COMPUTER);){
						
			statement.setInt(1, idSearch);		
			ResultSet resultat = statement.executeQuery();	
			resultat.next();
			computer = computerMapper.ResultSetToComputer(resultat);

		} catch (SQLException e ) {
			
			LOGGER.error(e.getMessage());
			
		} finally {
			this.connect = ConnectionSQL.disconnectDB();
		}
		
		return computer;
	}


	public void create(Computer computer) {
		Connection connect = ConnectionSQL.seConnecter();
		
		try (PreparedStatement statement = connect.prepareStatement(CREATE_ONE_COMPUTER);){		
			
			statement.setString(1, computer.getName());
			statement.setTimestamp(2,Timestamp.valueOf(computer.getIntroducedDate().atStartOfDay()));
			statement.setTimestamp(3,Timestamp.valueOf(computer.getDiscontinuedDate().atStartOfDay()));
			statement.setInt(4, computer.getCompany().getIdCompany());
			statement.executeUpdate();

		} catch (SQLException e) {
			
			LOGGER.error(e.getMessage());
			
		} finally {
			this.connect = ConnectionSQL.disconnectDB();
		}
		return;
	}



	public void update(Computer computer) {
		Connection connect = ConnectionSQL.seConnecter();
		
		try (PreparedStatement statement = connect.prepareStatement(UPDATE_ONE_COMPUTER);){ 
					
			statement.setString(1, computer.getName());
			statement.setTimestamp(2, Timestamp.valueOf(computer.getIntroducedDate().atStartOfDay()));
			statement.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinuedDate().atStartOfDay()));
			statement.setInt(4, computer.getCompany().getIdCompany());
			statement.setInt(5,  computer.getIdComputer());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			
			LOGGER.error(e.getMessage());
			
		} finally {
			this.connect = ConnectionSQL.disconnectDB();
		}
		
	}


	public void delete(int idSearch) {
		
		Connection connect = ConnectionSQL.seConnecter();
		
		try (PreparedStatement statement = connect.prepareStatement(DELETE_ONE_COMPUTER);){
								
			statement.setInt(1, idSearch);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			
			LOGGER.error(e.getMessage());
			
		} finally {
			this.connect = ConnectionSQL.disconnectDB();
		}
		
	}
	
	public int nbComputer() {
		
		Connection connect = ConnectionSQL.seConnecter();
				
		try (PreparedStatement statement = connect.prepareStatement(COUNT_COMPUTER);) {
			
			ResultSet resultat = statement.executeQuery();
			if (resultat.first()) {
				return resultat.getInt("nbComputer");
			}
		} catch (SQLException e) {
			
			LOGGER.error(e.getMessage(), "");	
			
		} finally {
			this.connect = ConnectionSQL.disconnectDB();
		}
		return 0;
	}

}
