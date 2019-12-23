package com.excilys.dto;


public class CompanyDTO {
	
	private int idCompany;
	private String nameCompany;
	
	private CompanyDTO(CompanyDTOBuilder builder) {
		this.idCompany = builder.idCompany;
		this.nameCompany = builder.nameCompany;
	}
    
    public static class CompanyDTOBuilder {
    	
    	private int idCompany;
    	private String nameCompany;
    	
    	public CompanyDTOBuilder() {};
    	
    	public CompanyDTOBuilder idCompany(int idCompany) {
    		this.idCompany = idCompany;
    		return this;
    	}
    	public CompanyDTOBuilder nameCompany(String nameCompany) {
    		this.nameCompany = nameCompany;
    		return this;
    	}
    	
    	public CompanyDTO build() {
    		return new CompanyDTO(this);
    	}
    }
    

	public int getIdCompany() {
		return idCompany;
	}
	
	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}
	
	public String getNameCompany() {
		return nameCompany;
	}
	
	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}


	public String toString() {
		return "CompanyDTO [idCompanyDTO=" + idCompany + ", nameCompanyDTO=" + nameCompany + "]";
	}


	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCompany;
		result = prime * result + ((nameCompany == null) ? 0 : nameCompany.hashCode());
		return result;
	}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyDTO other = (CompanyDTO) obj;
		if (idCompany != other.idCompany)
			return false;
		if (nameCompany == null) {
			if (other.nameCompany != null)
				return false;
		} else if (!nameCompany.equals(other.nameCompany))
			return false;
		return true;
	}

}