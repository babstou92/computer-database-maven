package com.excilys.models;

public class Company {
	
	private int idCompany;
	private String nameCompany;
	
	
	public Company(int idCompany, String nameCompany) {
		this.idCompany = idCompany;
		this.nameCompany = nameCompany;
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
		return "Company [idCompany=" + idCompany + ", nameCompany=" + nameCompany + "]";
	}

	
}
