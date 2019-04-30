package com.revature.BankProject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements Comparable<User> {
	String name = "-1";
	String password = "-1";
	int ID = -1;
	char type = 'x';
	char status = 'x';
	String extraNote = "-1";

	User(char userType, char userStatus, int userID, String userName, String userPass,
			String extraNoteIn) {
		type = userType;
		status = userStatus;
		ID = userID;
		name = userName;
		password = userPass;
		extraNote = extraNoteIn;
	}

	User() {

	}
	
	protected void approveUser() {
		status = 'a';
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

	protected static User parseRS(ResultSet rs) {
		// id name pass type status note
		int id = -1;
		String name = "-1";
		String password = "-1";
		char type = 'x';
		char status = 'x';
		String note = "-1";
		
		
		try {
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					String columnValue = rs.getString(i);
					if(i == 1)
						id = Integer.parseInt(columnValue);
					else if(i == 2)
						name = columnValue;
					else if(i == 3)
						password = columnValue;
					else if(i ==4)
						type = columnValue.charAt(0);
					else if(i == 5)
						status = columnValue.charAt(0);
					else if(i == 6)
						note = columnValue;
//					System.out.print(columnValue + " " + rsmd.getColumnName(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		User retUser = new User(type, status, id, name, password, note);
		if(retUser.type == 'x' || retUser.status == 'x' || retUser.ID == -1 || retUser.name.equals("-1") || retUser.password.contentEquals("-1"))
			System.out.println("User not created correctly, check meeeeee");
		
		return retUser;
	}
	
}
