package com.excilys.servlets;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.models.Computer;
import com.excilys.pagination.Page;
import com.excilys.service.ServiceComputer;

//@WebServlet(name = "Dashboard", urlPatterns = "/dashboard")
public class ServletMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ServiceComputer serviceComputer = ServiceComputer.getServiceCOmputer();
	private Page page = Page.getPage();
	private static final Logger LOGGER = LoggerFactory.getLogger(ServletMain.class); 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BasicConfigurator.configure();
		LOGGER.info("Start ...");
		
		
		int offset = 0;
		int currentPage = 1;
		int nbComputer = serviceComputer.countComputer();
		int nbPage = page.nbPageTotal(nbComputer);

		if(request.getParameter("limit") != null) {
			try {
				int limit = Integer.parseInt(request.getParameter("limit"));
				page.setLimite(limit);
				nbPage = page.nbPageTotal(nbComputer);
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
		
		List<Computer> listComputer = serviceComputer.findAllComputer(page.getLimite(), offset);
		request.setAttribute("nbComputer", nbComputer);
		request.setAttribute("nbPage", nbPage);
		request.setAttribute("listComputer", listComputer);
		request.setAttribute("currentPage", currentPage);
		

		this.getServletContext().getRequestDispatcher( "/view/dashboard.jsp" ).forward( request, response );
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request,response);
	}

}
