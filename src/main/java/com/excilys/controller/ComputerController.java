package com.excilys.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerMapper;
import com.excilys.models.Company;
import com.excilys.models.Computer;
import com.excilys.pagination.Page;
import com.excilys.service.ServiceCompany;
import com.excilys.service.ServiceComputer;
import com.excilys.validation.ValidationFront;


@Controller
public class ComputerController {
	

	private ServiceComputer serviceComputer;

	private  ServiceCompany serviceCompany;

	private ComputerMapper computerMapper;

	private Page page;
	
	public ComputerController (ServiceComputer serviceComputer, ServiceCompany serviceCompany, ComputerMapper computerMapper, Page page) {
		this.serviceComputer = serviceComputer;
		this.serviceCompany = serviceCompany;
		this.computerMapper = computerMapper;
		this.page = page;
		
	}
	
	@GetMapping("/")
	public String getDashboard( @RequestParam(required = false, defaultValue = "0") int limit,
								@RequestParam(required = false, defaultValue = "1") int pageNumero, 
								@RequestParam(required = false, defaultValue = "") String search, 
								Model model) {

		int offset = 0, currentPage = 1, nbComputer = 0, nbPage = 0;
		currentPage = pageNumero;
		offset = page.calculOffset(currentPage);

		if(limit != 0) {	
			
			page.setLimite(limit);	
			
		}	
		
		if(search != "") {
			
			List<Computer> listComputer = serviceComputer.searchComputerByName(page.getLimite(), offset, search);
			nbComputer= serviceComputer.countComputerByName(search);
			nbPage = page.nbPageTotal(nbComputer);
			model.addAttribute("listComputer", listComputer);
			model.addAttribute("search", search);

		} else {

			List<Computer> listComputer = serviceComputer.findAllComputer(page.getLimite(), offset);
			nbComputer = serviceComputer.countComputer();
			nbPage = page.nbPageTotal(nbComputer);
			model.addAttribute("listComputer", listComputer);

		}
		
		model.addAttribute("nbComputer", nbComputer);
		model.addAttribute("nbPage", nbPage);
		model.addAttribute("currentPage", currentPage);
	
		return "dashboard";
	}
	
	@PostMapping("/")
	public String postDashboard(@RequestParam(required = false, defaultValue = "") String selection) {
		
		String [] checkboxTableau = selection.split(",");
		for(String id : checkboxTableau) {
			
			serviceComputer.deleteOneComputer(Integer.parseInt(id));
			
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/addcomputer")
	public String getAddComputer(Model model) {
		
		List<Company> listCompany = serviceCompany.findAllCompany();
		model.addAttribute("listCompany", listCompany);
		model.addAttribute("computer", new ComputerDTO.ComputerDTOBuilder().build());
		
		return "addComputer";
		
	}
	
	@PostMapping("/addcomputer")
	public String postAddComputer(@ModelAttribute("computer")ComputerDTO computerDTO, Model model ) {

		Boolean ValidationNameIsEmpty = ValidationFront.verificationNameComputerIsEmpty(computerDTO.getComputerName());	
		if(!ValidationNameIsEmpty) {			
			Computer computer = computerMapper.computerDTOtoComputer(computerDTO);
			serviceComputer.createOneComputer(computer);

			return "redirect:/";
			
		} else {
			
			List<Company> listCompany = serviceCompany.findAllCompany();
			model.addAttribute("listCompany", listCompany);
			model.addAttribute("ValidationNameIsEmpty", ValidationNameIsEmpty);
		
			return "addComputer";
			
		}
	}
	
	@GetMapping("/editcomputer")
	public String getEditComputer(@RequestParam(required = true) int computer_id, Model model) {

		Computer computer = serviceComputer.findOneComputer(computer_id);
		List<Company> listCompany = serviceCompany.findAllCompany();
		model.addAttribute("listCompany", listCompany);
		model.addAttribute("computer", computerMapper.computertoComputerDTO(computer));

		return "editComputer";
		
	}

	@PostMapping("/editcomputer")
	public String postEditComputer(@ModelAttribute("computer")ComputerDTO computerDTO) {
		
        serviceComputer.updateOneComputer(computerMapper.computerDTOtoComputer(computerDTO)); 
        
        return "redirect:/";
        
	}

}
