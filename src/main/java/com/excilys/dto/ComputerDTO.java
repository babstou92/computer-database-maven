package com.excilys.dto;

public class ComputerDTO {
	
	private int idComputer;
	private String computerName;
	private String introducedDate;
	private String discontinuedDate;
	private int idCompany;
	private CompanyDTO companyDTO;

	public ComputerDTO() {
		
	}
	private ComputerDTO(ComputerDTOBuilder builder) {
		this.idComputer = builder.idComputer;
		this.computerName = builder.computerName;
		this.introducedDate = builder.introducedDate;
		this.discontinuedDate = builder.discontinuedDate;
		this.companyDTO = builder.companyDTO;
	}
	
	public static class ComputerDTOBuilder {
		
		private int idComputer;
		private String computerName;
		private String introducedDate;
		private String discontinuedDate;
		private CompanyDTO companyDTO;
		
		public ComputerDTOBuilder() {};
		
    	public ComputerDTOBuilder idComputer(int idComputer) {
    		this.idComputer = idComputer;
    		return this;
    	}
    	public ComputerDTOBuilder computerName(String computerName) {
    		this.computerName = computerName;
    		return this;
    	}
    	public ComputerDTOBuilder introducedDate(String introducedDate) {
    		this.introducedDate = introducedDate;
    		return this;
    	}
    	public ComputerDTOBuilder discontinuedDate(String discontinuedDate) {
    		this.discontinuedDate = discontinuedDate;
    		return this;
    	}
    	public ComputerDTOBuilder companyDTO(CompanyDTO companyDTO) {
    		this.companyDTO = companyDTO;
    		return this;
    	}
    	
    	public ComputerDTO build() {
    		return new ComputerDTO(this);
    	}
		
	}


	public int getIdComputer() {
		return idComputer;
	}


	public void setIdComputer(int idComputer) {
		this.idComputer = idComputer;
	}


	public String getComputerName() {
		return computerName;
	}


	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}


	public String getIntroducedDate() {
		return introducedDate;
	}


	public void setIntroducedDate(String introducedDate) {
		this.introducedDate = introducedDate;
	}


	public String getDiscontinuedDate() {
		return discontinuedDate;
	}


	public void setDiscontinuedDate(String discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}


	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}


	public void setCompanyDTO(CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}


	public int getIdCompany() {
		return idCompany;
	}


	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}


	@Override
	public String toString() {
		return "ComputerDTO [idComputer=" + idComputer + ", computerName=" + computerName + ", introducedDate="
				+ introducedDate + ", discontinuedDate=" + discontinuedDate + ", idCompany=" + idCompany
				+ ", companyDTO=" + companyDTO + "]";
	}

	
}
