package org.payroll;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DatabaseManager {
	
	String ConnectionString;
	
	Connection conn;
	Statement curs;
	
	public DatabaseManager(String db) {
		ConnectionString = "jdbc:sqlite:" + db;
		
		if (!(new File(db)).exists()) {
			connectToDatabase();
			initNewDatabase();
		} else {
			connectToDatabase();
		}
	}
	
	void connectToDatabase() {
		try {
			conn = DriverManager.getConnection(ConnectionString);
			curs = conn.createStatement();
			curs.setQueryTimeout(30);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	void initNewDatabase() {
		try {
			curs.executeUpdate(
					"CREATE TABLE login_ids(id INTEGER NOT NULL PRIMARY KEY, username STRING NOT NULL, password STRING NOT NULL)"
				);
			curs.executeUpdate(
					"INSERT INTO login_ids VALUES(null, \"admin\", \"password\")"
				);
			curs.executeUpdate(
					"CREATE TABLE Position(" +
							"id INTEGER NOT NULL PRIMARY KEY," +
							"pos_name STRING NOT NULL," +
							"hourly_rate INTEGER NOT NULL," +
							"overtime_rate INTEGER NOT NULL," +
					")"
				);
			curs.executeUpdate(
					"CREATE TABLE employees(" +
						"id INTEGER NOT NULL PRIMARY KEY," +
						"first_name STRING NOT NULL," +
						"last_name STRING NOT NULL," +
						"email STRING NOT NULL," +
						"position STRING NOT NULL" +
					")"
				);
			curs.executeUpdate(
					"CREATE TABLE clock_in(" +
							"clock_in_id INTEGER NOT NULL PRIMARY KEY," +
							"emp_id INTEGER NOT NULL," +
							"attendance_date DATE NOT NULL," +
							"clock_in_time TIME NOT NULL," +
							")"
			);
			curs.executeUpdate(
					"CREATE TABLE clock_out(" +
							"clock_out_id INTEGER NOT NULL PRIMARY KEY," +
							"emp_id INTEGER NOT NULL," +
							"attendance_date DATE NOT NULL," +
							"clock_out_time TIME NOT NULL," +
							")"
			);
			curs.executeUpdate(
					"CREATE TABLE emp_salary(" +
							"salary_id INTEGER NOT NULL PRIMARY KEY," +
							"date_salary DATE NOT NULL," +
							"emp_id INTEGER NOT NULL," +
							"salary_per_day INTEGER NOT NULL," +
							"total_time INTEGER NOT NULL" +
							")"
			);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Boolean verifyLoginId(String username) {
		try {
			return curs.executeQuery(
					"SELECT * FROM login_ids WHERE username=\"" + username + "\""
				).next();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	public Boolean verifyLoginId(String username, String password) {
		try {
			return curs.executeQuery(
					"SELECT * FROM login_ids WHERE username=\"" + username + "\" AND password=\"" + password + "\""
				).next();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	public void createLoginId(String username, String password) {
		try {
			curs.executeUpdate("INSERT INTO login_ids VALUES(null, \"" + username + "\", \"" + password + "\")");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void deleteLoginId(String username) {
		try {
			curs.executeUpdate(
					"DELETE FROM login_ids WHERE username=\"" + username + "\""
				);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void changePassword(String username, String newPassword) {
		try {
			curs.executeUpdate(
					"UPDATE login_ids SET password=\"" + newPassword + "\" WHERE username=\"" + username + "\""
				);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Boolean existsPosition(String pos_name) {
		try {
			return curs.executeQuery(
					"SELECT * FROM Position WHERE pos_name=\"" + pos_name + "\""
				).next();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	public void newPosition(String pos_name, int hourly_rate, int overtime_rate) {
		
		try {
			curs.executeUpdate(
					"INSERT INTO Position VALUES(" +
							"null," +
							"\"" + pos_name + "\" ," +
							Integer.toString(hourly_rate) + "," +
							Integer.toString(overtime_rate) +
					")"
				);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void deletePosition(String pos_name) {
		try {
			curs.executeUpdate(
					"DELETE FROM Position WHERE dep_name=\"" + pos_name + "\""
				);
			curs.executeUpdate(
					"DELETE FROM employees WHERE department=\"" + pos_name + "\""
				);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void updatePosition(String pos_name, int hourly_rate, int overtime_rate) {
		deletePosition(pos_name);
		newPosition(pos_name, hourly_rate, overtime_rate);
	}
	
	public ArrayList<String> getListOfPositions() {
		ArrayList<String> lst = new ArrayList<String>();
		
		try {
			ResultSet rs = curs.executeQuery("SELECT pos_name FROM Position");
			
			while (rs.next()) {
				lst.add(rs.getString("pos_name"));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return lst;
	}
	
	public int getSalary(String dep_name) {
		try {
			ResultSet rs = curs.executeQuery("SELECT net_salary FROM departments WHERE dep_name=\"" + dep_name + "\"");
			
			if (rs.next())
				return rs.getInt("net_salary");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return 0;
	}
	
	public Boolean existsEmployeeID(int id) {
		try {
			return curs.executeQuery(
					"SELECT * FROM employees WHERE id=" + Integer.toString(id)
				).next();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	public void createEmployee(String fn, String ln, String email, String position) {
		try {
			curs.executeUpdate("INSERT INTO employees VALUES(" +
					"null," +
					"\"" + fn + "\"," +
					"\"" + ln + "\"," + 
					"\"" + email + "\"," +
					"\"" + position + "\"" +
				")");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void deleteEmployee(int id) {
		try {
			curs.executeUpdate(
					"DELETE FROM employees WHERE id=" + Integer.toString(id)
				);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void updateEmployee(int id, String fn, String ln, String email, String position) {
		try {
			curs.executeUpdate(
					"UPDATE employees SET " +
					"first_name=\"" + fn + "\"," +
					"last_name=\"" + ln + "\"," +
					"email=\"" + email + "\"," +
					"position=\"" + position + "\" " +
					"WHERE id=" + Integer.toString(id)
				);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Object[][] getEmployees() {
		ArrayList<Object[]> employees = new ArrayList<Object[]>();
		ResultSet rs;
		
		try {
			rs = curs.executeQuery(
					"SELECT * FROM employees"
				);
			
			while (rs.next()) {
				Object[] temp = {
					rs.getInt("id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("email"),
					rs.getString("department"),
					getSalary(rs.getString("department"))
				};
				
				employees.add(temp);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return employees.toArray(new Object[employees.size()][]);
	}
}
