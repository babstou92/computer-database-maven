package com.excilys.validation;

import org.springframework.stereotype.Component;

@Component
public class ValidationFront {
	
	public boolean verificationNameComputerIsEmpty(String nameComputer) {
		if(nameComputer.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
}
