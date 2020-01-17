package com.excilys.validation;

import java.time.LocalDate;
import com.excilys.exception.CheckDateIntervale;
import com.excilys.exception.CheckNameException;

public class Validation {
	
	public static void computerNameIsEmpty(String nameComputer) throws CheckNameException{
		if(nameComputer == "") {
			throw new CheckNameException("you must enter a computer name");
		}
	}
	
	public static void discontinuedDateIsLater(LocalDate discontinued, LocalDate introduced) throws CheckDateIntervale{
		if(discontinued != null && introduced != null && introduced.isAfter(discontinued)) {		
			throw new CheckDateIntervale("introduced date must be earlier than discontinued date");
		} 	
	}
}
