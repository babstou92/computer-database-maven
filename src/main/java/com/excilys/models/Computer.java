package com.excilys.models;

import java.util.Date;

import com.excilys.models.Company;

public class Computer {
	
	private int idComputer;
	private String nameComputer;
	private Date introducedDateComputer;
	private Date discontinuedDateComputer;
	private  int companyIdComputer;
	private Company company;
	
	public Computer(int id, String name, Date introduced, Date discontinued, Company company) {
		this.idComputer = id;
		this.nameComputer = name;
		this.introducedDateComputer = introduced;
		this.discontinuedDateComputer = discontinued;
		this.company = company;
	}
	
	
	public int getIdComputer() {
		return idComputer;
	}
	
	public void setIdComputer(int idComputer) {
		this.idComputer = idComputer;
	}
	
	public String getNameComputer() {
		return nameComputer;
	}
	
	public void setNameComputer(String nameComputer) {
		this.nameComputer = nameComputer;
	}
	
	public Date getIntroducedDateComputer() {
		return introducedDateComputer;
	}
	
	public void setIntroducedDateComputer(Date introducedDateComputer) {
		this.introducedDateComputer = introducedDateComputer;
	}
	
	public Date getDiscontinuedDateComputer() {
		return discontinuedDateComputer;
	}
	
	public void setDiscontinuedDateComputer(Date discontinuedDateComputer) {
		this.discontinuedDateComputer = discontinuedDateComputer;
	}
	
	public int getCompanyIdComputer() {
		return companyIdComputer;
	}
	
	public void setCompanyIdComputer(int companyIdComputer) {
		this.companyIdComputer = companyIdComputer;
	}


	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}


	public String toString() {
		return "Computer [idComputer=" + idComputer + ", nameComputer=" + nameComputer + ", introducedDateComputer="
				+ introducedDateComputer + ", discontinuedDateComputer=" + discontinuedDateComputer
				+", company=" + company + "]";
	}

	
	
}
