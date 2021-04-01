package System;

import GUI.ApplicationWindow;
import com.mysql.cj.xdevapi.Result;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    private static DecimalFormat df2 = new DecimalFormat("0.00");

    public DatabaseConnection() {}

    // Executes SQL Query
    public static boolean executeStatement(final PreparedStatement statement) throws SQLException {
        try {
            assert statement.getConnection() != null;
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert statement.getConnection() != null;
            statement.getConnection().close(); }
        return false;
    }

    /*public static int getNextID(final String tableName) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("SELECT `AUTO_INCREMENT` " +
                    "FROM INFORMATION_SCHEMA.TABLES " +
                    "WHERE TABLE_SCHEMA = 'bapers_db' " +
                    "AND TABLE_NAME = '"+tableName+"'");
            ResultSet res = statement.executeQuery();
            res.next();
            return Integer.parseInt(res.getString( "AUTO_INCREMENT"));
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }*/

    public static boolean isCustomerValuedCustomer(final int ID) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("SELECT customerID FROM valuedCustomer WHERE " +
                    "customerID ="+ID);
            ResultSet res = statement.executeQuery();
            return res.next();
        } catch (SQLException exception) { exception.printStackTrace(); }
        return false;
    }

    public static int getNextID(final String tableName) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("SELECT MAX(ID) AS ID FROM "+tableName);
            ResultSet res = statement.executeQuery();
            res.next();
            return Integer.parseInt(res.getString("ID"));
        } catch (SQLException exception) {
            exception.printStackTrace();
            return -1;
        }
    }

    private static List<String[]> returnList(final PreparedStatement statement) throws SQLException {
        ResultSet res = statement.executeQuery();
        List<String[]> data = new ArrayList<>();
        int nCol = res.getMetaData().getColumnCount();
        while (res.next()) {
            String[] row = new String[nCol];
            for (int i=1; i<=nCol; i++) {
                Object object = res.getObject(i);
                row[i-1] = (object == null) ? null : object.toString();
            }
            data.add(row);
        }
        return data;
    }

    private static String[] returnRow(final PreparedStatement statement) throws SQLException {
        ResultSet res = statement.executeQuery();
        int nCol = res.getMetaData().getColumnCount();
        String[] row = new String[nCol];
        if (res.next()) {
            for (int i=1; i<=nCol; i++) {
                Object object = res.getObject(i);
                row[i-1] = (object == null) ? null : object.toString();
            }
        }
        return row;
    }

    public static String getCompanyName(int ID) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("SELECT companyName FROM customer " +
                    "WHERE ID = " +ID);
            ResultSet res = statement.executeQuery();
            if (res.next()){
                return res.getString("companyName");
            }
        } catch (SQLException exception) { exception.printStackTrace(); }
        return null;
    }

    public static int getTechnician(String department) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement;
            switch (department) {
                case "Copy room":
                    statement = conn.prepareStatement("SELECT ID FROM staff WHERE role LIKE '%copy%'");
                    break;
                case "Development area":
                    statement = conn.prepareStatement("SELECT ID FROM staff WHERE role LIKE '%development%'");
                    break;
                case "Finishing room":
                    statement = conn.prepareStatement("SELECT ID FROM staff WHERE role LIKE '%finishing%'");
                    break;
                case "Packing":
                    statement = conn.prepareStatement("SELECT ID FROM staff WHERE role LIKE '%packing%'");
                    break;
                default:
                    statement = conn.prepareStatement("SELECT ID FROM staff WHERE role LIKE 'Technician%'");
                    break;
            }
            ResultSet res = statement.executeQuery();
            res.next();
            return Integer.parseInt(res.getString("ID"));
        } catch (SQLException exception) {
            exception.printStackTrace();
            return -1;
        }
    }

    public static int getTaskIDFromJobID(final int jobID, final int availableTaskID) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("SELECT ID FROM task WHERE jobID = "+jobID+" AND " +
                    "availableTaskID = "+availableTaskID);
            ResultSet res = statement.executeQuery();
            res.next();
            return Integer.parseInt(res.getString("ID"));
        } catch (SQLException exception) {
            exception.printStackTrace();
            return -1;
        }
    }

    public static String getCustomerName(int ID) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("SELECT title, firstName, lastName FROM customer " +
                    "WHERE ID = " +ID);
            ResultSet res = statement.executeQuery();
            if (res.next()){
                return res.getString("title") + " " + res.getString("firstName") + " " + res.getString("lastName");
            }
        } catch (SQLException exception) { exception.printStackTrace(); }
        return null;
    }

    public static List<String[]> getAvailableTasks() {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("SELECT ID, description FROM availableTask");
            return returnList(statement);
        } catch (SQLException exception) { exception.printStackTrace(); }
        return null;
    }

    public static String getStaffName(int ID){
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("SELECT firstName, lastName, ID FROM staff " +
                    "WHERE ID = " +ID);
            ResultSet res = statement.executeQuery();
            if (res.next()){
                return res.getString("firstName")+ " " + res.getString("lastName");
            }
        } catch (SQLException exception) { exception.printStackTrace(); }
        return null;
    }


    public static List<String[]> getTechnicians() {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("SELECT firstName, lastName, ID FROM staff " +
                    "WHERE role LIKE '%Technician%'");
            return returnList(statement);
        } catch (SQLException exception) { exception.printStackTrace(); }
        return null;
    }

    public static boolean checkForCompletedTasks(int jobID) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("SELECT ID FROM task WHERE " +
                    "jobID ="+jobID+" AND isCompleted = 1");
            ResultSet res = statement.executeQuery();
            return res.next();
        } catch (SQLException exception) { exception.printStackTrace(); }
        return false;
    }

    public static List<String[]> getTasksFromJobID(int jobID) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM task " +
                    "WHERE jobID = "+jobID);
            return returnList(statement);
        } catch (SQLException exception) { exception.printStackTrace(); }
        return null;
    }

    public static List<String[]> getTaskFromJobID(int jobID) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM task " +
                    "WHERE jobID = '"+jobID+"' ORDER BY department");
            return returnList(statement);
        } catch (SQLException exception) { exception.printStackTrace(); }
        return null;
    }

    public static List<String[]> jobReportWithID(final String fromDate, final String toDate, final int ID) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM job WHERE customerID = "+ID+" AND " +
                            "((startDate >= '"+fromDate+"' " +
                            "AND endDate <= '"+toDate+"') " +
                            "OR (startDate >= '"+fromDate+"' AND endDate <= '"+toDate+"')) ");
            return returnList(statement);
        } catch (SQLException exception) { exception.printStackTrace(); }
        return null;
    }

    public static List<String[]> getTaskFromDates(final String fromDate, final String toDate, final String reportType) {
        try {
            Connection conn = Connect();
            assert conn != null;

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM task WHERE " +
                            "(startDate >= '"+fromDate+"' " +
                            "AND endDate <= '"+toDate+"') " +
                            "OR (startDate >= '"+fromDate+"' AND endDate <= '"+toDate+"') " +
                            "ORDER BY task.date");


            return returnList(statement);
        } catch (SQLException exception) { exception.printStackTrace(); }
        return null;
    }

    public static List<String[]> getTaskForPerformance(final String fromDate, final String toDate, final String reportType) {
        try {
            Connection conn = Connect();
            assert conn != null;

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM task WHERE " +
                    "(task.date >= '"+fromDate+"' " +
                    "AND task.date <= '"+toDate+"') " +
                    "OR (task.date >= '"+fromDate+"' AND task.date <= '"+toDate+"') " +
                    "ORDER BY staffID, availableTaskID");


            return returnList(statement);
        } catch (SQLException exception) { exception.printStackTrace(); }
        return null;
    }

    public static List<String[]> getTaskForShift(final String fromDate, final String time , final String toDate, final String time2,final String reportType) {
        try {
            Connection conn = Connect();
            assert conn != null;

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM task WHERE " +
                    "(task.date >= '"+(fromDate+time)+"' " +
                    "AND task.date <= '"+(toDate+time2)+"') " +
                    "OR (task.date >= '"+(fromDate+time)+"' AND task.date <= '"+(toDate+time2)+"') " +
                    "ORDER BY task.date");


            return returnList(statement);
        } catch (SQLException exception) { exception.printStackTrace(); }
        return null;
    }

    public static List<String[]> getJobFromDates(final String fromDate, final String toDate, final String reportType) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement;
            switch (reportType) {
                case "Customer Sales Report":
                    statement = conn.prepareStatement("SELECT * FROM job WHERE " +
                            "(startDate >= '"+fromDate+"' " +
                            "AND endDate <= '"+toDate+"') " +
                            "OR (startDate >= '"+fromDate+"' AND endDate <= '"+toDate+"') " +
                            "ORDER BY customerID");

                    break;
                default:
                    statement = conn.prepareStatement("SELECT * FROM job WHERE " +
                            "(startDate >= '"+fromDate+"' " +
                            "AND endDate <= '"+toDate+"') " +
                            "OR (startDate >= '"+fromDate+"' AND endDate <= '"+toDate+"')");
                    break;
            }
            return returnList(statement);
        } catch (SQLException exception) { exception.printStackTrace(); }
        return null;
    }

    public static List<String[]> searchJobs(final String fromDate, final String toDate) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("SELECT ID, startDate, customerID, price FROM job " +
                    "WHERE (startDate BETWEEN '"+fromDate+"' AND '"+toDate+"') AND (status = 'Created')"
            );
            return returnList(statement);
        } catch (SQLException exception) { exception.printStackTrace(); }
        return null;
    }


    public static List<String[]> searchCustomer(final String firstName, final String lastName) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement;
            if (firstName.isEmpty() && lastName.isEmpty()) return null;
            else if (firstName.isEmpty())
                statement = conn.prepareStatement("SELECT ID, title, firstName, lastName, email  FROM customer WHERE lastName LIKE '"+lastName+"%'");
            else if (lastName.isEmpty())
                statement = conn.prepareStatement("SELECT ID, title, firstName, lastName, email FROM customer WHERE firstName LIKE '"+firstName+"%'");
            else
                statement = conn.prepareStatement("SELECT ID, title, firstName, lastName, email FROM customer WHERE firstName LIKE '" +
                    firstName+"%' OR lastName LIKE '"+lastName+"%'");
            return returnList(statement);
        } catch (SQLException exception) { exception.printStackTrace(); }
        return null;
    }

    public static List<String[]> searchPayments(final String firstName, final String lastName) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement;
            if (firstName.isEmpty() && lastName.isEmpty()) return null;
            else if (firstName.isEmpty())
                statement = conn.prepareStatement("SELECT customer.firstName, customer.lastName, jobID, amountdue, discount FROM payment " +
                        "INNER JOIN job ON payment.jobID = job.ID INNER JOIN customer ON job.ID = customer.ID  WHERE customer.lastName LIKE '"+lastName+"%'");
            else if (lastName.isEmpty())
                statement = conn.prepareStatement("SELECT customer.firstName, customer.lastName, jobID, amountdue, discount FROM payment " +
                        "INNER JOIN job ON payment.jobID = job.ID INNER JOIN customer ON job.ID = customer.ID WHERE customer.firstName LIKE '"+firstName+"%'");
            else
                statement = conn.prepareStatement("SELECT customer.firstName, customer.lastName, jobID, amountdue, discount FROM payment INNER JOIN job " +
                        "ON payment.jobID = job.ID INNER JOIN customer ON job.ID = customer.ID   WHERE customer.firstName LIKE '" +
                        firstName+"%' OR customer.lastName LIKE '"+lastName+"%'");
            return returnList(statement);
        } catch (SQLException exception) { exception.printStackTrace(); }
        return null;
    }




    public static String[] getRowBySingleID(final String tableName, final int ID) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement;
            if (tableName.equals("valuedCustomer")) {
                statement = conn.prepareStatement("SELECT * FROM "+tableName+" WHERE customerID = "+ID);
            } else
                statement = conn.prepareStatement("SELECT * FROM "+tableName+" WHERE ID = "+ID);
            return returnRow(statement);
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public static List<String[]> getData(final String tableName) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM "+tableName);
            return returnList(statement);
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    // Removing an existing job_jobReport record
    public static boolean
        removeJobJobReport(final int reportID, final int jobID, final int taskID, final int staffID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM staff_performanceReport WHERE reportID = "+reportID+" AND jobID"+jobID+" AND " +
                        "taskID = "+taskID+" AND staffID = "+staffID
        );
        return executeStatement(statement);
    }

    // Inserting a new job_jobReport record
    public static boolean
        addJobJobReport(final int reportID, final int jobID, final int taskID, final int staffID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO job_jobReport SELECT * FROM (SELECT "+reportID+", "+jobID+", " +
                        ""+taskID+", "+staffID+") AS tmp WHERE NOT EXISTS (SELECT * FROM job_jobReport " +
                        "WHERE reportID = "+reportID+" AND jobID = "+jobID+" AND taskID = "+taskID+" AND " +
                        "staffID = "+staffID+") LIMIT 1"
        );
        return executeStatement(statement);
    }

    // Removing an existing task_summaryReport record
    public static boolean
        removeTaskSummaryReport(final int reportID, final int taskID, final int jobID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM staff_performanceReport WHERE reportID = "+reportID+" AND taskID"+taskID+" " +
                        "AND jobID = "+jobID
        );
        return executeStatement(statement);
    }

    // Inserting a new task_summaryReport record
    public static boolean
        addTaskSummaryReport(final int reportID, final int taskID, final int jobID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO task_summaryReport SELECT * FROM (SELECT "+reportID+", "+taskID+", "+jobID+") AS " +
                        "tmp WHERE NOT EXISTS (SELECT * FROM task_summaryReport WHERE reportID = "+reportID+" AND " +
                        "taskID = "+taskID+" AND jobID = "+jobID+") LIMIT 1"
        );
        return executeStatement(statement);
    }

    // Removing an existing staff_performanceReport record
    public static boolean
        removeStaffPerformanceReport(final int reportID, final int staffID, final int taskID, final int jobID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM staff_performanceReport WHERE reportID = "+reportID+" AND staffID = "+staffID+" AND " +
                        "taskID"+taskID+" AND jobID = "+jobID
        );
        return executeStatement(statement);
    }

    // Inserting a new staff_performanceReport record
    public static boolean
        addStaffPerformanceReport(final int reportID, final int staffID, final int taskID, final int jobID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO staff_performanceReport SELECT * FROM (SELECT "+reportID+", "+staffID+", " +
                        ""+taskID+", "+jobID+") AS tmp WHERE NOT EXISTS (SELECT * FROM staff_performanceReport " +
                        "WHERE reportID = "+reportID+" AND staffID = "+staffID+" AND taskID = "+taskID+" AND " +
                        "jobID = "+jobID+") LIMIT 1"
        );
        return executeStatement(statement);
    }

    // Editing an existing jobReport record
    public static boolean editJobReport(final int reportID, final int numberOfJobs) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "UPDATE jobReport SET numberOfJobs = '"+numberOfJobs+"' WHERE reportID = "+reportID
        );
        return executeStatement(statement);
    }

    // Removing an existing jobReport record
    public static boolean removeJobReport(final int reportID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM jobReport WHERE reportID = "+reportID
        );
        return executeStatement(statement);
    }

    // Inserting a new jobReport record
    public static boolean
        addJobReport(final int reportID, final int numberOfJobs) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO jobReport SELECT * FROM (SELECT '"+reportID+"', '"+numberOfJobs+"') AS tmp " +
                        "WHERE NOT EXISTS (SELECT * FROM jobReport WHERE reportID = '"+reportID+"' AND " +
                        "numberOfJobs = '"+numberOfJobs+"') LIMIT 1"
        );
        return executeStatement(statement);
    }

    // Editing an existing summaryReport record
    public static boolean editSummaryReport(final int reportID, final int numberOfTasks) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "UPDATE summaryReport SET numberOfTasks = '"+numberOfTasks+"' WHERE reportID = "+reportID
        );
        return executeStatement(statement);
    }

    // Removing an existing summaryReport record
    public static boolean removeSummaryReport(final int reportID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM summaryReport WHERE reportID = "+reportID
        );
        return executeStatement(statement);
    }

    // Inserting a new summaryReport record
    public static boolean
        addSummaryReport(final int reportID, final int numberOfTasks) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO summaryReport SELECT * FROM (SELECT '"+reportID+"', '"+numberOfTasks+"') AS tmp " +
                        "WHERE NOT EXISTS (SELECT * FROM summaryReport WHERE reportID = '"+reportID+"' AND " +
                        "numberOfTasks = '"+numberOfTasks+"') LIMIT 1"
        );
        return executeStatement(statement);
    }

    // Editing an existing performanceReport record
    public static boolean editPerformanceReport(final int reportID, final int numberOfStaff) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "UPDATE performanceReport SET numberOfStaff = '"+numberOfStaff+"' WHERE reportID = "+reportID
        );
        return executeStatement(statement);
    }

    // Removing an existing performanceReport record
    public static boolean removePerformanceReport(final int reportID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM performanceReport WHERE reportID = "+reportID
        );
        return executeStatement(statement);
    }

    // Inserting a new performanceReport record
    public static boolean
        addPerformanceReport(final int reportID, final int numberOfStaff) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO performanceReport SELECT * FROM (SELECT '"+reportID+"', '"+numberOfStaff+"') AS tmp " +
                        "WHERE NOT EXISTS (SELECT * FROM performanceReport WHERE reportID = '"+reportID+"' AND " +
                        "numberOfStaff = '"+numberOfStaff+"') LIMIT 1"
        );
        return executeStatement(statement);
    }

    // Editing an existing report record
    public static boolean
        editReport(final int ID, final String reportType, final String content, final LocalDate startDate,
                   final LocalDate endDate) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "UPDATE report SET reportType = '"+reportType+"', content = '"+content+"', startDate = '"+startDate+"', " +
                        "endDate = '"+endDate+"' WHERE ID = "+ID
        );
        return executeStatement(statement);
    }

    // Removing an existing report record
    public static boolean removeReport(final int ID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM report WHERE ID = "+ID
        );
        return executeStatement(statement);
    }

    // Inserting a new report record
    public static boolean
        addReport(final String reportType, final String content, final LocalDate startDate, final LocalDate endDate) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO report (reportType, content, startDate, endDate) SELECT * FROM (SELECT '"+reportType+"', " +
                        "'"+content+"', '"+startDate+"', '"+endDate+"') AS tmp WHERE NOT EXISTS (SELECT reportType, " +
                        "content, startDate, endDate FROM report WHERE reportType = '"+reportType+"' AND content = '" +
                        ""+content+"' AND startDate = '"+startDate+"' AND endDate = '"+endDate+"') LIMIT 1"
        );
        return executeStatement(statement);
    }

    // Editing an existing cash record
    public static boolean
        editCash(final int jobID, final double cashPaid, final double changeGiven) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "UPDATE cash SET cashPaid = '"+cashPaid+"', changeGiven = '"+changeGiven+"' WHERE jobID = "+jobID
        );
        return executeStatement(statement);
    }

    // Removing an existing cash record
    public static boolean removeCash(final int jobID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM cash WHERE jobID = "+jobID
        );
        return executeStatement(statement);
    }

    // Inserting a new cash record
    public static boolean
        addCash(final int jobID, final double cashPaid, final double changeGiven) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO cash (jobID, cashPaid, changeGiven) SELECT * FROM (SELECT '"+jobID+"', '"+cashPaid+"', " +
                        "'"+changeGiven+"') AS tmp WHERE NOT EXISTS (SELECT jobID, cashPaid, changeGiven FROM cash WHERE " +
                        "jobID = '"+jobID+"' AND cashPaid = '"+cashPaid+"' AND changeGiven = '"+changeGiven+"') LIMIT 1"
        );
        return executeStatement(statement);
    }

    // Editing an existing card record
    public static boolean
        editCard(final int jobID, final String cardType, final String expiryDate, final int lastFourDigits) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "UPDATE card SET cardType = '"+cardType+"', expiryDate = '"+expiryDate+"', lastFourDigits = '" +
                        ""+lastFourDigits+"' WHERE jobID = "+jobID
        );
        return executeStatement(statement);
    }

    // Removing an existing card record
    public static boolean removeCard(final int jobID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM card WHERE jobID = "+jobID
        );
        return executeStatement(statement);
    }

    // Inserting a new card record
    public static boolean
        addCard(final int jobID, final String cardType, final String expiryDate, final int lastFourDigits) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO card (jobID, cardType, expiryDate, lastFourDigits) SELECT * FROM (SELECT '"+jobID+"', " +
                        "'"+cardType+"', '"+expiryDate+"', "+lastFourDigits+") AS tmp WHERE NOT EXISTS (SELECT jobID, " +
                        "cardType, expiryDate, lastFourDigits FROM card WHERE jobID = '"+jobID+"' AND cardType = " +
                        "'"+cardType+"' AND expiryDate = '"+expiryDate+"' AND lastFourDigits = "+lastFourDigits+") LIMIT 1"
        );
        return executeStatement(statement);
    }

    // Editing an existing payment record
    public static boolean
        editPayment(final int jobID, final double amountDue, final int isPaid, final double discount,
                    final String paymentType, final int customerID, final int staffID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "UPDATE payment SET amountDue = '"+amountDue+"', isPaid = '"+isPaid+"', discount = '"+discount+"', " +
                        "paymentType = '"+paymentType+"', customerID = '"+customerID+"', staffID = '"+staffID+"' " +
                        "WHERE jobID = "+jobID
        );
        return executeStatement(statement);
    }

    // Removing an existing payment record
    public static boolean removePayment(final int jobID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM payment WHERE jobID = "+jobID
        );
        return executeStatement(statement);
    }

    // Inserting a new payment record
    public static boolean
        addPayment(final int jobID, final double amountDue, final int isPaid, final double discount,
                   final String paymentType, final int customerID, final int staffID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO payment (jobID, amountDue, isPaid, discount, paymentType, customerID, staffID) SELECT * " +
                        "FROM (SELECT '"+jobID+"', '"+amountDue+"', '"+isPaid+"', '"+discount+"', '"+paymentType+"', " +
                        "'"+customerID+"', '"+staffID+"') AS tmp WHERE NOT EXISTS (SELECT jobID, amountDue, isPaid, " +
                        "discount, paymentType, customerID, staffID FROM payment WHERE jobID = '"+jobID+"' AND " +
                        "amountDue = '"+amountDue+"' AND isPaid = '"+isPaid+"' AND discount = '"+discount+"' AND " +
                        "paymentType = '"+paymentType+"' AND customerID = '"+customerID+"' AND staffID = '"+staffID+"') LIMIT 1"
        );
        return executeStatement(statement);
    }

    // Editing an existing available task record
    public static boolean
        editAvailableTask(final int ID, final String description, final String department, final String timeTaken, final double price) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "UPDATE availableTask SET description = '"+description+"', department = '"+department+"', timeTaken = '" +
                        ""+timeTaken+"', price = '"+price+"' WHERE ID = "+ID
        );
        return executeStatement(statement);
    }

    // Removing an existing available task record
    public static boolean removeAvailableTask(final int ID) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("UPDATE task SET availableTaskID = NULL " +
                    "WHERE availableTaskID = "+ID);
            statement.executeUpdate();
            statement = conn.prepareStatement(
                    "DELETE FROM availableTask WHERE ID = "+ID
            );
            return executeStatement(statement);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    // Inserting a new available task record
    public static boolean
        addAvailableTask(final String description, final String department, final String timeTaken, final double price) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO availableTask (description, department, timeTaken, price) SELECT * FROM (SELECT '"+description+"', " +
                        "'"+department+"', '"+timeTaken+"', '"+price+"') AS tmp WHERE NOT EXISTS (SELECT description, department, " +
                        "timeTaken, price FROM availableTask WHERE description = '"+description+"' AND department = '"+department+"' " +
                        "AND timeTaken = '"+timeTaken+"' AND price = '"+price+"') LIMIT 1"
        );
        return executeStatement(statement);
    }

    // Editing an existing task record
    public static boolean
        editTask(final int ID, final int availableTaskID, final int jobID, final String description, final String department,
                 final String timeTaken, final double price, final int staffID, final int isCompleted) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "UPDATE task SET availableTaskID = '"+availableTaskID+"', description = '"+description+"', department = '"+department+"', timeTaken = '" +
                        ""+timeTaken+"', price = '"+price+"', staffID = '"+staffID+"', isCompleted = '"+isCompleted+"' WHERE ID = "+ID
        );
        return executeStatement(statement);
    }

    // Removing several existing task records
    public static boolean removeAllTasksWithJobID(final int jobID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM task WHERE jobID = "+jobID);
        return executeStatement(statement);
    }

    // Removing several existing task records
    public static boolean removeTasks(final int jobID, final int availableTaskID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM task WHERE jobID = "+jobID+" AND availableTaskID = "+availableTaskID);
        return executeStatement(statement);
    }

    // Removing an existing task record
    public static boolean removeTask(final int ID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM task WHERE ID = "+ID
        );
        return executeStatement(statement);
    }

    // Inserting a new task record
    public static boolean
        addTask(final int availableTaskID, final int jobID, final String description, final String department, final String timeTaken,
                final double price, final int staffID, final int isCompleted) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        /*PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO task (availableTaskID, jobID, description, department, timeTaken, price, staffID, isCompleted) SELECT * " +
                        "FROM (SELECT '"+availableTaskID+"', '"+jobID+"', '"+description+"', '"+department+"', '"+timeTaken+"', " +
                        "'"+price+"', '"+staffID+"', '"+isCompleted+"') AS tmp WHERE NOT EXISTS (SELECT availableTaskID, jobID, " +
                        "description, department, timeTaken, price, staffID, isCompleted FROM task WHERE availableTaskID = '"+availableTaskID+"' AND " +
                        "jobID = '"+jobID+"' AND description = '"+description+"' AND department = '"+department+"' AND " +
                        "timeTaken = '"+timeTaken+"' AND price = '"+price+"' AND staffID = '"+staffID+"' AND isCompleted = '"+isCompleted+"') LIMIT 1"
        );*/
        PreparedStatement statement = conn.prepareStatement(
                "INSERT IGNORE INTO task (availableTaskID, jobID, description, department, timeTaken, price, " +
                        "staffID, isCompleted) VALUES ('"+availableTaskID+"', '"+jobID+"', '"+description+"', '"+department+"', " +
                        "'"+timeTaken+"', '"+price+"', '"+staffID+"', '"+isCompleted+"')"
        );
        return executeStatement(statement);
    }

    // Editing an existing job record
    public static boolean
        editJob(final int ID, final int isUrgent, final double price, final LocalDateTime deadline, final String status) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "UPDATE job SET isUrgent = '"+isUrgent+"', price = '"+price+"', deadline = '"+deadline+"', status = " +
                        "'"+status+"' WHERE ID = "+ID
        );
        return executeStatement(statement);
    }

    // Removing an existing job record
    public static boolean removeJob(final int ID) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("DELETE FROM job_jobReport WHERE jobID = "+ID);
            statement.executeUpdate();
            statement = conn.prepareStatement("DELETE FROM staff_performanceReport WHERE jobID = "+ID);
            statement.executeUpdate();
            statement = conn.prepareStatement("DELETE FROM task WHERE jobID = "+ID);
            statement.executeUpdate();
            statement = conn.prepareStatement("DELETE FROM task_summaryReport WHERE jobID = "+ID);
            statement.executeUpdate();
            statement = conn.prepareStatement(
                    "DELETE FROM job WHERE ID = "+ID
            ); return executeStatement(statement);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    // Inserting a new job record
    public static boolean
        addJob(final int isUrgent, final double price, final LocalDate startDate, final LocalDateTime deadline,
               final String status, final int customerID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        /*PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO job (isUrgent, price, startDate, deadline, status, customerID) SELECT * " +
                        "FROM (SELECT '"+isUrgent+"', '"+price+"', '"+startDate+"', '"+deadline+"', " +
                        "'"+status+"', '"+customerID+"') AS tmp WHERE NOT EXISTS (SELECT isUrgent, price, startDate, " +
                        "deadline, status, customerID FROM job WHERE isUrgent = '"+isUrgent+"' AND " +
                        "price = '"+price+"' AND startDate = '"+startDate+"' AND deadline = '"+deadline+"' AND " +
                        "status = '"+status+"' AND customerID = '"+customerID+"') LIMIT 1"
        );*/
        PreparedStatement statement = conn.prepareStatement(
                "INSERT IGNORE INTO job (isUrgent, price, startDate, deadline, status, customerID) " +
                        "VALUES ('"+isUrgent+"', '"+price+"', '"+startDate+"', '"+deadline+"', '"+status+"', " +
                        "'"+customerID+"')");
        return executeStatement(statement);
    }

    // Editing an existing staff record
    public static boolean
        editStaff(final int ID, final String title, final String firstName, final String lastName, final String contactNumber,
                  final String address, final String email, final String username, final String password, final String role) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "UPDATE staff SET title = '"+title+"', firstName = '"+firstName+"', lastName = '"+lastName+"', contactNumber = '" +
                        ""+contactNumber+"', address = '"+address+"', email = '"+email+"', username = '"+username+"', password = " +
                        "'"+password+"', role = '"+role+"' WHERE ID = "+ID);
        return executeStatement(statement);
    }

    // Removing an existing staff record
    public static boolean removeStaff(final int ID) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("UPDATE job_jobReport SET staffID = NULL " +
                    "WHERE staffID = "+ID);
            statement.executeUpdate();
            statement = conn.prepareStatement("UPDATE payment SET staffID = NULL " +
                    "WHERE staffID = "+ID);
            statement.executeUpdate();
            statement = conn.prepareStatement("UPDATE staff_performanceReport SET staffID = NULL " +
                    "WHERE staffID = "+ID);
            statement.executeUpdate();
            statement = conn.prepareStatement("UPDATE task SET staffID = NULL " +
                    "WHERE staffID = "+ID);
            statement.executeUpdate();
            statement = conn.prepareStatement(
                    "DELETE FROM staff WHERE ID = "+ID
            ); return executeStatement(statement);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    // Inserting a new staff record
    public static boolean
        addStaff(final String title, final String firstName, final String lastName, final String contactNumber, final String address,
                 final String email, final String username, final String password, final String role) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "INSERT IGNORE INTO staff (title, firstName, lastName, contactNumber, address, email, username, password, role) " +
                        "VALUES ('"+title+"', '"+firstName+"', '"+lastName+"', '"+contactNumber+"', '"+address+"', '"+email+"', " +
                        "'"+username+"', '"+password+"', '"+role+"')"
        );
        return executeStatement(statement);
    }

    // Editing an existing valuedCustomer record
    public static boolean
    editValuedCustomer(final int customerID, final String agreedDiscount, final String discountRate) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "UPDATE valuedCustomer SET agreedDiscount = '"+agreedDiscount+"', discountRate = '"+discountRate+"' " +
                        "WHERE customerID = "+customerID
        );
        return executeStatement(statement);
    }

    // Removing an existing valuedCustomer record
    public static boolean removeValuedCustomer(final int customerID) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM valuedCustomer WHERE customerID = "+customerID
        );
        return executeStatement(statement);
    }

    // Inserting a new valuedCustomer record
    public static boolean
        addValuedCustomer(final int customerID, final String agreedDiscount, final String discountRate) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "INSERT IGNORE INTO valuedCustomer VALUES (" +
                        ""+customerID+", '"+agreedDiscount+"', '"+discountRate+"')"
        );
        return executeStatement(statement);
    }

    // Editing an existing customer record
    public static boolean
        editCustomer(final int ID, final String companyName, final String title, final String firstName, final String lastName,
                     final String contactNumber, final String address, final String email) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "UPDATE customer SET companyName = '"+companyName+"',firstName = '"+firstName+"', lastName = '"+lastName+"', " +
                        "contactNumber = '"+contactNumber+"', address = '"+address+"', email = '"+email+"' WHERE ID = "+ID
        );
        return executeStatement(statement);
    }

    // Removing an existing customer record
    public static boolean removeCustomer(final int ID) {
        try {
            Connection conn = Connect();
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("DELETE FROM valuedCustomer WHERE " +
                    "customerID = "+ID);
            statement.executeUpdate();
            statement = conn.prepareStatement("UPDATE job SET customerID = NULL " +
                    "WHERE customerID = "+ID);
            statement.executeUpdate();
            statement = conn.prepareStatement("UPDATE payment SET customerID = NULL " +
                    "WHERE customerID = "+ID);
            statement.executeUpdate();
            statement = conn.prepareStatement(
                    "DELETE FROM customer WHERE ID = "+ID
            ); return executeStatement(statement);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    // Inserting a new customer record
    public static boolean
        addCustomer(final String companyName, final String title, final String firstName, final String lastName, final String contactNumber,
                    final String address, final String email) throws SQLException {
        Connection conn = Connect();
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(
                "INSERT IGNORE INTO customer (companyName, title, firstName, lastName, contactNumber, address, email) VALUES (" +
                        "'"+companyName+"', '"+title+"', '"+firstName+"', '"+lastName+"', '"+contactNumber+"', '"+address+"', '"+email+"')"
        );
        return executeStatement(statement);
    }

    // Converts string into hex value
    public static String md5(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : digest)
            sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        return sb.toString();
    }

    // Verifies login credentials
    public static boolean VerifyLogInCredentials(String username, char[] password) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT username, role FROM staff WHERE username = '"+username+"' AND password = '"+md5(new String(password))+"'",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            ResultSet res = statement.executeQuery();
            int count = 0;
            while (res.next())
                count++;
            res.previous();
            if (count == 1 && res.getString("username").equals(username)) {
                ApplicationWindow.username = res.getString("username");
                ApplicationWindow.role = res.getString("role");
                return true;
            }
        } catch (NoSuchAlgorithmException | SQLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            conn.close(); }
        return false;
    }

    // Gets connection to the database
    public static Connection Connect() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://34.105.223.156:3306/bapers_db";
            String username = "root";
            String password = "cityproject5";
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
}