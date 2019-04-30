package com.revature.BankProject;

import java.util.TreeSet;

public class Menu {
	private static String outputText = "";
	private TreeSet<Integer> validOptions = new TreeSet<Integer>();

	String navigateState(State currentState) {

		outputText = "";
		validOptions.clear();

		outputText += "q -\t quit program\n";
		validOptions.add((int) 'q');

		if (currentState.activeUser == null) {
			outputText += "l + username + password -\t login" + "\n"
					+ "n + username password -\t submit request for new user\n";
			validOptions.add((int) 'l');
			validOptions.add((int) 'n');
		} else
			switch (currentState.activeUser.type) {
			case 'c': // customer
				if (currentState.activeUser.status == 'a') { // active/approved user

					if (currentState.activeAccount == null) {
						// load and list valid accounts
						validOptions.add((int) 'a'); // change active/selected account
						// todo DBHandler.getUserAccounts(alkasjdlfk);
						outputText += "a + account number -\t select account\n";

						validOptions.add((int) 'c'); // create new account
						outputText += "c -\t\t apply for new account\n";

					} else {
						validOptions.add((int) 'j'); // change to joint account
						outputText += "j + username -\t add user as joint account owner\n";

						validOptions.add((int) 'w'); // withdraw
						outputText += "w + dollars.cents -\t withdraw money from account\n";

						validOptions.add((int) 't'); // transfer
						outputText += "t + dollars.cents + account number -\t transferr money to other account\n";

						validOptions.add((int) 'd');// deposit
						outputText += "d + dollars.cents -\t deposit money into selected account\n";
					}
				}
				break;

			case 'e': // employee
			{
				if(currentState.targetUser == null) {			
					validOptions.add((int) 's'); // select user
					outputText += "s + username OR account number -\t select user\n";					
				}
				else if(currentState.activeAccount == null) {
					validOptions.add((int) 'u'); // approve pending user
					outputText +="u  -\t\t approve pending user account\n";
					
					validOptions.add((int) 'x'); //deny pending user
					outputText +="x  -\t\t deny pending user account\n";
					
					validOptions.add((int) 'b'); // approve bank account
					outputText +="b + account number -\t approve bank account";
				}
			}
				break;
			}
		return outputText;
	}

	public State checkAndRunState(State currentState, Integer option, String cleanInputString) {
		State newState = null;

		//todo
		//print result of prev command
		//print active status/location
		//print current options available
		
		if (validOptions.contains(option)) {
			runState(option, currentState, cleanInputString);
		}

		return newState;
	}

	private State runState(Integer option, State beforeState, String cleanInputString) {
		State retState = beforeState;

		switch (option) {
		case (int) 'q':// exit
//			System.out.println("inside runState: " + 'q');
			App.closeAndExit();
			break;
		case (int) 'l': // login
			System.out.println("inside runState: " + 'l');
			break;
		case (int) 'n': // new user application
			System.out.println("inside runState: " + 'n');
			break;

		case (int) 'a': // change active/selected account
			System.out.println("inside runState: " + 'n');
			// todo make get account from ID
			// retState.activeAccount = Integer.parseInt(cleanInputString) ;
		}
		return retState;
	}

	protected static void addToOutput(String addition) {
		if (addition != null)
			outputText += addition;
	}

}
