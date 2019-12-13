package com.excilys.mapper;

import java.sql.Date;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.excilys.models.Company;
import com.excilys.models.Computer;

public class ComputerMapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerMapper.class);
	private ComputerMapper() {};
	private static ComputerMapper computerMapper;
	
	public static ComputerMapper getComputerMapper() {
		if(computerMapper == null) {
			computerMapper = new ComputerMapper();
		}		
		return computerMapper;
	}
	
	public  Computer ResultSetToComputer(ResultSet resultat) {
		
		LocalDate dateDis = null, dateInt = null;
		int id = 0, company_id = 0;
		String name = null, company_name = null;

		try {
			Date dateSQLDis = resultat.getDate("discontinued");
			dateDis = (dateSQLDis != null ) ? dateSQLDis.toLocalDate() : null;
			Date dateSQLInt = resultat.getDate("introduced");
			dateInt = (dateSQLInt != null ) ? dateSQLInt.toLocalDate() : null;
			id = resultat.getInt("id");
			company_id = resultat.getInt("company_id");
			company_name = resultat.getString("company_name");
			name = resultat.getString("name");
		
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		
		return new Computer.ComputerBuilder().idComputer(id).name(name).introducedDate(dateInt).discontinuedDate(dateDis)
					.company(new Company.CompanyBuilder().idCompany(company_id).nameCompany(company_name).build()).build();


	}
	
}