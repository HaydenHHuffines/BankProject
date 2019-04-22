package com.revature.BankProject;

import java.util.TreeSet;

public class Menu {
	private String outputText = "";
	private TreeSet<Character> validOptions = new TreeSet<Character>();

	String navigateState(State currentState) {

		outputText = "";
		validOptions.clear();

		outputText += "q -\t quit program\n";
		validOptions.add('e');

		if (currentState.activeUser == null) {
			outputText += "0 -\t login" + "\n" +
						  "1 -\t submit request for new user\n";
			validOptions.add('l');
			validOptions.add('n');
		} else
			switch (currentState.activeUser.type) {
			case 'c': // customer
				if (currentState.activeUser.status == 'a') { // active/approved user
					if (currentState.activeAccount == null) {
						// load and list valid accounts
						outputText += "\tplease enter the account number of the account you wish to use\n";
						validOptions.add('a'); // change active/selected account
					}
					else{
						
					}

				}
				break;
			}
		return outputText;
	}

	public State checkAndRunState(State currentState, char option, String cleanInputString) {
		State newState = null;

		if (validOptions.contains(option)) {
			runState(option, currentState, cleanInputString);
		}

		return newState;
	}

	private State runState(char option, State beforeState, String cleanInputString) {
		State retState = beforeState;

		switch (option) {
		case 'q':// exit
			System.out.println("inside runState: " + 'q');
			break;
		case 'l': // login
			System.out.println("inside runState: " + 'l');
			break;
		case 'n': // new user application
			System.out.println("inside runState: " + 'n');
			break;

		case 'a': // change active/selected account
			System.out.println("inside runState: " + 'n');
			// todo make get account from ID
			// retState.activeAccount = Integer.parseInt(cleanInputString) ;
		}
		return retState;
	}

}
