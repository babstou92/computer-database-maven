package com.excilys.mapper;


import com.excilys.dto.CompanyDTO;
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
	
	public Company CompanyDTOToCompany(CompanyDTO companyDTO) {	
		
		return new Company.CompanyBuilder().idCompany(companyDTO.getIdCompany()).nameCompany(companyDTO.getNameCompany()).build();

	}
	
	public CompanyDTO CompanyToCompanyDTO(Company company) {

		return new CompanyDTO.CompanyDTOBuilder().idCompany(company.getIdCompany()).nameCompany(company.getNameCompany()).build();

	}
}
