package com.revature.BankProject;

import java.util.ArrayList;

public class Menu {
	private String outputText = "";
	private ArrayList<Character> validOptions = new ArrayList<Character>();

	String navigateState(State currentState) {

		outputText = "";
		validOptions.clear();

		outputText += "e -\t exit program\n";
		validOptions.add('e');

		if (currentState.activeUser == null) {
			outputText += "0 -\t login" + "\n" + "1 -\t submit request for new user\n";
			validOptions.add('l');
			validOptions.add('n');
		} else
			switch (currentState.activeUser.type) {
			case 'c': // customer
				if (currentState.activeUser.status == 'a') { // active/approved user
					if (currentState.activeAccount == null) {
						//load and list valid accounts
						outputText += "- 0 ";

					}

				}
				break;
			}
		return outputText;
	}

	public State checkAndRunState(State currentState, char option) {
		State newState = null;
		
		if(validOptions.contains(option)) {
			runState(option);
		}
		
		
		return newState;
	}

	private void runState(char option) {
		switch (option) {
		case 'e':// exit
			System.out.println("inside runState: " + 'e');
			break;
		case 'l': // login
			System.out.println("inside runState: " + 'l');

			break;
		case 'n': //new user application
			System.out.println("inside runState: " + 'n');

			break;
		}
	}

}
