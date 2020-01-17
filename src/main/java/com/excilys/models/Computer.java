package com.excilys.models;

import java.time.LocalDate;
import com.excilys.models.Company;


public class Computer {
	
	private int idComputer;
	private String name;
	private LocalDate introducedDate;
	private LocalDate discontinuedDate;
	private Company company;

	
	private Computer(ComputerBuilder builder) {
		this.idComputer = builder.idComputer;
		this.name = builder.name;
		this.introducedDate = builder.introducedDate;
		this.discontinuedDate = builder.discontinuedDate;
		this.company = builder.company;
	}
	
	public static class ComputerBuilder {
		
		private int idComputer;
		private String name;
		private LocalDate introducedDate;
		private LocalDate discontinuedDate;
		private Company company;
		
		public ComputerBuilder() {};
		
    	public ComputerBuilder idComputer(int idComputer) {
    		this.idComputer = idComputer;
    		return this;
    	}
    	public ComputerBuilder name(String name) {
    		this.name = name;
    		return this;
    	}
    	public ComputerBuilder introducedDate(LocalDate introducedDate) {
    		this.introducedDate = introducedDate;
    		return this;
    	}
    	public ComputerBuilder discontinuedDate(LocalDate discontinuedDate) {
    		this.discontinuedDate = discontinuedDate;
    		return this;
    	}
    	public ComputerBuilder company(Company company) {
    		this.company = company;
    		return this;
    	}
    	
    	public Computer build() {
    		return new Computer(this);
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


	public LocalDate getIntroducedDate() {
		return introducedDate;
	}


	public void setIntroducedDate(LocalDate introducedDate) {
		this.introducedDate = introducedDate;
	}


	public LocalDate getDiscontinuedDate() {
		return discontinuedDate;
	}


	public void setDiscontinuedDate(LocalDate discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public String toString() {
		return "Computer [idComputer=" + idComputer + ", nameComputer=" + name + ", introducedDateComputer="
				+ introducedDate + ", discontinuedDateComputer=" + discontinuedDate
				+", company=" + company + "]";
	}
	
	
}
