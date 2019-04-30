package com.revature.BankProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBHandler {

	protected static Connection con;

	protected static void establishConneciton() {
		con = DoNotPush.getRemoteConnection();
//		System.out.println("BP stalling in DBHandler establishConnection");
	}

	private static ResultSet doExecuteQuery(String queryIn) {
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs = stmt.executeQuery(queryIn);

			// printSQLResult(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (rs == null)
			System.out.println("DBHandler doSelectQuery's result set is coming out null!");

		return rs;
	}

	private static int doExecuteUpdate(String queryIn) {
		java.sql.Statement stmt = null;
		int rowsAffected = -1;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rowsAffected = stmt.executeUpdate(queryIn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (rowsAffected == -1) {
			System.out.println("DBHandler's doExecuteQuery seems to have malfunctioned!");
		}

		return rowsAffected;
	}

	private static int insertReturnID(String queryIn) {
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(queryIn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet rs = null;
		try {
			rs = stmt.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int newAccountID = -1;

		try {
			if (rs.next()) {
				newAccountID = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newAccountID;
	}

	protected static boolean insertPendingCustomer(String name, String password) {
		if (con == null) {
			System.out.println("InsertIntoUsers trying to make a new user in the DB when connection == null, yikes");
			return false;
		}

		String insertQuery = "INSERT INTO Users " + "(Name, Password, Type, Status, Note)" + "VALUES (  '" + name
				+ "', '" + password + "', 'c', 'p', 'new pending customer')";

		int rowsAffected = doExecuteUpdate(insertQuery);
		if (rowsAffected != -1)
			return true;
		else
			return false;

		/*
		 * // INSERT INTO Users // (Name, Password, Type, Status, Note ) // VALUES //
		 * ('Abe', 'AbePass', 'c', 'p', 'new pending user');
		 */
	}

	protected static boolean insertNewAccount(User user) {
		if (con == null) {
			System.out.println("InsertNewAccount trying to make a new user in the DB when connection == null, yikes");
			return false;
		}

		String insertQuery = "INSERT INTO Accounts RETURNING ID;";

		int newAccountID = insertReturnID(insertQuery);

		if (insertNewJoinTableEntry(user.ID, newAccountID))
			return true;
		else {
			System.out.println("uh-oh, SQL stuff going wrong");
			return false;
		}
	}

	protected static boolean insertNewCustomer(String inputString) {
		String[] splitStr = inputString.split(" ");
		String query = "INSERT INTO Users (Name, Password, Status ) " + "VALUES ('" + splitStr[1] + "', '" + splitStr[2]
				+ "', 'p');";

		int rowsAffected = doExecuteUpdate(query);
		if (rowsAffected == 1)
			return true;
		else
			return false;
	}

	protected static boolean insertNewJoinTableEntry(int u_ID, int a_ID) {
		String insertQuery = "INSERT INTO Users_Accounts (uID, aID) VALUES (" + u_ID + ", " + a_ID + ");";

		if (doExecuteUpdate(insertQuery) != -1) {
			return true;
		} else
			return false;
	}

	protected static void printSQLResult(java.sql.ResultSet rsIn) {
		try {
			java.sql.ResultSetMetaData rsmd = rsIn.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (rsIn.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1)
						System.out.print(",  ");
					String columnValue = rsIn.getString(i);
					System.out.print(columnValue + " " + rsmd.getColumnName(i));
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected static void testDummyQuery() {
		printSQLResult(doExecuteQuery("SELECT * FROM testTable1"));
	}

	protected static User loginCheck(String name, String password) {
		ResultSet rs = doExecuteQuery(
				"SELECT * FROM Users WHERE Name = '" + name + "' AND Password = '" + password + "';");

		User retUser = null;
//		System.out.println("stalling for breakpoint");

		retUser = User.parseRS(rs);
		return retUser;
	}

	protected static ArrayList<Account> getUserAccounts(int userID) {
		ArrayList<Account> retArrLst = new ArrayList<Account>();
		ArrayList<Integer> aIDs = new ArrayList<Integer>();

		ResultSet rs = doExecuteQuery("SELECT aID FROM Users_Accounts where uID = " + userID + ";");

		try {
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					String columnValue = rs.getString(i);
					int aID = Integer.parseInt(columnValue);
					aIDs.add(aID);
					// System.out.print(columnValue + " " + rsmd.getColumnName(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int aID : aIDs) {
			retArrLst.add(getAccount(aID));
		}

		return retArrLst;
	}

	protected static Account getAccount(int aID) {
		ResultSet rs = doExecuteQuery("SELECT * FROM Accounts where ID = " + aID + ";");

		return Account.parseRS(rs);
	}

	protected static User getUserFromName(String nameIn) {
		ResultSet rs = doExecuteQuery("SELECT * FROM Users WHERE Name = '" + nameIn + "';");
		User retUser = null;
		retUser = User.parseRS(rs);
		return retUser;
	}

	protected static boolean addJointUser(User activeUser, Account activeAccount, String joinee) {
		User joineeUser = getUserFromName(joinee);
		if (joineeUser.status == 'a')
			if (insertNewJoinTableEntry(joineeUser.ID, activeAccount.id))
				return true;
			else
				return false;
		else return false;
	}

}