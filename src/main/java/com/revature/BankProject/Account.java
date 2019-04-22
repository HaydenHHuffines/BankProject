package com.revature.BankProject;

import java.util.TreeSet;

public class Account {
	
	TreeSet<Integer> customerIDs = new TreeSet<Integer>();
//	TreeSet<User> customers ; //  maybe this is preferable? 
	USD money ;
	
	Account(USD startingBalance, int initialOwnerID){
		money = startingBalance;
		customerIDs.add(initialOwnerID);
	}
	
	
}
