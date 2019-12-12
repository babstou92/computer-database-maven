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
	
	public String getNameComputer() {
		return name;
	}
	
	public void setNameComputer(String nameComputer) {
		this.name = nameComputer;
	}
	
	public LocalDate getIntroducedDateComputer() {
		return introducedDate;
	}
	
	public void setIntroducedDateComputer(LocalDate introducedDateComputer) {
		this.introducedDate = introducedDateComputer;
	}
	
	public LocalDate getDiscontinuedDateComputer() {
		return discontinuedDate;
	}
	
	public void setDiscontinuedDateComputer(LocalDate discontinuedDateComputer) {
		this.discontinuedDate = discontinuedDateComputer;
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


	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
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
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
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
