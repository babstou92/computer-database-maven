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
	

	@Override
		public Company mapRow(ResultSet result, int rowNum) throws SQLException {
			int id = result.getInt("id") != 0 ? result.getInt("id") : null;
			String name = result.getString("name") != null ? result.getString("name") : null;
			return new Company.CompanyBuilder().idCompany(id).nameCompany(name).build();
	}

}
