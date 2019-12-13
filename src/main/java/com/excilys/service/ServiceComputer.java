package com.excilys.service;

import java.util.List;

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
	
	
	public  List<Computer> findAllComputer() {
		return computerDAO.findAll();
		
		}

	public  Computer findOneComputer(int id) {	
		return computerDAO.findOne(id);
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
