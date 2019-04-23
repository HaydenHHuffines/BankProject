package com.revature.BankProject;

import java.util.TreeSet;

public class Account {

	TreeSet<Integer> customerIDs = new TreeSet<Integer>();
//	TreeSet<User> customers ; //  maybe this is preferable? 
	USD money;
	char status;

	Account(USD startingBalance, int initialOwnerID) {
		money = startingBalance;
		customerIDs.add(initialOwnerID);
		status = 'a';
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

}
