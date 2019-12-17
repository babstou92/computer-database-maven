package com.excilys.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.excilys.models.Computer;
import com.excilys.pagination.Page;
import com.excilys.service.ServiceComputer;

//@WebServlet(name = "Dashboard", urlPatterns = "/dashboard")
public class ServletMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ServiceComputer serviceComputer = ServiceComputer.getServiceCOmputer();
	private static Page page = Page.getPage();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int currentPage = 1;

		int nbComputer = serviceComputer.countComputer();
		int nbPage = page.nbPageTotal(nbComputer);
		List<Computer> listComputer = serviceComputer.findAllComputer();
		System.out.println(listComputer);
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
