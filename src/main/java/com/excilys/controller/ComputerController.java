package com.excilys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
//				int limitInt = Integer.parseInt(limit);
				page.setLimite(limit);
			} catch (NumberFormatException e) {
				
			}
		}
		
		if (pageNumero != 0) {
			try {
//				currentPage = Integer.parseInt(page);
				offset = page.calculOffset(pageNumero);
			} catch (NumberFormatException e) {
				return "500";
			}			
		} else {
			offset = page.calculOffset(pageNumero);
			System.out.println(offset);
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
	public String postDashboard(HttpServletRequest request, Model model) {
		
		String checkbox = request.getParameter("selection");
		String checkboxNettoyée = checkbox.replaceAll(",", " ");
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
	public String postAddComputer(HttpServletRequest request, Model model) {
		String computerName =  request.getParameter("computerName");
		String dateStringInt = request.getParameter("introduced");
		String dateStringDis = request.getParameter("discontinued");
		int company_id =  Integer.parseInt(request.getParameter("companyId").trim());
		Boolean ValidationNameIsEmpty = ValidationFront.verificationNameComputerIsEmpty(computerName);
	
		if(!ValidationNameIsEmpty) {

			ComputerDTO computerDTO = new ComputerDTO.ComputerDTOBuilder().name(computerName)
										.introducedDate(dateStringInt).discontinuedDate(dateStringDis)
										.companyDTO(new CompanyDTO.CompanyDTOBuilder().idCompany(company_id).build()).build();
	
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
	public String getEditComputer(HttpServletRequest request) {

		int computerId = Integer.parseInt(request.getParameter("computer_id"));
		Computer computer = serviceComputer.findOneComputer(computerId);
		List<Company> listCompany = serviceCompany.findAllCompany();
		request.setAttribute("listCompany", listCompany);
		request.setAttribute("computer", computer);

		return "editComputer";
	}

	@PostMapping("/editcomputer")
	public String postEditComputer(HttpServletRequest request, Model model) {
		
		String computerName = request.getParameter("computerName");
		String dateStringInt = request.getParameter("introduced");
		String dateStringDis = request.getParameter("discontinued");
		int company_id = Integer.parseInt(request.getParameter("companyId").trim());
		int computerId = Integer.parseInt(request.getParameter("id"));
		
		ComputerDTO computerDTO = new ComputerDTO.ComputerDTOBuilder().idComputer(computerId).name(computerName)
									.introducedDate(dateStringInt).discontinuedDate(dateStringDis)
									.companyDTO(new CompanyDTO.CompanyDTOBuilder().idCompany(company_id).build()).build();

		
        serviceComputer.updateOneComputer(computerMapper.ComputerDTOToComputer(computerDTO)); 
        
        return "redirect:/";
	}

}
