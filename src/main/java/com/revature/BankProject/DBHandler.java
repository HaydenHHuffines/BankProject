package com.revature.BankProject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = stmt.executeQuery(queryIn);

			printSQLResult(rs);

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

	protected static boolean InsertPendingCustomer(String name, String password) {
		if (con == null) {
			System.out.println("InsertIntoUsers trying to make a new user in the DB when connection == null, yikes");
			return false;
		}

		String insertQuery = "INSERT INTO Users " + "(Name, Password, Type, Status, Note)" + "VALUES (  \'" + name
				+ "\', \'" + password + "\', 'c', 'p', 'new pending user')";

		int rowsAffected = doExecuteUpdate(insertQuery);
		if(rowsAffected != -1)
			return true;
		else return false;

		/*
		 * // INSERT INTO Users // (Name, Password, Type, Status, Note ) // VALUES //
		 * ('Abe', 'AbePass', 'c', 'p', 'new pending user');
		 */
	}
	
//	protected static boolean todo
	

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

//	protected static void 

}
