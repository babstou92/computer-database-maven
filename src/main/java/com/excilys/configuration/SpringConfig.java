package com.excilys.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


@Configuration
@ComponentScan(basePackages = {"com.excilys.configuration","com.excilys.dao","com.excilys.service", "com.excilys.servlets", 
								"com.excilys.pagination"})
@PropertySource(value="classpath:database.properties")
public class SpringConfig extends AbstractContextLoaderInitializer{
	
	@Autowired
	private Environment environment;

	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringConfig.class);
		return rootContext;
	}
	
	@Bean
	 public DataSource seConnecter() {
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
		 dataSource.setDriverClassName(environment.getProperty("dataSource.driverClassName"));
		 dataSource.setUrl(environment.getProperty("dataSource.jdbcUrl"));
		 dataSource.setUsername(environment.getProperty("dataSource.user"));
		 dataSource.setPassword(environment.getProperty("dataSource.password"));
		 return dataSource;
	 }
	
	

}
