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

}
