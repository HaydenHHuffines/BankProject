package com.revature.BankProject;

public class User {
	String name = "-1";
	String password = "-1";
	int ID = -1;
	char type = 'x';
	char status = 'x';

	User(char userType, char userStatus, int userID, String userName, String userPass) {
		type = userType;
		status = userStatus;
		ID = userID;
		name = userName;
		password = userPass;
	}
	
	User(){
		
	}

}
