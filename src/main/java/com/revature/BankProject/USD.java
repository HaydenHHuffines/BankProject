package com.revature.BankProject;

public class USD {
	private int cents = -1;
	private long dollars = -1;
	public String errorMsg = "-1";

	USD(String input) {

		// todo make sure basic non-empty, no specials, ect string checking already done
		// by other

		String[] splitStr = input.split(".");
		dollars = Long.parseLong(splitStr[0]);
		cents = Integer.parseInt(splitStr[1]);

		if (splitStr.length > 2) {
			errorMsg = "too many '.' symbols";
		} else if (splitStr.length == 1) {
			cents = 0;
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

	public void addUSD(USD b) {
		long retDollars = dollars + b.dollars;
		int retCents = cents + b.cents;

		if (retCents > 99) {
			retCents -= 100;
			retDollars += 1;
		}

		dollars = retDollars;
		cents = retCents;
	}

	public void subtractUSD(USD b) {
		long retDollars = dollars - b.dollars;
		int retCents = cents - b.cents;

		if (retCents < 0) {
			retDollars -= 1;
			retCents += 100;
		}

		if (retDollars < 0) {
			errorMsg = "removing more than the USD amount!\n\tNo change made to this USD!";
		} else {
			dollars = retDollars;
			cents = retCents;
		}

	}

}
