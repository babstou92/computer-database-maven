package com.excilys.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.models.Company;
import com.excilys.models.Computer;
import com.excilys.service.ServiceCompany;
import com.excilys.service.ServiceComputer;


//@WebServlet("/ServletAddComputer")
public class ServletAddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ServiceComputer serviceComputer = ServiceComputer.getServiceCOmputer();
	private static ServiceCompany serviceCompany = ServiceCompany.getServiceCompany();
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> listCompany = serviceCompany.findAllCompany();
		request.setAttribute("listCompany", listCompany);
		this.getServletContext().getRequestDispatcher( "/view/addComputer.jsp" ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String computerName =  request.getParameter("computerName");
		String dateStringInt = request.getParameter("introduced");
		LocalDate introduced = LocalDate.parse(dateStringInt, formatter);
		String dateStringDis = request.getParameter("discontinued");
		LocalDate discontinued = LocalDate.parse(dateStringDis, formatter);
		int company_id = (int) Integer.parseInt(request.getParameter("companyId").trim());

		
		serviceComputer.createOneComputer(  new Computer.ComputerBuilder().name(computerName).introducedDate(introduced)
				.discontinuedDate(discontinued).company(new Company.CompanyBuilder()
				.idCompany(company_id).build()).build());
		
		this.getServletContext().getRequestDispatcher( "/" ).forward( request, response );
		
	}

}
