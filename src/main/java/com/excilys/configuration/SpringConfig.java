package com.excilys.configuration;


import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.excilys.configuration","com.excilys.dao","com.excilys.service", 
								"com.excilys.pagination", "com.excilys.mapper", "com.excilys.controller", "com.excilys.dto"})
@PropertySource(value= "classpath:database.properties")
public class SpringConfig implements WebApplicationInitializer {
	
	@Autowired
	private Environment environment;

	@Bean
	 public DataSource seConnecter() {
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
		 dataSource.setDriverClassName(environment.getProperty("dataSource.driverClassName"));
		 dataSource.setUrl(environment.getProperty("dataSource.jdbcUrl"));
		 dataSource.setUsername(environment.getProperty("dataSource.user"));
		 dataSource.setPassword(environment.getProperty("dataSource.password"));
		 return dataSource;
	 }

	@Override
	public void onStartup(ServletContext servletContext) {

		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(SpringConfig.class, SpringMVC.class);
		webContext.setServletContext(servletContext);
		
		ServletRegistration.Dynamic registration =  servletContext.addServlet("springDispatcherServlet", new DispatcherServlet(webContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
	}
		
    

}
