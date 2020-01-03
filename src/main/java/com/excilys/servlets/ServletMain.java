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
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.models.Computer;
import com.excilys.pagination.Page;
import com.excilys.service.ServiceComputer;


@WebServlet(name = "Dashboard", urlPatterns = "/")

public class ServletMain extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private ServiceComputer serviceComputer;
	@Autowired
	private Page page;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
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
				this.getServletContext().getRequestDispatcher("/views/500.jsp").forward(request, response);
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

		this.getServletContext().getRequestDispatcher( "/view/dashboard.jsp" ).forward( request, response );
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String checkbox = request.getParameter("selection");
		String checkboxNettoyée = checkbox.replaceAll(",", " ");
		String [] checkboxTableau = checkboxNettoyée.split(" ");
		for(String id : checkboxTableau) {
			
				serviceComputer.deleteOneComputer(Integer.parseInt(id));	
				
		}
		
		this.doGet(request,response);
	}

}
