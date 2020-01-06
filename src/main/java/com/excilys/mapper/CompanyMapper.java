package com.excilys.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.dto.CompanyDTO;
import com.excilys.models.Company;

@Component
public class CompanyMapper implements RowMapper<Company>{
	
	
	public Company CompanyDTOToCompany(CompanyDTO companyDTO) {	
		
		return new Company.CompanyBuilder().idCompany(companyDTO.getIdCompany()).nameCompany(companyDTO.getNameCompany()).build();

	}
	
	public CompanyDTO CompanyToCompanyDTO(Company company) {

		return new CompanyDTO.CompanyDTOBuilder().idCompany(company.getIdCompany()).nameCompany(company.getNameCompany()).build();

	}
	
	public Company ResultSetToCompany(ResultSet result) {
		
		int id = 0;
		String name = "";
		try {
			id = result.getInt("id");
			name = result.getString("name");
		} catch(SQLException  sqle) {
			for(Throwable e : sqle) {
				System.err.println("Problèmes rencontrés: " + e);
			}
		}

		return new Company.CompanyBuilder().idCompany(id).nameCompany(name).build();
	}

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		return null;
	}

}
