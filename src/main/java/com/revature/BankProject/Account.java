package com.revature.BankProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

public class Account {

	TreeSet<Integer> customerIDs = new TreeSet<Integer>();
//	TreeSet<User> customers ; //  maybe this is preferable? 
	USD money;
	char status;
	int id;

	Account(USD startingBalance, int initialOwnerID) {
		money = startingBalance;
		customerIDs.add(initialOwnerID);
		status = 'a';
	}

	Account(String startingBalance, int aID, char statusIn){
		money = new USD(startingBalance);
		id = aID;
		status = statusIn;
	}
	
	protected boolean isActiveAccount() {
		if (status == 'a')
			return true;
		else
			return false;
	}

	protected boolean MakeDeposit(USD depositAmount) {
		if (depositAmount.checkIfValid() && status == 'a') {
			money.addUSD(depositAmount);
			return true;
		} else
			return false;
	}

	protected boolean MakeWithdrawal(USD withdrawalAmount) {
		if (withdrawalAmount.checkIfValid()) {
			if (money.subtractUSD(withdrawalAmount)) { // if true, subtraction done, if false, value unchanged
				return true; // cashing out
			}
		}
		return false;
	}

	protected boolean MakeTransfer(USD transferAmount, Account recipient) {
		if (transferAmount.checkIfValid()) {
			if (recipient.isActiveAccount()) {
				if (this.MakeWithdrawal(transferAmount)) {
					recipient.money.addUSD(transferAmount);
					return true;
				}
			}
		}
		return false;
	}

	protected  String print4User() {
		String retStr = "Account ID: " +id+"\t Available Balance: "+ money.toString()+"\n";
		
		return retStr;
	}
	protected  String print4Employee() {
		String retStr = "Account ID: " +id+"\t Available Balance: "+ money+"\t Status: " + status+"\n";
		
		return retStr;
	}
	
	
	protected static Account parseRS(ResultSet rs) {
		int id = -1;
		char status = 'x';
		String balance = "-1";
		
		try {
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					String columnValue = rs.getString(i);
					if(i == 1)
						id = Integer.parseInt(columnValue);
					else if(i == 2)
						status = columnValue.charAt(0);
					else if(i == 3)
						balance = columnValue;
					// System.out.print(columnValue + " " + rsmd.getColumnName(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		System.out.println("Breakpoints! getcha breakpoints!");
		return new Account(balance, id, status);
		
	}
}
