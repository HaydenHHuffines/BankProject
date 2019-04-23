package com.revature.BankProject;

import java.util.ArrayList;
import java.util.TreeSet;

public class DummyData {

	protected static TreeSet<User> gimmieDummyUsers() {
		TreeSet<User> retTreeSet = new TreeSet<User>();
		// User(char userType, char userStatus, int userID, String userName, String
		// userPass) {
		retTreeSet.add(new User('c', 'p', 4, "customerNumber4", "password", 	"likes apples"));
		retTreeSet.add(new User('c', 'a', 7, "customerNumber7", "password", 	"hates apples"));
		retTreeSet.add(new User('c', 'd', 8, "customerNumber8", "password", 	"secretly eats apples"));
		retTreeSet.add(new User('c', 'a', 9, "customerNumber9", "password", 	"has a puppy named puppy"));
		retTreeSet.add(new User('c', 'a', 10, "customerNumber10", "password", 	"doesn't mind the rain"));

		retTreeSet.add(new User('e', 'a', 15, "Employee Numbah 15", "password", null));
		retTreeSet.add(new User('e', 'a', 16, "Employee Numbah 16", "password", null));

		retTreeSet.add(new User('a', 'a', 42, "Admin Numbah 42", "password",	null));

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
