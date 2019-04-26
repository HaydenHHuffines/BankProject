package com.revature.BankProject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHandler {

	protected static Connection establishConneciton() {
		java.sql.Connection con = DoNotPush.getRemoteConnection();
		
		return con;
	}

	protected static ResultSet doSelectQuery(String queryIn, Connection con) {
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
		
		if(rs == null)
			System.out.println("DBHandler doSelectQuery's result set is coming out null!");
		
		return rs;
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

}
