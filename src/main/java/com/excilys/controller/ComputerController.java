package com.excilys.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
								HttpServletRequest request, Model model) {

		int offset = 0;
		int currentPage = 1;
		int nbComputer = 0;
		int nbPage = 0;

		if(limit != 0) {
			try {
				page.setLimite(limit);
			} catch (NumberFormatException e) {
				
			}
		}
		
		if (pageNumero != 1) {
			try {
				currentPage = pageNumero;
				offset = page.calculOffset(currentPage);
			} catch (NumberFormatException e) {
				return "500";
			}			
		} else {
			offset = page.calculOffset(currentPage);
		}
		
		if(search != "") {
			
			List<Computer> listComputer = serviceComputer.searchComputerByName(page.getLimite(), offset, search);
			nbComputer= serviceComputer.countComputerByName(search);
			request.setAttribute("listComputer", listComputer);
			request.setAttribute("search", search);
			nbPage = page.nbPageTotal(nbComputer);
			System.out.println("ici");
		} else {

			List<Computer> listComputer = serviceComputer.findAllComputer(page.getLimite(), offset);
			nbComputer = serviceComputer.countComputer();
			request.setAttribute("listComputer", listComputer);
			nbPage = page.nbPageTotal(nbComputer);

		}
			request.setAttribute("nbComputer", nbComputer);
			request.setAttribute("nbPage", nbPage);
			request.setAttribute("currentPage", currentPage);

		
		return "dashboard";
	}
	
	@PostMapping("/")
	public String postDashboard(@RequestParam(required = false, defaultValue = "") String selection, HttpServletRequest request, Model model) {
		
		String checkboxNettoyée = selection.replaceAll(",", " ");
		String [] checkboxTableau = checkboxNettoyée.split(" ");
		for(String id : checkboxTableau) {
			
				serviceComputer.deleteOneComputer(Integer.parseInt(id));	
				
		}
		return "redirect:/";
	}
	
	@GetMapping("/addcomputer")
	public String getAddComputer(HttpServletRequest request) {
		
		List<Company> listCompany = serviceCompany.findAllCompany();
		request.setAttribute("listCompany", listCompany);
		
		return "addComputer";
	}
	
	@PostMapping("/addcomputer")
	public String postAddComputer(@RequestParam(required = false) String computerName, 
								  @RequestParam(required = false, defaultValue = "") String introduced,
								  @RequestParam(required = false, defaultValue = "") String discontinued,
								  @RequestParam(required = false) int companyId,
								  HttpServletRequest request, Model model) {

		Boolean ValidationNameIsEmpty = ValidationFront.verificationNameComputerIsEmpty(computerName);
	
		if(!ValidationNameIsEmpty) {

			ComputerDTO computerDTO = new ComputerDTO.ComputerDTOBuilder().name(computerName)
										.introducedDate(introduced).discontinuedDate(discontinued)
										.companyDTO(new CompanyDTO.CompanyDTOBuilder().idCompany(companyId).build()).build();
	
			serviceComputer.createOneComputer(computerMapper.ComputerDTOToComputer(computerDTO));

			return "redirect:/";
			
		} else {
			
			List<Company> listCompany = serviceCompany.findAllCompany();
			request.setAttribute("listCompany", listCompany);
			request.setAttribute("ValidationNameIsEmpty", ValidationNameIsEmpty);
		
			return "addComputer";
		}
	}
	
	@GetMapping("/editcomputer")
	public String getEditComputer(@RequestParam(required = false) int computer_id, HttpServletRequest request) {

		Computer computer = serviceComputer.findOneComputer(computer_id);
		List<Company> listCompany = serviceCompany.findAllCompany();
		request.setAttribute("listCompany", listCompany);
		request.setAttribute("computer", computer);

		return "editComputer";
	}

	@PostMapping("/editcomputer")
	public String postEditComputer(@RequestParam(required = false, defaultValue = "") String computerName,
								   @RequestParam(required = false, defaultValue = "") String introduced,
								   @RequestParam(required = false, defaultValue = "") String discontinued,
								   @RequestParam(required = false) int companyId,
								   @RequestParam(required = false) int id,
								   HttpServletRequest request, Model model) {

		
		ComputerDTO computerDTO = new ComputerDTO.ComputerDTOBuilder().idComputer(id).name(computerName)
									.introducedDate(introduced).discontinuedDate(discontinued)
									.companyDTO(new CompanyDTO.CompanyDTOBuilder().idCompany(companyId).build()).build();

		
        serviceComputer.updateOneComputer(computerMapper.ComputerDTOToComputer(computerDTO)); 
        
        return "redirect:/";
	}

}
