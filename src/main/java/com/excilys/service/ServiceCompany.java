package com.excilys.service;

import java.util.List;

import com.excilys.dao.CompanyDAO;
import com.excilys.models.Company;

public class ServiceCompany {
    
    private static CompanyDAO companyDAO = CompanyDAO.getCompanyDAO();
    
    private ServiceCompany() {};
    
    private static ServiceCompany serviceCompany = null;
    
    public static ServiceCompany getServiceCompany() {
    	if(serviceCompany == null) {
    		serviceCompany = new ServiceCompany();
    	}
    	return serviceCompany;
    }

    
    public List<Company> findAllCompany() {
    	return companyDAO.findAll();
    }
}
