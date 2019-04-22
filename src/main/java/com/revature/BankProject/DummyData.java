package com.revature.BankProject;

import java.util.ArrayList;
import java.util.TreeSet;

public class DummyData {

	protected static TreeSet<User> gimmieDummyUsers() {
		TreeSet<User> retTreeSet = new TreeSet<User>();
		// User(char userType, char userStatus, int userID, String userName, String
		// userPass) {
		retTreeSet.add(new User('c', 'p', 4, "customerNumber4", "password"));
		retTreeSet.add(new User('c', 'a', 7, "customerNumber7", "password"));
		retTreeSet.add(new User('c', 'd', 8, "customerNumber8", "password"));
		retTreeSet.add(new User('c', 'a', 9, "customerNumber9", "password"));
		retTreeSet.add(new User('c', 'a', 10, "customerNumber10", "password"));

		retTreeSet.add(new User('e', 'a', 15, "Employee Numbah 15", "password"));
		retTreeSet.add(new User('e', 'a', 16, "Employee Numbah 16", "password"));

		retTreeSet.add(new User('a', 'a', 42, "Admin Numbah 42", "password"));

		return retTreeSet;
	}

	protected static ArrayList<Account> gimmieDummyAccounts() {
		ArrayList<Account> retSet = new ArrayList<Account>();

		// Account(USD startingBalance, int initialOwnerID){

		retSet.add(new Account(new USD("4000.00"), 4));
		retSet.add(new Account(new USD("7000"), 7));
		retSet.add(new Account(new USD("8000.00"), 8));
		retSet.add(new Account(new USD("10000"), 10));

		return retSet;
	}

}
