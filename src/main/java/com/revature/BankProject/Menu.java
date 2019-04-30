package com.revature.BankProject;

import java.util.ArrayList;
import java.util.TreeSet;

public class Menu {
	protected static String optionsText = "";
	protected static String resultText = "";
	private TreeSet<Integer> validOptions = new TreeSet<Integer>();

	String navigateState(State currentState) {

		optionsText = "";
		// resultText = "";
		validOptions.clear();

		optionsText += "q -\t\t quit program\n";
		validOptions.add((int) 'q');

		if (currentState.activeUser == null) {
			optionsText += "l + username + password -\t login" + "\n"
					+ "n + username + password -\t submit request for new user\n";
			validOptions.add((int) 'l');
			validOptions.add((int) 'n');
		} else
			switch (currentState.activeUser.type) {
			case 'c': // customer
				if (currentState.activeUser.status == 'a') { // active/approved user

					if (currentState.activeAccount == null) {

						validOptions.add((int) 'a'); // change active/selected account
						optionsText += "a + account number -\t select account\n";
						printCustomerAccounts(currentState.activeUser.ID);

						validOptions.add((int) 'c'); // create new account
						optionsText += "c -\t\t apply for new account\n";

					} else {
						currentState.activeAccount.print4User();

						validOptions.add((int) 'j'); // change to joint account
						optionsText += "j + username -\t add user as joint account owner\n";

						validOptions.add((int) 'w'); // withdraw
						optionsText += "w + dollars.cents -\t withdraw money from account\n";

						validOptions.add((int) 't'); // transfer
						optionsText += "t + dollars.cents + account number -\t transfer money to other account\n";

						validOptions.add((int) 'd');// deposit
						optionsText += "d + dollars.cents -\t deposit money into selected account\n";
					}
				} else if (currentState.activeUser.status == 'p') { // pending user
					System.out.print("Hey! Thank your for your interest in our bank, once an employee approves"
							+ " your account you will be free to login\n"
							+ "At this time your account is still pending\n");
				}

				break;

			case 'e': // employee
			{
				if (currentState.targetUser == null) {
					validOptions.add((int) 's'); // select user
					optionsText += "s + username OR account number -\t select user\n";
				} else if (currentState.activeAccount == null) {
					validOptions.add((int) 'u'); // approve pending user
					optionsText += "u  -\t\t approve pending user account\n";

					validOptions.add((int) 'x'); // deny pending user
					optionsText += "x  -\t\t deny pending user account\n";

					validOptions.add((int) 'b'); // approve bank account
					optionsText += "b + account number -\t approve bank account";
				}
			}
			case 'a': // admin falling through to this from employee case
				if (currentState.targetUser == null) {

				} else {
//					validOptions.add(1);
//					outputText += "1 "
				}

				if (currentState.activeAccount == null) {

				} else {
					validOptions.add((int) 'j'); // change to joint account
					optionsText += "j + username -\t add user as joint account owner\n";

					validOptions.add((int) 'w'); // withdraw
					optionsText += "w + dollars.cents -\t withdraw money from account\n";

					validOptions.add((int) 't'); // transfer
					optionsText += "t + dollars.cents + account number -\t transfer money to other account\n";

					validOptions.add((int) 'd');// deposit
					optionsText += "d + dollars.cents -\t deposit money into selected account\n";

					validOptions.add(0);// cancel account
					optionsText += "0 - \t\t cancel this account (must be empty)\n";

				}
				break;
			}
		return optionsText;
	}

	public State checkAndRunState(State currentState, Integer option, String cleanInputString) {
		State newState = currentState;

		// todo
		// print result of prev command
		// print active status/location
		// print current options available

		if (validOptions.contains(option)) {
			runState(option, newState, cleanInputString);
		}

		return newState;
	}

	private State runState(Integer option, State beforeState, String cleanInputString) {
		State retState = beforeState;
		String[] splitInput = cleanInputString.split(" ");

		if (splitInput.length > 1 && splitInput[0].length() == 1) {

			switch (option) { // argument taking options
			case (int) 'l': // login
				// System.out.println("inside runState: " + 'l');
				if (splitInput.length > 2) {
					State.activeUser = DBHandler.loginCheck(splitInput[1], splitInput[2]);
				} else
					resultText += "Invalid login arguments!\n";
				break;
			case (int) 'n': // new user application
				System.out.println("inside runState: " + 'n');
				if (cleanInputString.split(" ").length == 3)
					DBHandler.insertNewCustomer(cleanInputString);
				resultText += "Now awaiting employee approval before your login becomes active.\n";
				break;

			case (int) 'a': // change active/selected account
				System.out.println("inside runState: " + 'a');

				try {
					int inpAccID = Integer.parseInt(splitInput[1]);

					boolean foundIt = false;
					for (int a_id : getCustomerAccounts(retState.activeUser.ID)) {
						if (a_id == inpAccID)
							try {
								retState.activeAccount = DBHandler.getAccount(inpAccID);
								foundIt = true;
							} catch (Exception e) {
								throw new NumberFormatException();
							}
						else {
						}
					}
					if (!foundIt)
						throw new NumberFormatException();
				} catch (NumberFormatException e) {
					resultText += "Hey! Valid Account IDs only!\n";
				}
				break;

			case (int) 'j':// add joint user
				// todo DBHANDLLLLLEEEER
				if (splitInput.length == 2)
					if (DBHandler.addJointUser(retState.activeUser, retState.activeAccount, splitInput[1]))
						resultText += "User " + splitInput[1] + " should now have access to this account!\n";
					else
						resultText += "No change was made, check your syntax and username\n";
				break;

			case (int) 'w': // withdraw money
				if (splitInput.length == 2) {
					try {
						USD toWithdraw = new USD(splitInput[1]);
					} catch (Exception e) {
						resultText += "Hey, you need to enter a valid dollar.cents to attempt a withdraw\n";
					}
				}
				break;
			}

		} else {
			switch (option) { // no argument options
			case (int) 'q':// exit
//			System.out.println("inside runState: " + 'q');
				App.closeAndExit();
				break;
			}
		}

		return retState;
	}

	protected void printCustomerAccounts(int id) {
		// load and list valid accounts
		ArrayList<Account> accounts = DBHandler.getUserAccounts(id);
		for (Account acc : accounts) {
			System.out.print(acc.print4User());
		}
	}

	protected ArrayList<Integer> getCustomerAccounts(int id) {
		ArrayList<Integer> retArrLst = new ArrayList<Integer>();
		ArrayList<Account> accounts = DBHandler.getUserAccounts(id);
		for (Account acc : accounts) {
			retArrLst.add(acc.id);
		}
		return retArrLst;
	}

	protected static void addToOutput(String addition) {
		if (addition != null)
			optionsText += addition;
	}

}
