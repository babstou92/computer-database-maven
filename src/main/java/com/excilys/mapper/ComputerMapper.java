package com.excilys.mapper;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;



import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.models.Company;
import com.excilys.models.Computer;

@Component
public class ComputerMapper implements RowMapper<Computer>{
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");	

	public Computer computerDTOtoComputer(ComputerDTO computerDTO) {

		String dateStringInt = computerDTO.getIntroducedDate();
		LocalDate introduced = dateStringInt.equals("") ? null : LocalDate.parse(dateStringInt, formatter);
		String dateStringDis = computerDTO.getDiscontinuedDate();
		LocalDate discontinued = dateStringInt.equals("") ? null : LocalDate.parse(dateStringDis, formatter);

		return  new Computer.ComputerBuilder().idComputer(computerDTO.getIdComputer()).computerName(computerDTO.getComputerName())
							.introducedDate(introduced).discontinuedDate(discontinued)
							.company(new Company.CompanyBuilder().idCompany(computerDTO.getIdCompany()).build()).build();
	}

	public ComputerDTO computertoComputerDTO(Computer computer) {
		String introducedDate = computer.getIntroducedDate()!= null ? computer.getIntroducedDate().toString() : null;
		String discontinuedDate = computer.getDiscontinuedDate()!= null ? computer.getDiscontinuedDate().toString() : null;
		
		return new ComputerDTO.ComputerDTOBuilder().idComputer(computer.getIdComputer()).computerName(computer.getComputerName())
								.introducedDate(introducedDate).discontinuedDate(discontinuedDate)
								.companyDTO(new CompanyDTO.CompanyDTOBuilder().idCompany(computer.getCompany().getIdCompany()).nameCompany(computer.getCompany().getNameCompany()).build()).build();
	}
	
	public List<ComputerDTO> listComputerToListComputerDTO(List<Computer> listComputer){
		List<ComputerDTO> listComputerDTO = new ArrayList<ComputerDTO> ();
		
		for (Computer computer : listComputer) {
			listComputerDTO.add(computertoComputerDTO(computer));
		}
		return listComputerDTO;		
	}

	@Override
	public Computer mapRow(ResultSet result, int rowNum) throws SQLException {
		Date dateSQLDis = result.getDate("discontinued");
		LocalDate dateDis = (dateSQLDis != null ) ? dateSQLDis.toLocalDate() : null;
		Date dateSQLInt = result.getDate("introduced");
		LocalDate dateInt = (dateSQLInt != null ) ? dateSQLInt.toLocalDate() : null;
		int id = result.getInt("id");
		int company_id = result.getInt("company_id");
		String company_name = result.getString("company.name");
		String name = result.getString("name");

		return  new Computer.ComputerBuilder().idComputer(id).computerName(name).introducedDate(dateInt).discontinuedDate(dateDis)
									.company(new Company.CompanyBuilder().idCompany(company_id)
									.nameCompany(company_name).build()).build();

	}

}