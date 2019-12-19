package com.excilys.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerMapper;
import com.excilys.models.Company;
import com.excilys.service.ServiceCompany;
import com.excilys.service.ServiceComputer;


//@WebServlet("/ServletAddComputer")
public class ServletAddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ServiceComputer serviceComputer = ServiceComputer.getServiceCOmputer();
	private static ServiceCompany serviceCompany = ServiceCompany.getServiceCompany();
	private static ComputerMapper computerMapper = ComputerMapper.getComputerMapper();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> listCompany = serviceCompany.findAllCompany();
		request.setAttribute("listCompany", listCompany);
		this.getServletContext().getRequestDispatcher( "/view/addComputer.jsp" ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String computerName =  request.getParameter("computerName");
		String dateStringInt = request.getParameter("introduced");
		String dateStringDis = request.getParameter("discontinued");
		int company_id =  Integer.parseInt(request.getParameter("companyId").trim());
		
		ComputerDTO computerDTO = new ComputerDTO.ComputerDTOBuilder().name(computerName)
						.introducedDate(dateStringInt).discontinuedDate(dateStringDis)
						.companyDTO(new CompanyDTO.CompanyDTOBuilder().idCompany(company_id).build()).build();

		
		serviceComputer.createOneComputer(computerMapper.ComputerDTOToComputer(computerDTO));
		
		this.getServletContext().getRequestDispatcher( "/" ).forward( request, response );
		
	}

}
