package com.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.models.Company;



public class CompanyDAO  {
	
	//instanciation unique non-initialisée
	private static CompanyDAO companyDAO = null;
	private static final String SELECT_ALL_COMPUTER  = "SELECT * FROM company ;";												
	//appelle la methode static de la classe CoSQL - stocke le return dans l'objet connect de la classe Connection
	public Connection connect = ConnectionSQL.seConnecter();
	
	//constructeur privé pour garantir une unique instance de la classe
	private CompanyDAO() {};
	

	public static CompanyDAO getCompanyDAO() {
		if(companyDAO == null) {
			companyDAO = new CompanyDAO();
		}
		return companyDAO;
	}
	
	//methode pour recup tous les elements de la table Company
	public List<Company> findAll() {
		
		List<Company> companyList = new ArrayList<Company>();
		
		try {             
			//on stocke le resultat de la requete dans l'obj resultat - this correspond a l'instance de CompanyDAO appellant la methode
			PreparedStatement statement = connect.prepareStatement(SELECT_ALL_COMPUTER);
			ResultSet resultat = statement.executeQuery();
			
			// tant que l'element suivant de la requete existe, on creer un nouvel objet company basé sur le modele 
			// le nouvel objet company est add a la liste companyList
			while (resultat.next()) {

				int id = resultat.getInt("id");
				String name = resultat.getString("name");
				Company company = new Company.CompanyBuilder().idCompany(id).nameCompany(name).build();
				companyList.add(company); 
				}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return companyList;
	}

}
