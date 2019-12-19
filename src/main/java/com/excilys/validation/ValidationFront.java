package com.excilys.validation;

public class ValidationFront {
	
	private static ValidationFront validationFront;

	private ValidationFront() {}

	public static ValidationFront getValidationFront() {
		
		if (validationFront == null) {
			validationFront = new ValidationFront();
		}
		return validationFront;
	}
	
	public boolean verificationNameComputerIsEmpty(String nameComputer) {
		if(nameComputer.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
}
