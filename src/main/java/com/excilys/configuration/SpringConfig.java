package com.excilys.configuration;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.excilys.configuration","com.excilys.dao","com.excilys.service", 
								"com.excilys.pagination", "com.excilys.mapper", "com.excilys.controller"})
@PropertySource(value= "classpath:database.properties")
public class SpringConfig implements WebApplicationInitializer, WebMvcConfigurer  {
	
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
	
    @Bean
    public ViewResolver internalResourceViewResolver() {
      InternalResourceViewResolver bean = new InternalResourceViewResolver();
      bean.setViewClass(JstlView.class);
      bean.setPrefix("/view/");
      bean.setSuffix(".jsp");
      return bean;
    }

	@Override
	public void onStartup(ServletContext servletContext) {

		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(SpringConfig.class);
		webContext.setServletContext(servletContext);
		
		ServletRegistration.Dynamic registration =  servletContext.addServlet("springDispatcherServlet", new DispatcherServlet(webContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource ret = new ReloadableResourceBundleMessageSource();
		ret.setBasename("classpath:messages");
		ret.setDefaultEncoding("utf-8");
		return ret;
	}

	@Bean
	public CookieLocaleResolver localeResolver(){
	    CookieLocaleResolver resolver = new CookieLocaleResolver();
	    resolver.setDefaultLocale(Locale.ENGLISH);
	    resolver.setCookieName("myCookie");
	    resolver.setCookieMaxAge(3600);
	    return resolver;
	}

	@Bean 
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("locale");
        return localeChangeInterceptor;
    }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	

}
