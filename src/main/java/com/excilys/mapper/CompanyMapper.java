package com.excilys.mapper;

import java.sql.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dao.ComputerDAO;
import com.excilys.models.Company;


public class CompanyMapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class); 
	private CompanyMapper() {};
	private static CompanyMapper companyMapper;
	
	public static CompanyMapper getCompanyMapper() {
		if(companyMapper == null) {
			companyMapper = new CompanyMapper();
		}
		return companyMapper;
	}
	
	public  Company ResultSetToCompany(ResultSet resultat) {
		
		int id = 0;
		String name = null;
		
		try {
			id = (resultat.getInt("id") != 0) ? resultat.getInt("id"): null;
			name = (resultat.getString("name") != null) ? resultat.getString("name"): null;
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return new Company.CompanyBuilder().idCompany(id).nameCompany(name).build();

	}
}
