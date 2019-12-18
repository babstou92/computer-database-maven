package com.excilys.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.models.Company;

public class CompanyDAO  {
	
	private static final String SELECT_ALL_COMPUTER  = "SELECT * FROM company ;";												
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class); 
	private Connection connect;
	
	private CompanyDAO() {};
	private static CompanyDAO companyDAO = null;

	public static CompanyDAO getCompanyDAO() {
		if(companyDAO == null) {
			companyDAO = new CompanyDAO();
		}
		return companyDAO;
	}
	

	public List<Company> findAll() {
		
		List<Company> companyList = new ArrayList<Company>();
		this.connect = ConnectionSQL.seConnecter();
		
		try (PreparedStatement statement = connect.prepareStatement(SELECT_ALL_COMPUTER)){             

			
			ResultSet resultat = statement.executeQuery();

			while (resultat.next()) {
				
				int id = (resultat.getInt("id") != 0) ? resultat.getInt("id"): null;
				String name = (resultat.getString("name") != null) ? resultat.getString("name"): null;
				Company company = new Company.CompanyBuilder().idCompany(id).nameCompany(name).build();
				companyList.add(company);
				}

		} catch (SQLException e) {
			
			LOGGER.error(e.getMessage());
			
		} finally {
			this.connect = ConnectionSQL.disconnectDB();
		}

		return companyList;
	}

}
