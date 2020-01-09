package com.excilys.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@Autowired
	private ServiceComputer serviceComputer;
	@Autowired
	private  ServiceCompany serviceCompany;
	@Autowired
	ComputerMapper computerMapper;
	@Autowired
	private Page page;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String dashboard(HttpServletRequest request) {
		
		int offset = 0;
		int currentPage = 1;
		int nbComputer = 0;
		int nbPage = 0;
		
		if(request.getParameter("limit") != null) {
			try {
				int limit = Integer.parseInt(request.getParameter("limit"));
				page.setLimite(limit);
			} catch (NumberFormatException e) {
				
			}
		}
		
		if (request.getParameter("page") != null) {
			try {
				currentPage = Integer.parseInt(request.getParameter("page"));
				offset = page.calculOffset(currentPage);
			} catch (NumberFormatException e) {
				return "/views/500.jsp";
			}
		} else {
			offset = page.calculOffset(currentPage);
		}
		
		if(request.getParameter("search") != null) {
			
			List<Computer> listComputer = serviceComputer.searchComputerByName(page.getLimite(), offset, request.getParameter("search"));
			nbComputer= serviceComputer.countComputerByName(request.getParameter("search"));
			request.setAttribute("listComputer", listComputer);
			request.setAttribute("search", request.getParameter("search"));
			nbPage = page.nbPageTotal(nbComputer);
			
		} else {

			List<Computer> listComputer = serviceComputer.findAllComputer(page.getLimite(), offset);
			nbComputer = serviceComputer.countComputer();
			request.setAttribute("listComputer", listComputer);
			nbPage = page.nbPageTotal(nbComputer);
		}
			request.setAttribute("nbComputer", nbComputer);
			request.setAttribute("nbPage", nbPage);
			request.setAttribute("currentPage", currentPage);

		
		return "/view/dashboard.jsp";
	}
	
	@RequestMapping(value="/addcomputer", method = RequestMethod.GET)
	public String getAddComputer(HttpServletRequest request) {
		
		List<Company> listCompany = serviceCompany.findAllCompany();
		request.setAttribute("listCompany", listCompany);
		
		return "/view/addComputer.jsp";
	}
	
	@RequestMapping(value="/addcomputer", method = RequestMethod.POST)
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
		
			return "/view/addComputer.jsp";
		}
	}
}
