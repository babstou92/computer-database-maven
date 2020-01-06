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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.excilys.models.Company;
import com.excilys.models.Computer;


@Repository
public class ComputerDAO {
	
	
	private static final String SELECT_ALL_COMPUTER = "SELECT  computer.id, computer.name, computer.introduced, computer.discontinued, "
														+ "computer.company_id, company.name "
														+ "FROM computer "
														+ "LEFT JOIN company ON computer.company_id = company.id "
														+ "ORDER BY computer.id ;";
	
	private static final String SELECT_ALL_COMPUTER_PAGINATION = "SELECT  computer.id, computer.name, computer.introduced, computer.discontinued, "
														+ "computer.company_id, company.name "
														+ "FROM computer "
														+ "LEFT JOIN company ON computer.company_id = company.id "														
														+ "LIMIT ? OFFSET ? ;";
	
	private static final String SELECT_ONE_COMPUTER  = "SELECT  computer.id, computer.name, computer.introduced, computer.discontinued, "
														+ "computer.company_id, company.name "
														+ "FROM computer LEFT JOIN company "
														+ "ON computer.company_id = company.id "
														+ "WHERE computer.id = ? ;";
	
	private static final String CREATE_ONE_COMPUTER = "INSERT into computer (name,introduced,discontinued,company_id) "
														+ "VALUES (?,?,?,?)";
	
	private static final String UPDATE_ONE_COMPUTER = "UPDATE computer "			
														+ "SET name = ? ,"
														+ "introduced = ? ,"
														+ "discontinued = ? ,"
														+ "company_id = ? "
														+ "WHERE id = ? ;";
	
	private static final String DELETE_ONE_COMPUTER = "DELETE  from computer "
														+ "WHERE id = ? ;";
	
	private static final String COUNT_COMPUTER 		= "SELECT COUNT(id) AS nbComputer "
														+ "FROM computer ";
	
	private static final String SEARCH_COMPUTER_BY_NAME = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, "
														+ "computer.company_id, company.name FROM computer "
														+ "LEFT JOIN company ON computer.company_id = company.id "
														+ "WHERE company.name LIKE ? OR computer.name LIKE ? LIMIT ? OFFSET ?";
	
	private static final String COUNT_COMPUTER_BY_NAME 	= "SELECT COUNT(computer.id) AS nbComputerByName "
														+ "FROM computer LEFT JOIN company ON computer.company_id = company.id "
														+ "WHERE company.name LIKE ? OR computer.name LIKE ?";
	
	private ConnectionSQL connectionSQL;
	public ComputerDAO (ConnectionSQL connectionSQL) {
		this.connectionSQL = connectionSQL;
	}

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class); 
	
														
	
	private Connection connect;											
	
	public List<Computer> findAll() {
		
		List<Computer> computerList = new ArrayList<>();
		this.connect = connectionSQL.seConnecter();
		
		try (PreparedStatement statement = connect.prepareStatement(SELECT_ALL_COMPUTER)){
			
			ResultSet resultat = statement.executeQuery();			
			while (resultat.next()) {
				Date dateSQLDis = resultat.getDate("discontinued");
				LocalDate dateDis = (dateSQLDis != null ) ? dateSQLDis.toLocalDate() : null;
				Date dateSQLInt = resultat.getDate("introduced");
				LocalDate dateInt = (dateSQLInt != null ) ? dateSQLInt.toLocalDate() : null;
				int id = resultat.getInt("id");
				int company_id = resultat.getInt("company_id");
				String company_name = resultat.getString("company.name");
				String name = resultat.getString("name");
				
				Computer computer = new Computer.ComputerBuilder().idComputer(id).name(name).introducedDate(dateInt).discontinuedDate(dateDis)
											.company(new Company.CompanyBuilder().idCompany(company_id)
											.nameCompany(company_name).build()).build();
				computerList.add(computer);
				
			    }
		
		} catch (SQLException e) {
			
			e.getMessage();
			
		} finally {
			this.connect = connectionSQL.disconnectDB();
		}

		return computerList;
	}
	
	
	public List<Computer> findAll(int limite, int offset) {
		
		List<Computer> computerList = new ArrayList<>();
		this.connect = connectionSQL.seConnecter();
		
		try (PreparedStatement statement = connect.prepareStatement(SELECT_ALL_COMPUTER_PAGINATION)){
						
			statement.setInt(1, limite);
			statement.setInt(2, offset);
			ResultSet resultat = statement.executeQuery();			
			while (resultat.next()) {
				Date dateSQLDis = resultat.getDate("discontinued");
				LocalDate dateDis = (dateSQLDis != null ) ? dateSQLDis.toLocalDate() : null;
				Date dateSQLInt = resultat.getDate("introduced");
				LocalDate dateInt = (dateSQLInt != null ) ? dateSQLInt.toLocalDate() : null;
				int id = resultat.getInt("id");
				int company_id = resultat.getInt("company_id");
				String company_name = resultat.getString("company.name");
				String name = resultat.getString("name");
				
				Computer computer = new Computer.ComputerBuilder().idComputer(id).name(name).introducedDate(dateInt).discontinuedDate(dateDis)
											.company(new Company.CompanyBuilder().idCompany(company_id)
											.nameCompany(company_name).build()).build();
				computerList.add(computer);
				
			    }
		
		} catch (SQLException e) {
			
			LOGGER.error(e.getMessage());
			
		} finally {
			this.connect = connectionSQL.disconnectDB();
		}

		return computerList;
	}
	
	public Computer findOne(int idSearch) {
			
		Computer computer = null;
		Connection connect = connectionSQL.seConnecter();
		
		try (PreparedStatement statement = connect.prepareStatement(SELECT_ONE_COMPUTER);){
		
			statement.setInt(1, idSearch);
			ResultSet resultat = statement.executeQuery();	
			resultat.next();
			Date dateSQLDis = resultat.getDate("discontinued");
			LocalDate dateDis = (dateSQLDis != null ) ? dateSQLDis.toLocalDate() : null;
			Date dateSQLInt = resultat.getDate("introduced");
			LocalDate dateInt = (dateSQLInt != null ) ? dateSQLInt.toLocalDate() : null;
			int id = idSearch;
			int company_id = resultat.getInt("company_id");
			String company_name = resultat.getString("company.name");
			String name = resultat.getString("name");
			
			computer = new Computer.ComputerBuilder().idComputer(id).name(name).introducedDate(dateInt).discontinuedDate(dateDis)
										.company(new Company.CompanyBuilder().idCompany(company_id)
										.nameCompany(company_name).build()).build();

		} catch (SQLException e ) {
			
			LOGGER.error(e.getMessage());
			
		} finally {
			this.connect = connectionSQL.disconnectDB();
		}
		
		return computer;
	}


	public void create(Computer computer) {
		Connection connect = connectionSQL.seConnecter();
		
		try (PreparedStatement statement = connect.prepareStatement(CREATE_ONE_COMPUTER);){		
			
			statement.setString(1, computer.getName());
			statement.setTimestamp(2, computer.getIntroducedDate() == null ? null : Timestamp.valueOf(computer.getIntroducedDate().atStartOfDay()));
			statement.setTimestamp(3,computer.getDiscontinuedDate() == null ? null : Timestamp.valueOf(computer.getDiscontinuedDate().atStartOfDay()));
			statement.setInt(4, computer.getCompany().getIdCompany());
			statement.executeUpdate();

		} catch (SQLException e) {
			
			LOGGER.error(e.getMessage());
			
		} finally {
			this.connect = connectionSQL.disconnectDB();
		}
		return;
	}



	public void update(Computer computer) {
		Connection connect = connectionSQL.seConnecter();
		
		try (PreparedStatement statement = connect.prepareStatement(UPDATE_ONE_COMPUTER);){ 
					
			statement.setString(1, computer.getName());
			statement.setTimestamp(2, computer.getIntroducedDate() == null ? null : Timestamp.valueOf(computer.getIntroducedDate().atStartOfDay()));
			statement.setTimestamp(3, computer.getIntroducedDate() == null ? null : Timestamp.valueOf(computer.getDiscontinuedDate().atStartOfDay()));
			statement.setInt(4, computer.getCompany().getIdCompany());
			statement.setInt(5, computer.getIdComputer());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			
			LOGGER.error(e.getMessage());
			
		} finally {
			this.connect = connectionSQL.disconnectDB();
		}
		
	}


	public void delete(int idSearch) {
		
		Connection connect = connectionSQL.seConnecter();
		
		try (PreparedStatement statement = connect.prepareStatement(DELETE_ONE_COMPUTER);){
								
			statement.setInt(1, idSearch);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			
			LOGGER.error(e.getMessage());
			
		} finally {
			this.connect = connectionSQL.disconnectDB();
		}
		
	}
	
	public int nbComputer() {

		Connection connect = connectionSQL.seConnecter();
				
		try (PreparedStatement statement = connect.prepareStatement(COUNT_COMPUTER);) {
			
			ResultSet resultat = statement.executeQuery();
			if (resultat.first()) {
				return resultat.getInt("nbComputer");
			}
		} catch (SQLException e) {
			
			LOGGER.error(e.getMessage(), "");	
			
		} finally {
			this.connect = connectionSQL.disconnectDB();
		}
		return 0;
	}
	
	public List<Computer> searchComputerByName(int limite, int offset, String name) {
		
		List<Computer> computerList = new ArrayList<>();
		this.connect = connectionSQL.seConnecter();
		
		try (PreparedStatement statement = connect.prepareStatement(SEARCH_COMPUTER_BY_NAME)){
			
			statement.setString(1, "%"+ name +"%");
			statement.setString(2, "%"+ name +"%");
			statement.setInt(3, limite);
			statement.setInt(4, offset);
			ResultSet resultat = statement.executeQuery();			
			while (resultat.next()) {
				Date dateSQLDis = resultat.getDate("discontinued");
				LocalDate dateDis = (dateSQLDis != null ) ? dateSQLDis.toLocalDate() : null;
				Date dateSQLInt = resultat.getDate("introduced");
				LocalDate dateInt = (dateSQLInt != null ) ? dateSQLInt.toLocalDate() : null;
				int id = resultat.getInt("id");
				int company_id = resultat.getInt("company_id");
				String company_name = resultat.getString("company.name");
				String computer_name = resultat.getString("name");
				
				Computer computer = new Computer.ComputerBuilder().idComputer(id).name(computer_name).introducedDate(dateInt).discontinuedDate(dateDis)
											.company(new Company.CompanyBuilder().idCompany(company_id)
											.nameCompany(company_name).build()).build();
				computerList.add(computer);
				
			    }
		
		} catch (SQLException e) {
			
			LOGGER.error(e.getMessage());
			
		} finally {
			this.connect = connectionSQL.disconnectDB();
		}

		return computerList;
	}
	
	public int nbComputerByName(String name) {
		
		Connection connect = connectionSQL.seConnecter();
				
		try (PreparedStatement statement = connect.prepareStatement(COUNT_COMPUTER_BY_NAME);) {

			statement.setString(1, "%"+ name +"%");
			statement.setString(2, "%"+ name +"%");
			ResultSet resultat = statement.executeQuery();
			if (resultat.first()) {
				return resultat.getInt("nbComputerByName");
			}
		} catch (SQLException e) {
			
			LOGGER.error(e.getMessage(), "");	
			
		} finally {
			this.connect = connectionSQL.disconnectDB();
		}
		return 0;
	}
	

}
