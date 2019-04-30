package com.revature.BankProject;

import java.sql.Connection;
import java.sql.SQLException;

//import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
//import com.amazonaws.auth.policy.Statement;
//import com.amazonaws.services.rds.auth.GetIamAuthTokenRequest;
//import com.amazonaws.services.rds.auth.RdsIamAuthTokenGenerator;

import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class
import java.util.TreeSet;

import javafx.util.Pair;


public class App {

	protected static State currentState = new State();

	private static boolean keepExecuting = true;

	private static Helper helperObject = new Helper();

	private static Menu mainMenu = new Menu();

	public static void main(String[] args) {

		TreeSet<User> userData = DummyData.gimmieDummyUsers();
		ArrayList<Account> accountData = DummyData.gimmieDummyAccounts();

		DBHandler.establishConneciton(); // initialize DBHandler's con

//		DBHandler.testDummyQuery();

		String inputText = "";

		Scanner myScanner = new Scanner(System.in); // Create a Scanner object
		System.out.println("Welcome to the system!");
		
		while (keepExecuting) {
			mainMenu.navigateState(currentState);
			System.out.print(mainMenu.optionsText);
			inputText = myScanner.nextLine();
			Pair<String, Boolean> cleanInput = helperObject.cleanInput(inputText);

			if (cleanInput.getValue() == false) {// if user attempted invalid input
				System.out.println("invalid input detected");
				continue; // jump back to input loop
			}

			String cleanInputString = cleanInput.getKey();
			// todo fork menu/io on single char commands and username/account id/ect
			char cleanChar = cleanInputString.toLowerCase().charAt(0);

			currentState = mainMenu.checkAndRunState(currentState, (int)cleanChar, cleanInputString);

			System.out.print(mainMenu.resultText);
			mainMenu.resultText = "";
//			System.out.println("breaaaaak poooooint");
		}

		System.out.println("Entered Text is: " + inputText); // Output user input
		myScanner.close();

	}
	
	protected static void closeAndExit() {
		System.out.println("Thank you for using this system!");
		
		if(DBHandler.con != null)
			try {
				DBHandler.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		System.exit(0);
	}

}
