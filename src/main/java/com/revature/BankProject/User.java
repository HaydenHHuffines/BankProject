package com.revature.BankProject;

public class User implements Comparable<User> {
	String name = "-1";
	String password = "-1";
	int ID = -1;
	char type = 'x';
	char status = 'x';
	String personalInformation = "-1";

	User(char userType, char userStatus, int userID, String userName, String userPass,
			String personalInformationInput) {
		type = userType;
		status = userStatus;
		ID = userID;
		name = userName;
		password = userPass;
		personalInformation = personalInformationInput;
	}

	User() {

	}

	@Override
	public int compareTo(User arg0) {
		if (this.ID == arg0.ID) {
			return 0;
		} else if (this.ID < arg0.ID) {
			return -1;
		} else
			return 1;
	}

}
