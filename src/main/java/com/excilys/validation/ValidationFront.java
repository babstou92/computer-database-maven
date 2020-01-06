package com.excilys.validation;


public class ValidationFront {
	
	public static boolean verificationNameComputerIsEmpty(String nameComputer) {
		if(nameComputer.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
}
