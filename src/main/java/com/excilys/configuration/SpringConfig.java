package com.excilys.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


@Configuration
@ComponentScan(basePackages = {"com.excilys.configuration","com.excilys.dao","com.excilys.service", "com.excilys.servlets", 
								"com.excilys.pagination", "com.excilys.mapper", "com.excilys.validation"})
public class SpringConfig extends AbstractContextLoaderInitializer{

	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringConfig.class);
		return rootContext;
	}
	
	
	

}
