package com.excilys.service;

import com.excilys.dao.CompanyDAO;
import com.excilys.models.Company;

public class ServiceCompany {
    
    private static CompanyDAO companyDAO = CompanyDAO.getCompanyDAO();
    
    
    public static void findAllCompany() {
        for(Company company : companyDAO.findAll()) {
            System.out.println(company.toString());
        }
    }
}
