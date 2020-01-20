package com.excilys.dao;

import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.excilys.mapper.CompanyMapper;
import com.excilys.models.Company;

@Repository
public class CompanyDAO  {
	
	private static final String SELECT_ALL_COMPUTER  		  = "SELECT * FROM company ;";	
	
	//private static final String DELETE_COMPANY_BY_COMPANYID   = "DELETE FROM company where id = ? ;";
	
	//private static final String DELETE_COMPUTER_BY_COMPANYID  = "DELETE FROM computer where company_id = ? ;";
	
	@Autowired
	private CompanyMapper companyMapper;
	
	private JdbcTemplate jdbcTemplate;

	public CompanyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Company> findAll() {
		return  jdbcTemplate.query(SELECT_ALL_COMPUTER, companyMapper );
	}
	
//	public boolean deleteCompany(int companyId) {
//		
//		Connection connect = connectionSQL.seConnecter();
//		
//		try (PreparedStatement statementOne = connect.prepareStatement(DELETE_COMPUTER_BY_COMPANYID);
//			 PreparedStatement statementTwo = connect.prepareStatement(DELETE_COMPANY_BY_COMPANYID);){
//			
//			connect.setAutoCommit(false);
//			
//			statementOne.setInt(1, companyId);
//			statementOne.execute();
//			statementTwo.setInt(1, companyId);
//			statementTwo.executeUpdate();
//			
//			connect.commit();
//			
//		} catch (SQLException e) {
//			
//			system.out.println(e.getMessage());
//			
//		} finally {
//			this.connect = connectionSQL.disconnectDB();
//		}
//		
//		return jdbcTemplate.update(SQL_DELETE_PERSON, person.getId()) > 0;
//		
//	}

}
