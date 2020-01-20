package com.excilys.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.excilys.models.Company;

@Entity
@Table(name="computer")
public class Computer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int idComputer;
	@Column(name="name")
	private String computerName;
	@Column(name="introduced")
	private LocalDate introducedDate;
	@Column(name="discontinued")
	private LocalDate discontinuedDate;
	@OneToOne
	@JoinColumn(name = "company_id")
	private Company company;

	
	private Computer(ComputerBuilder builder) {
		this.idComputer = builder.idComputer;
		this.computerName = builder.computerName;
		this.introducedDate = builder.introducedDate;
		this.discontinuedDate = builder.discontinuedDate;
		this.company = builder.company;
	}
	
	public static class ComputerBuilder {
		
		private int idComputer;
		private String computerName;
		private LocalDate introducedDate;
		private LocalDate discontinuedDate;
		private Company company;
		
		public ComputerBuilder() {};
		
    	public ComputerBuilder idComputer(int idComputer) {
    		this.idComputer = idComputer;
    		return this;
    	}
    	public ComputerBuilder computerName(String computerName) {
    		this.computerName = computerName;
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


	public String getComputerName() {
		return computerName;
	}


	public void setComputerName(String computerName) {
		this.computerName = computerName;
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
		return "Computer [idComputer=" + idComputer + ", nameComputer=" + computerName + ", introducedDateComputer="
				+ introducedDate + ", discontinuedDateComputer=" + discontinuedDate
				+", company=" + company + "]";
	}
	
	
}
