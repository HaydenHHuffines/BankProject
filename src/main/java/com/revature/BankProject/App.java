package com.revature.BankProject;

import java.util.Scanner; // Import the Scanner class
import  javafx.util.Pair;

/**
 * Hello world!
 *
 */
public class App {
	private static User activeUser = null;
	private static Account activeAccount = null;
	private static User targetUser = null;
	private static Account targetAccount = null;
	
	
//	private static long activeUserID = -1;
//	private static long targetUserID = -1;
//
//	private static long activeAccountID = -1;
//	private static long targetAccountID = -1;

	private static boolean keepExecuting = true;
	
	private static Helper helperObject = new Helper();
	

	public static void main(String[] args) {

		String inputText = "";

		Scanner myScanner = new Scanner(System.in); // Create a Scanner object
		System.out.println("Welcome to the system!");
		while (keepExecuting) {
			if (activeUser == null) {
				System.out.println("- 0\t login"
							+ "\n" + "- 1\t submit request for new user");
			}
			else switch(activeUser.type) {
				case 'c': //customer
					if(activeUser.status == 'a') { //active/approved user
						if(activeAccount == null) { 
							System.out.println("- 0 ");

							
						}
						

					}
					break;
			}
			
			
			
			inputText = myScanner.nextLine();
			Pair<String, Boolean> cleanText = helperObject.cleanInput(inputText);
			inputText = cleanText.getKey();
			
			if( ! cleanText.getValue()) {
				
			}
			
		}

		System.out.println("Entered Text is: " + inputText); // Output user input
		myScanner.close();

	}
}
