package com.excilys.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringConfig {
	
	//read spring config java class
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Class.class);
	
	//close the context
//	context.close();
}
