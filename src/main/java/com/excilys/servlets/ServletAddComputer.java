package com.excilys.servlets;

import java.io.IOException;


import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerMapper;
import com.excilys.models.Company;
import com.excilys.service.ServiceCompany;
import com.excilys.service.ServiceComputer;
import com.excilys.validation.ValidationFront;


@WebServlet(name = "AddComputer", urlPatterns = "/addcomputer")

public class ServletAddComputer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private  ServiceComputer serviceComputer;
	@Autowired
	private  ServiceCompany serviceCompany;
	@Autowired
	ComputerMapper computerMapper;

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Company> listCompany = serviceCompany.findAllCompany();
		request.setAttribute("listCompany", listCompany);
		this.getServletContext().getRequestDispatcher("/view/addComputer.jsp").forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

			response.sendRedirect("/computer-database");
			
		} else {
			
			List<Company> listCompany = serviceCompany.findAllCompany();
			request.setAttribute("listCompany", listCompany);
			request.setAttribute("ValidationNameIsEmpty", ValidationNameIsEmpty);
			this.getServletContext().getRequestDispatcher( "/view/addComputer.jsp" ).forward( request, response );
		}
		
	}

}
