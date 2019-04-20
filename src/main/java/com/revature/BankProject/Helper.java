package com.revature.BankProject;
import  javafx.util.Pair;

public class Helper {
	Pair<String, Boolean> cleanInput(String userInput) {
		String retInput = userInput.trim();
		boolean validInput = true;
		
		String specialCharacters = ".*[\\{}();:].*" ;

		if (retInput.matches(specialCharacters)) {
			System.out.println("no special characters are allowed: \"\\{}();:\""
					+ "\ntry again");
			
			retInput = "-2";
			validInput = false;
		}
		
		if(retInput.length() > 64) {
			System.out.println("no input above 64 characters in length is allowed");
			retInput = "-2";
			validInput = false;
		}
		
		return  new Pair<String, Boolean>(retInput, validInput);
	}
}
