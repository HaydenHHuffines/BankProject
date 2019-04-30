package com.revature.BankProject;

public class USD {
	private int cents = -1;
	private long dollars = -1;
	public String errorMsg = "-1";
	private boolean isValid = false;

	USD(String input) {

		// todo make sure basic non-empty, no specials, ect string checking already done
		// by other

		String[] splitStr = input.split("\\.");
		
		if (splitStr.length > 1) {
			dollars = Long.parseLong(splitStr[0]);
			cents = Integer.parseInt(splitStr[1]);
		} else {
			dollars = Long.parseLong(input);
//					dollars = 1;
		}

		// todo extra check for cents length
		if (splitStr.length > 2) {
			errorMsg = "too many '.' symbols";
		} else if (splitStr.length == 1) {
			cents = 0;
		}

		if (dollars >= 0 && cents >= 0) {
			isValid = true;
		}

		if (splitStr.length == 2 && splitStr[1].length() != 2) {
			errorMsg = "only two decimal places after the period cents is permitted";
		}

		if (dollars == -1 || cents == -1) {
			errorMsg = "check inside USD class for error";
			throw new ArithmeticException("invalid creation of USD");
		}

	}

	public boolean checkIfValid() {
		boolean isValid = true;

		if (dollars < 0)
			isValid = false;
		if (cents < 0)
			isValid = false;
		else if (cents > 99)
			isValid = false;

		return isValid;
	}

	public boolean addUSD(USD b) {
		long retDollars = dollars + b.dollars;
		int retCents = cents + b.cents;

		if (retCents > 99) {
			retCents -= 100;
			retDollars += 1;
		}

		dollars = retDollars;
		cents = retCents;
		
		return true;
	}

	// todo add no negative USDs
	public boolean subtractUSD(USD b) {
		long retDollars = dollars - b.dollars;
		int retCents = cents - b.cents;

		if (retCents < 0) {
			retDollars -= 1;
			retCents += 100;
		}

		if (retDollars < 0) {
			errorMsg = "removing more than the USD amount!\n\tNo change made to this USD!";
			return false;
		} else {
			dollars = retDollars;
			cents = retCents;
			return true;
		}

	}

	public String toString() {
		return "" + dollars +"."+cents;
	}
	
}
