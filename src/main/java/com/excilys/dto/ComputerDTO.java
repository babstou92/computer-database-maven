package com.excilys.dto;



public class ComputerDTO {
	
	private int idComputer;
	private String name;
	private String introducedDate;
	private String discontinuedDate;
	private CompanyDTO companyDTO;

	
	private ComputerDTO(ComputerDTOBuilder builder) {
		this.idComputer = builder.idComputer;
		this.name = builder.name;
		this.introducedDate = builder.introducedDate;
		this.discontinuedDate = builder.discontinuedDate;
		this.companyDTO = builder.companyDTO;
	}
	
	public static class ComputerDTOBuilder {
		
		private int idComputer;
		private String name;
		private String introducedDate;
		private String discontinuedDate;
		private CompanyDTO companyDTO;
		
		public ComputerDTOBuilder() {};
		
    	public ComputerDTOBuilder idComputer(int idComputer) {
    		this.idComputer = idComputer;
    		return this;
    	}
    	public ComputerDTOBuilder name(String name) {
    		this.name = name;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public String toString() {
		return "Computer [idComputerDTO=" + idComputer + ", nameComputerDTO=" + name + ", introducedDateComputer="
				+ introducedDate + ", discontinuedDateComputerDTO=" + discontinuedDate
				+", companyDTO=" + companyDTO + "]";
	}


	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyDTO == null) ? 0 : companyDTO.hashCode());
		result = prime * result + ((discontinuedDate == null) ? 0 : discontinuedDate.hashCode());
		result = prime * result + idComputer;
		result = prime * result + ((introducedDate == null) ? 0 : introducedDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComputerDTO other = (ComputerDTO) obj;
		if (companyDTO == null) {
			if (other.companyDTO != null)
				return false;
		} else if (!companyDTO.equals(other.companyDTO))
			return false;
		if (discontinuedDate == null) {
			if (other.discontinuedDate != null)
				return false;
		} else if (!discontinuedDate.equals(other.discontinuedDate))
			return false;
		if (idComputer != other.idComputer)
			return false;
		if (introducedDate == null) {
			if (other.introducedDate != null)
				return false;
		} else if (!introducedDate.equals(other.introducedDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
