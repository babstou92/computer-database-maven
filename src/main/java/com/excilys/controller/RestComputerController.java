package com.excilys.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerMapper;
import com.excilys.pagination.Page;
import com.excilys.service.ServiceCompany;
import com.excilys.service.ServiceComputer;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/computers")
public class RestComputerController {
	

	private ServiceComputer serviceComputer;
	private ComputerMapper computerMapper;
	
	public RestComputerController (ServiceComputer serviceComputer, ServiceCompany serviceCompany, ComputerMapper computerMapper, Page page) {
		this.serviceComputer = serviceComputer;
		this.computerMapper = computerMapper;		
	}
	
	@CrossOrigin
	@GetMapping
	public List<ComputerDTO> findAllDTO() {	
		return serviceComputer.findAllComputerDTO();
	}
	
	@CrossOrigin
	@PostMapping
	public boolean create(ComputerDTO computerDTO) {	
		return serviceComputer.createOneComputer(computerMapper.computerDTOtoComputer(computerDTO));
	}
	
	@CrossOrigin
	@GetMapping("/{id}")
	public boolean delete(int id) {	
		return serviceComputer.deleteOneComputer(id);
	}
	
	@CrossOrigin
	@PutMapping
	public boolean edit(ComputerDTO computerDTO) {	
		return serviceComputer.updateOneComputer(computerMapper.computerDTOtoComputer(computerDTO) );
	}
}
