package org.payroll;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class DatabaseManager {

    String ConnectionString;

    Connection conn;
    Statement curs;

    public DatabaseManager(String db) {
        ConnectionString = "jdbc:sqlite:" + db;

        if (!((new File(db)).exists())) {
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
                    "INSERT INTO login_ids VALUES(null, \"admin\", \"1\")"
            );
            curs.executeUpdate(
                    "CREATE TABLE Position(" +
                            "id INTEGER NOT NULL PRIMARY KEY," +
                            "pos_name STRING NOT NULL," +
                            "hourly_rate INTEGER NOT NULL," +
                            "overtime_rate INTEGER NOT NULL)"

            );
            curs.executeUpdate(
                    "CREATE TABLE employees(" +
                            "id STRING NOT NULL PRIMARY KEY," +
                            "first_name STRING NOT NULL," +
                            "last_name STRING NOT NULL," +
                            "email STRING NOT NULL," +
                            "pos_name STRING NOT NULL)"

            );
            curs.executeUpdate(
                    "CREATE TABLE Attendance(" +
                            "clock_in_id INTEGER NOT NULL PRIMARY KEY," +
                            "emp_id STRING NOT NULL," +
                            "attendance_date DATE NOT NULL ," +
                            "clock_in_time TIME NULL ," +
                            "clock_out_time TIME DEFAULT NULL )"

            );
            curs.executeUpdate(
                    "CREATE TABLE emp_salary(" +
                            "salary_id INTEGER NOT NULL PRIMARY KEY," +
                            "date_salary DATE NOT NULL," +
                            "emp_id STRING NOT NULL," +
                            "emp_name STRING NOT NULL," +
                            "salary_per_day INTEGER NOT NULL," +
                            "total_time INTEGER NOT NULL)"
            );
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public Boolean verifyLoginId(String id) {
        try {
            return curs.executeQuery(
                    "SELECT * FROM login_ids WHERE id=\"" + id + "\""
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

    public void changePassword(String Password, String newPassword) {
        try {
            curs.executeUpdate(
                    "UPDATE login_ids SET password=\"" + newPassword + "\" WHERE password=\"" + Password + "\""
            );
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void changeUsername(String newusername, String Password) {
        try {
            curs.executeUpdate(
                    "UPDATE login_ids SET username=\"" + newusername + "\" WHERE password=\"" + Password + "\""
            );
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public Boolean VerifyClockin(String empID) {
        try {
            return curs.executeQuery(
                    "SELECT  clock_in_time FROM Attendance WHERE ( emp_id =\"" + empID + "\")"
                            + "AND ( attendance_date = CURRENT_DATE ) AND ( clock_in_time is not null ) "
            ).next();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    public Boolean VerifyClockOut(String empID) {
        try {
            return curs.executeQuery(
                    "SELECT clock_out_time FROM Attendance WHERE ( emp_id =\"" + empID + "\")"
                            + "AND ( attendance_date = CURRENT_DATE ) AND ( clock_out_time is not null ) "
            ).next();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }


    public void PunchIn(String empID) {
        try {
            curs.executeUpdate(
                    "INSERT INTO Attendance ( clock_in_id, emp_id , attendance_date , clock_in_time )" +
                            "VALUES( null ,\"" + empID + "\"," + "CURRENT_DATE , CURRENT_TIME )"
            );
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public void PunchOut(String empID) {
        try {
            curs.executeUpdate(
                    "UPDATE Attendance " +
                            "SET clock_out_time = CURRENT_TIME " +
                            "WHERE ( emp_id = \"" + empID + "\")" +
                            "AND (attendance_date = CURRENT_DATE)"

            );
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public void InsertEmpSalary(String Date , String emp_id , String Emp_Fullname , int totalPay , int totalHour ){
        try{
            curs.executeUpdate("INSERT INTO emp_salary ( date_salary , emp_id , emp_name , salary_per_day , total_time )" +
                    " VALUES (" + "\"" + Date + "\"," + "\"" + emp_id + "\"," + "\"" + Emp_Fullname + "\"," + Integer.toString(totalPay)
                    + "," + Integer.toString(totalHour) + ")");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void InsertEmployeeSalaryFromAttandance(String empid) {
        String Date, emp_id , emp_fullname ;
        int hourly_rate, OT_rate, total_hour, TotalPay;

        try {
            ResultSet rs = curs.executeQuery(
                    "SELECT emp_id , attendance_date , (((strftime(%m,clock_out_time)) - (strftime(%m,clock_in_time)))/60) AS 'Total_hour'  " + "FROM Attendance" +
                            " WHERE emp_id =\"" + empid + "\" AND attendance_date = CURRENT_DATE"
            );
            while (rs.next()) {

                emp_id = rs.getString("emp_id");
                Date = rs.getString("attendance_date");
                emp_fullname = getEmployeeName(rs.getString("emp_id"));
                hourly_rate = getHourlyRate(getPosition(rs.getString("emp_id")));
                OT_rate = getOvertimeRate(getPosition(rs.getString("emp_id")));
                total_hour = rs.getInt("Total_hour");
                if (total_hour > 8) {
                    TotalPay = ((8 * hourly_rate) + ((total_hour - 8) * OT_rate));
                } else {
                    TotalPay = (total_hour * hourly_rate);
                }

                InsertEmpSalary(Date,emp_id,emp_fullname,TotalPay,total_hour);
            }

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
                    "DELETE FROM Position WHERE pos_name=\"" + pos_name + "\""
            );

            ResultSet rs = curs.executeQuery(
                    "SELECT id FROM employees WHERE pos_name =\"" + pos_name + "\""
            );

            deleteEmployee(rs.getString("id"));

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    public ArrayList<String> getListOfPositionName() {
        ArrayList<String> Positions = new ArrayList<String>();

        try {
            ResultSet rs = curs.executeQuery("SELECT pos_name FROM Position");

            while (rs.next()) {
                Positions.add(rs.getString("pos_name"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return Positions;
    }

    public Object[][] getPositions(){
        ArrayList<Object[]> Positions = new ArrayList<Object[]>();

        try {
            ResultSet rs = curs.executeQuery("SELECT * FROM Position");

            while (rs.next()) {

                Object[] temp = {
                        rs.getInt("id"),
                        rs.getString("pos_name"),
                        rs.getInt("hourly_rate"),
                        rs.getInt("overtime_rate")

                };

                Positions.add(temp);
            }
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return Positions.toArray(new Object[Positions.size()][]);
    }

    public void createEmployee(String Empid, String fn, String ln, String email, String position) {
        try {
            curs.executeUpdate("INSERT INTO employees VALUES(" +
                    "\"" + Empid + "\"," +
                    "\"" + fn + "\"," +
                    "\"" + ln + "\"," +
                    "\"" + email + "\"," +
                    "\"" + position + "\"" +
                    ")");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public Boolean existsEmployeeID(String Empid) {
        try {
            return curs.executeQuery(
                    "SELECT * FROM employees WHERE id=\"" + Empid + "\""
            ).next();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public ArrayList<String> getEmployeeID() {
        ArrayList<String> EmployeeID = new ArrayList<String>();

        try {
            ResultSet rs = curs.executeQuery("SELECT id FROM employees");

            while (rs.next()) {
                EmployeeID.add(rs.getString("id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return EmployeeID;
    }

    public void deleteEmployee(String Empid) {
        try {
            curs.executeUpdate(
                    "DELETE FROM employees WHERE id=\"" + Empid + "\""
            );
            curs.executeUpdate(
                    "DELETE FROM Attendance WHERE Emp_id=\"" + Empid + "\""
            );
            curs.executeUpdate(
                    "DELETE FROM emp_salary WHERE Emp_id=\"" + Empid + "\""
            );

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateEmployee(String Empid, String fn, String ln, String email, String position) {
        try {
            curs.executeUpdate(
                    "UPDATE employees SET " +
                            "first_name=\"" + fn + "\"," +
                            "last_name=\"" + ln + "\"," +
                            "email=\"" + email + "\"," +
                            "pos_name=\"" + position + "\" " +
                            "WHERE id=\"" + Empid + "\""
            );
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public String getEmployeeName(String empid ){
        String FullName;
        try {
            ResultSet rs = curs.executeQuery("SELECT  first_name , last_name  FROM employees WHERE id=\""
                    + empid + "\"");

            if(rs.next()){
                return ( FullName = ( rs.getString("first_name") + " " + rs.getString("last_name") ) ) ;
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }

        return "";
    }

    public Object[][] getEmployees(){

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
                        rs.getString("pos_name")
                };

                employees.add(temp);
            }
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }

        return employees.toArray(new Object[employees.size()][]);
    }

    public String getPosition(String EmpID) {
        try {
            ResultSet rs = curs.executeQuery("SELECT pos_name FROM employees WHERE id =\"" + EmpID + "\"");

            if (rs.next())
                return rs.getString("pos_name");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return "";
    }

    public int getHourlyRate(String pos_name) {
        try {
            ResultSet rs = curs.executeQuery("SELECT hourly_rate FROM Position WHERE pos_name =\"" + pos_name + "\"");

            if (rs.next())
                return rs.getInt("hourly_rate");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }

    public int getOvertimeRate(String pos_name) {
        try {
            ResultSet rs = curs.executeQuery("SELECT overtime_rate FROM Position WHERE pos_name =\"" + pos_name + "\"");

            if (rs.next())
                return rs.getInt("overtime_rate");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }

    public Object[][] getAttendance(String Date ){
        ArrayList<Object[]> Attendance = new ArrayList<Object[]>();
        ResultSet rs;

        try{
            rs = curs.executeQuery("SELECT * FROM Attendance WHERE attendance_date =\"" + Date + "\"");

            while(rs.next()){
                Object[] temp = {
                        rs.getInt("clock_in_id"),
                        rs.getString("emp_id"),
                        getEmployeeName(rs.getString("emp_id")),
                        rs.getString("attendance_date"),
                        rs.getString("clock_in_time"),
                        rs.getString("clock_out_time")
                };
                Attendance.add(temp);
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }

        return Attendance.toArray(new Object[Attendance.size()][]);

    }

    public Object[][] getAllAttendance(){
        ArrayList<Object[]> Attendance = new ArrayList<Object[]>();
        ResultSet rs;

        try{
            rs = curs.executeQuery("SELECT * FROM Attendance ");

            while(rs.next()){
                Object[] temp = {
                        rs.getInt("clock_in_id"),
                        rs.getString("emp_id"),
                        getEmployeeName(rs.getString("emp_id")),
                        rs.getString("attendance_date"),
                        rs.getString("clock_in_time"),
                        rs.getString("clock_out_time")
                };
                Attendance.add(temp);
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }

        return Attendance.toArray(new Object[Attendance.size()][]);

    }

    public Object[][] getAllMonthlySalary(){
        ArrayList<Object[]> Salary = new ArrayList<Object[]>();
        String EmID , EmFN ;
        int m ,  y , TP ,  TH;

        try{
            ResultSet rs = curs.executeQuery
                    (
                            "SELECT  emp_id, emp_name, ( SUM(salary_per_day) AS 'Total_salary') , ( SUM(total_time) AS 'Total_Hour') , ( EXTRACT(YEAR FROM date_salary) AS 'sal_year' ), " +
                                    "(EXTRACT(MONTH FROM data_salary) AS 'sal_month')  FROM emp_salary " +
                                    "GROUP BY ( emp_id , sal_year ,sal_month ) " +
                                    "ORDER BY ( sal_month, sal_year DESC )  "
                    );

            while(rs.next()){

                Object[] temp = {
                        rs.getString("emp_id"),
                        rs.getString("emp_name"),
                        rs.getInt("sal_month"),
                        rs.getInt("sal_year"),
                        rs.getInt("Total_salary"),
                        rs.getInt("Total_Hour")

                };

                Salary.add(temp);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return Salary.toArray(new Object[Salary.size()][]);

    }

    public Object[][] getSalaryByMonthAndYear(int month , int year){
        ArrayList<Object[]> Salary = new ArrayList<Object[]>();
        String EmID , EmFN ;
        int m ,  y , TP ,  TH;


        try{

            ResultSet rs = curs.executeQuery
                    (
                            "SELECT  emp_id, emp_name, ( SUM(salary_per_day) AS 'Total_salary') , ( SUM(total_time) AS 'Total_Hour') , ( EXTRACT(YEAR FROM date_salary) AS 'sal_year' ), " +
                                    "(EXTRACT(MONTH FROM data_salary) AS 'sal_month')  FROM emp_salary " +
                                    "WHERE ( ( sal_month = " + Integer.toString(month) + ")" + "AND " + "(sal_year = " + Integer.toString(year) + "))" +
                                    "GROUP BY ( emp_id , sal_year ,sal_month ) "
                    );

            while( rs.next()) {

                Object[] temp = {
                        rs.getString("emp_id"),
                        rs.getString("emp_name"),
                        rs.getInt("sal_month"),
                        rs.getInt("sal_year"),
                        rs.getInt("Total_salary"),
                        rs.getInt("Total_Hour")

                };

                Salary.add(temp);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return Salary.toArray(new Object[Salary.size()][]);

    }



}