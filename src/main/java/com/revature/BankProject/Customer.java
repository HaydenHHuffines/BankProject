package com.revature.BankProject;
import java.util.Vector;

public class Customer extends User{
	public boolean approved = false;
	
	Vector<Integer> accountIDs = new Vector<Integer>();
	
	public Vector<Integer> getAccountIDs(){
		return accountIDs;
	}
	
	void testInheritance() {
        System.out.println("printing user data from customer: "+ name);
	}
	
}
