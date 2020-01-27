package com.excilys.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.excilys.mapper.ComputerMapper;
import com.excilys.models.Computer;

@Repository
public class ComputerDAO {
	
	
	private static final String SELECT_ALL_COMPUTER = "SELECT  computer.id, computer.name, computer.introduced, computer.discontinued, "
														+ "computer.company_id, company.name "
														+ "FROM computer "
														+ "LEFT JOIN company ON computer.company_id = company.id "
														+ "ORDER BY computer.id ;";
	
	private static final String SELECT_ALL_COMPUTER_PAGINATION = "SELECT  computer.id, computer.name, computer.introduced, computer.discontinued, "
														+ "computer.company_id, company.name "
														+ "FROM computer "
														+ "LEFT JOIN company ON computer.company_id = company.id "														
														+ "LIMIT ? OFFSET ? ;";
	
	private static final String SELECT_ONE_COMPUTER  = "SELECT  computer.id, computer.name, computer.introduced, computer.discontinued, "
														+ "computer.company_id, company.name "
														+ "FROM computer LEFT JOIN company "
														+ "ON computer.company_id = company.id "
														+ "WHERE computer.id = ? ;";
	
	private static final String CREATE_ONE_COMPUTER = "INSERT into computer (name,introduced,discontinued,company_id) "
														+ "VALUES (?,?,?,?)";
	
	private static final String UPDATE_ONE_COMPUTER = "UPDATE computer "			
														+ "SET name = ? ,"
														+ "introduced = ? ,"
														+ "discontinued = ? ,"
														+ "company_id = ? "
														+ "WHERE id = ? ;";
	
	private static final String DELETE_ONE_COMPUTER = "DELETE  from computer "
														+ "WHERE id = ? ;";
	
	private static final String COUNT_COMPUTER 		= "SELECT COUNT(id) AS nbComputer "
														+ "FROM computer ";
	
	private static final String SEARCH_COMPUTER_BY_NAME = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, "
														+ "computer.company_id, company.name FROM computer "
														+ "LEFT JOIN company ON computer.company_id = company.id "
														+ "WHERE company.name LIKE ? OR computer.name LIKE ? LIMIT ? OFFSET ?";
	
	private static final String COUNT_COMPUTER_BY_NAME 	= "SELECT COUNT(computer.id) AS nbComputerByName "
														+ "FROM computer LEFT JOIN company ON computer.company_id = company.id "
														+ "WHERE company.name LIKE ? OR computer.name LIKE ?";
	
	private JdbcTemplate jdbcTemplate;
	private ComputerMapper computerMapper;
	private SessionFactory sessionFactory;

	public ComputerDAO(DataSource dataSource, ComputerMapper computerMapper, SessionFactory sessionFactory) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.computerMapper = computerMapper;
		this.sessionFactory = sessionFactory;
	}
	
	@PersistenceContext
	EntityManager entityManager;

	public List<Computer> findAll() {
		Session session = sessionFactory.openSession();
		List<Computer> listComputer = session.createQuery(SELECT_ALL_COMPUTER, Computer.class).getResultList();
		
		return listComputer;
	}	
	
	public List<Computer> findAll(int limite, int offset) {
		Session session = sessionFactory.openSession();
		List<Computer> listComputer = session.createQuery(SELECT_ALL_COMPUTER_PAGINATION, Computer.class).getResultList();
			
		return listComputer;
	}
	
	public Computer findOne(int idSearch) {
		
		return jdbcTemplate.queryForObject(SELECT_ONE_COMPUTER, computerMapper, idSearch);
	}
	
//	public Computer findOne(int idSearch) {
//		Session session = sessionFactory.openSession();
//		Computer computer = session.createQuery(SELECT_ONE_COMPUTER, Computer.class).setParameter(0, idSearch).uniqueResult();
//		
//		return computer;
//	}
	
	
	public boolean create(Computer computer) {

		return jdbcTemplate.update(CREATE_ONE_COMPUTER, computer.getComputerName(), computer.getIntroducedDate(), computer.getDiscontinuedDate(),
				computer.getCompany().getIdCompany()) > 0;
	}


	public boolean update(Computer computer) {
						
			return jdbcTemplate.update(UPDATE_ONE_COMPUTER, computer.getComputerName(), computer.getIntroducedDate(), computer.getDiscontinuedDate(),
					computer.getCompany().getIdCompany(), computer.getIdComputer()) > 0;	
	}


	public boolean delete(int idSearch) {

		return jdbcTemplate.update(DELETE_ONE_COMPUTER, idSearch) > 0;
	}
	

	
	public List<Computer> searchComputerByName(String name, int limite, int offset) {

		return jdbcTemplate.query(SEARCH_COMPUTER_BY_NAME, computerMapper, "%"+ name +"%", "%"+ name +"%", limite, offset);
	}
	
	
	public int nbComputer() {

		return jdbcTemplate.queryForObject(COUNT_COMPUTER, Integer.class);
	}
	
	public int nbComputerByName(String name) {
	
		return jdbcTemplate.queryForObject(COUNT_COMPUTER_BY_NAME, Integer.class, "%"+ name +"%", "%"+ name +"%");
	}
	

}
