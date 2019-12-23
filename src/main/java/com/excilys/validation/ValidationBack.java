package com.excilys.validation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationBack {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationBack.class); 
	private static ValidationBack validationBack;
	private ValidationBack() {}

	public static ValidationBack getValidationBack() {
		
		if (validationBack == null) {
			validationBack = new ValidationBack();
		}
		return validationBack;
	}
	
	public boolean verificationDiscontinuedDateIsLater(LocalDate discontinued, LocalDate introduced) {
		long intervale = introduced.until(discontinued, ChronoUnit.DAYS);
		System.out.println(intervale);
		if(intervale > 0) {		
			return true;
		} else {
			LOGGER.info("veuillez entrer une date discontinued plus récente que la date introduced");
			return false;
		}
	}	
	
}
