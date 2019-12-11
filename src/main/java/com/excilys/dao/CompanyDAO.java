package com.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.models.Company;



public class CompanyDAO  {
	

	private static CompanyDAO companyDAO = null;
	private static final String SELECT_ALL_COMPUTER  = "SELECT * FROM company ;";												

	public Connection connect = ConnectionSQL.seConnecter();

	private CompanyDAO() {};
	

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

				int id = resultat.getInt("id");
				String name = resultat.getString("name");
				Company company = new Company.CompanyBuilder().idCompany(id).nameCompany(name).build();
				companyList.add(company); 
				}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return companyList;
	}

}
