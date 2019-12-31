package com.excilys.mapper;


import java.time.LocalDate;


import java.time.format.DateTimeFormatter;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.models.Company;
import com.excilys.models.Computer;

public class ComputerMapper {
	
	private ComputerMapper() {};
	private static ComputerMapper computerMapper;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	
	public static ComputerMapper getComputerMapper() {
		if(computerMapper == null) {
			computerMapper = new ComputerMapper();
		}		
		return computerMapper;
	}
	
	public  Computer ComputerDTOToComputer(ComputerDTO computerDTO) {
		
		String dateStringInt = computerDTO.getIntroducedDate();
		LocalDate introduced = dateStringInt.equals("") ? null : LocalDate.parse(dateStringInt, formatter);
		String dateStringDis = computerDTO.getDiscontinuedDate();
		LocalDate discontinued = dateStringInt.equals("") ? null : LocalDate.parse(dateStringDis, formatter);
		CompanyDTO companyDTO = computerDTO.getCompanyDTO();
		
		return new Computer.ComputerBuilder().idComputer(computerDTO.getIdComputer()).name(computerDTO.getName())
							.introducedDate(introduced).discontinuedDate(discontinued)
							.company(new Company.CompanyBuilder().idCompany(companyDTO.getIdCompany()).nameCompany(companyDTO.getNameCompany()).build()).build();



	}
	
	public  ComputerDTO ComputerToComputerDTO(Computer computer) {
			 
		
		
		return new ComputerDTO.ComputerDTOBuilder().idComputer(computer.getIdComputer()).name(computer.getName())
								.introducedDate(computer.getIntroducedDate().toString()).discontinuedDate(computer.getDiscontinuedDate().toString())
								.companyDTO(new CompanyDTO.CompanyDTOBuilder().idCompany(computer.getCompany().getIdCompany()).nameCompany(computer.getCompany().getNameCompany()).build()).build();


	}

}