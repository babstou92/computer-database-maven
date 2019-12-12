package com.excilys.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.mapper.CompanyMapper;
import com.excilys.models.Company;



public class CompanyDAO  {
	
	private CompanyDAO() {};
	private static CompanyDAO companyDAO = null;
	private static final String SELECT_ALL_COMPUTER  = "SELECT * FROM company ;";												

	private Connection connect = ConnectionSQL.seConnecter();
	private static CompanyMapper companyMapper = CompanyMapper.getCompanyMapper();
	
	

	public static CompanyDAO getCompanyDAO() {
		if(companyDAO == null) {
			companyDAO = new CompanyDAO();
		}
		return companyDAO;
	}
	

	public List<Company> findAll() {
		
		List<Company> companyList = new ArrayList<Company>();
		
		try {             

			PreparedStatement statement = connect.prepareStatement(SELECT_ALL_COMPUTER);
			ResultSet resultat = statement.executeQuery();

			while (resultat.next()) {

				companyList.add(companyMapper.ResultSetToCompany(resultat)); 
				
				};

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return companyList;
	}

}
