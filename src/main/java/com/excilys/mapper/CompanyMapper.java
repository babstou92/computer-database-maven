package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.excilys.models.Company;


public class CompanyMapper {
	
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new Company.CompanyBuilder().idCompany(id).nameCompany(name).build();

	}
}
