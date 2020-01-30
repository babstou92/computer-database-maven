package com.excilys.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.excilys.dao.ComputerDAO;
import com.excilys.dto.ComputerDTO;
import com.excilys.models.Computer;

@Service
public class ServiceComputer {

	private ComputerDAO computerDAO;
	public ServiceComputer(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}
	
	
	public  List<Computer> findAllComputer() {
		return computerDAO.findAll();
		}
	
	public  List<ComputerDTO> findAllComputerDTO() {
		return computerDAO.findAllDTO();
		}
	
	public  List<Computer> findAllComputer(int limite, int offset) {
		return computerDAO.findAll(limite, offset);	
		}

	public  Computer findOneComputer(int id) {	
		return computerDAO.findOne(id);
	}
	
	public  boolean deleteOneComputer(int id) {
		return computerDAO.delete(id);
	}
	
	public boolean createOneComputer(Computer computer) {		
		 return computerDAO.create(computer);
	}

	public boolean updateOneComputer(Computer computer) {
		return computerDAO.update(computer);
	}
	
	public int countComputer() {
		return computerDAO.nbComputer();
	}
	
	public List<Computer> searchComputerByName(int limite, int offset, String name) {
		return computerDAO.searchComputerByName(name, limite, offset);
	}
	
	public int countComputerByName(String name) {
		return computerDAO.nbComputerByName(name);
	}	

}
