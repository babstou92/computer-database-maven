package com.excilys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.excilys.dao.ComputerDAO;
import com.excilys.models.Computer;

@Service
public class ServiceComputer {
	
	@Autowired
	private ComputerDAO computerDAO;
	
	
	public  List<Computer> findAllComputer() {
		return computerDAO.findAll();
		}
	
	public  List<Computer> findAllComputer(int limite, int offset) {
		return computerDAO.findAll(limite, offset);	
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

	public void updateOneComputer(Computer computer) {
		computerDAO.update(computer);
	}
	
	public int countComputer() {
		return computerDAO.nbComputer();
	}
	
	public List<Computer> searchComputerByName(int limite, int offset, String name) {
		return computerDAO.searchComputerByName(limite, offset, name);
	}
	
	public int countComputerByName(String name) {
		return computerDAO.nbComputerByName(name);
	}	

}
