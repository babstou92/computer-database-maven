package com.excilys.service;


import java.sql.Date;

import com.excilys.dao.ComputerDAO;
import com.excilys.models.Computer;

public class ServiceComputer {
	
	
	private static ComputerDAO computerDAO = ComputerDAO.getComputerDAO();
	
	
	public static void findAllComputer() {
		for(Computer computer : computerDAO.findAll()) {
			System.out.println(computer.toString());
		}
	}

	public static void findOneComputer(int id) {	
		System.out.println((computerDAO.findOne(id)).toString());
	}
	
	public static void deleteOneComputer(int id) {
		computerDAO.delete(id);
	}
	
	public static void createOneComputer(String computerName, Date introduced, Date discontinued, int company_id) {
		computerDAO.create(computerName, introduced, discontinued, company_id);
	}

	public static void updateOneComputer(String computerName, Date introduced, Date discontinued, int company_id, int idSearch) {
		computerDAO.update(computerName, introduced, discontinued, company_id, idSearch);
	}

}
