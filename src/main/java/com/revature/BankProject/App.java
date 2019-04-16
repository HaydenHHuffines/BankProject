package com.revature.BankProject;

import java.util.Scanner;  // Import the Scanner class

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
//        System.out.println( "test test" );
        
    	Customer customerA = new Customer();
    	customerA.testInheritance();
    	
    	
        Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter text");

        String inputText = myScanner.nextLine();  // Read user input
        System.out.println("Entered Text is: " + inputText);  // Output user input 
        myScanner.close();
        
    }
}
