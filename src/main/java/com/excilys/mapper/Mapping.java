package com.excilys.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.excilys.models.Company;
import com.excilys.models.Computer;

public class Mapping {
	
	public static Computer createAComputer(ResultSet resultat) {
		
		Date dateSQLDis = resultat.getDate("discontinued");
		LocalDate dateDis = changeToLocalDate(dateSQLDis);
		Date dateSQLInt = resultat.getDate("introduced");
		LocalDate dateInt = changeToLocalDate(dateSQLInt);
		Computer computer = new Computer.ComputerBuilder().idComputer(resultat.getInt("id"))
								.name(resultat.getString("name")).introducedDate(dateInt).discontinuedDate(dateDis)
								.company(new Company.CompanyBuilder().idCompany(resultat.getInt("company_id"))
								.nameCompany(resultat.getString("company_name")).build()).build();

		return computer;
	}
	
	public static LocalDate changeToLocalDate(Date date) {
		LocalDate localDate;	
		if(date == null) {
			localDate =  null;	
		} else {
			localDate = date.toLocalDate();
		}
		return localDate;
	}
}

