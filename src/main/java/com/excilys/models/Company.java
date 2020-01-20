package com.excilys.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company {
	
	@Id
	@Column(name="id")
	private int idCompany;
	@Column(name="name")
	private String nameCompany;
	
	private Company(CompanyBuilder builder) {
		this.idCompany = builder.idCompany;
		this.nameCompany = builder.nameCompany;
	}
    
    public static class CompanyBuilder {
    	
    	private int idCompany;
    	private String nameCompany;
    	
    	public CompanyBuilder() {};
    	
    	public CompanyBuilder idCompany(int idCompany) {
    		this.idCompany = idCompany;
    		return this;
    	}
    	public CompanyBuilder nameCompany(String nameCompany) {
    		this.nameCompany = nameCompany;
    		return this;
    	}
    	
    	public Company build() {
    		return new Company(this);
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
