package com.excilys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.excilys.dao.CompanyDAO;
import com.excilys.models.Company;

@Service
public class ServiceCompany {
    
	@Autowired
    private CompanyDAO companyDAO;
    
    public List<Company> findAllCompany() {
    	return companyDAO.findAll();
    }
    
    public void deleteOneCompany(int companyName) {
    	companyDAO.deleteCompany(companyName);
    	return;
    }
}
