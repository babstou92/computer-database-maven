package com.excilys.service;

import com.excilys.dao.ComputerDAO;
import com.excilys.models.Computer;

public class ServiceComputer {
		
	private static ComputerDAO computerDAO = ComputerDAO.getComputerDAO();
	
	private ServiceComputer() {};
	
	private static ServiceComputer serviceComputer = null;
	
	public static ServiceComputer getServiceCOmputer() {
		if(serviceComputer == null) {
			serviceComputer = new ServiceComputer();
		}
		return serviceComputer;
	}
	
	
	public  void findAllComputer() {
		for(Computer computer : computerDAO.findAll()) {
			System.out.println(computer.toString());
		}
	}

	public  void findOneComputer(int id) {	
		System.out.println((computerDAO.findOne(id)).toString());
	}
	
	public  void deleteOneComputer(int id) {
		computerDAO.delete(id);
	}
	
	public void createOneComputer(Computer computer) {
		
		computerDAO.create(computer);
	}

	public  void updateOneComputer(Computer computer) {
		computerDAO.update(computer);
	}

}
