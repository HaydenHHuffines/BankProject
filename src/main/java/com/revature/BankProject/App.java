package com.revature.BankProject;

import java.sql.DriverManager;
import java.sql.SQLException;

//import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
//import com.amazonaws.auth.policy.Statement;
//import com.amazonaws.services.rds.auth.GetIamAuthTokenRequest;
//import com.amazonaws.services.rds.auth.RdsIamAuthTokenGenerator;

import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class
import java.util.TreeSet;

import javafx.util.Pair;

/**
 * Hello world!
 *
 */
public class App {

	protected static State currentState = new State();

	private static boolean keepExecuting = true;

	private static Helper helperObject = new Helper();

	private static Menu mainMenu = new Menu();

	public static void main(String[] args) {

		TreeSet<User> userData = DummyData.gimmieDummyUsers();
		ArrayList<Account> accountData = DummyData.gimmieDummyAccounts();

		

		String inputText = "";

		Scanner myScanner = new Scanner(System.in); // Create a Scanner object
		System.out.println("Welcome to the system!");
		String outputText = "";
		while (keepExecuting) {
			outputText = mainMenu.navigateState(currentState);
			System.out.print(outputText);
			inputText = myScanner.nextLine();
			Pair<String, Boolean> cleanInput = helperObject.cleanInput(inputText);

			if (cleanInput.getValue() == false) // if user attempted invalid input
				continue; // jump back to input loop

			String cleanInputString = cleanInput.getKey();
			// todo fork menu/io on single char commands and username/account id/ect
			char cleanChar = cleanInputString.toLowerCase().charAt(0);

			currentState = mainMenu.checkAndRunState(currentState, cleanChar, cleanInputString);

			Pair<String, Boolean> cleanText = helperObject.cleanInput(inputText);
			inputText = cleanText.getKey();

			if (!cleanText.getValue()) {

			}

		}

		System.out.println("Entered Text is: " + inputText); // Output user input
		myScanner.close();

	}

}
