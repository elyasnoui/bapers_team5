package System;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDate;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DatabaseConnection {

    public DatabaseConnection() {}

    // TODO: Reminder -> Add method to reduce code repetition

    // Inserting a new job_jobReport - working
    public static boolean
        addJobJobReport(final int reportID, final int jobID, final int taskID, final int staffID) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO job_jobReport SELECT * FROM (SELECT "+reportID+", "+jobID+", " +
                            ""+taskID+", "+staffID+") AS tmp WHERE NOT EXISTS (SELECT * FROM job_jobReport " +
                            "WHERE reportID = "+reportID+" AND jobID = "+jobID+" AND taskID = "+taskID+" AND " +
                            "staffID = "+staffID+") LIMIT 1"
            );
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            conn.close(); }
        return false;
    }

    // Inserting a new task_summaryReport - working
    public static boolean
        addTaskSummaryReport(final int reportID, final int taskID, final int jobID) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO task_summaryReport SELECT * FROM (SELECT "+reportID+", "+taskID+", "+jobID+") AS " +
                            "tmp WHERE NOT EXISTS (SELECT * FROM task_summaryReport WHERE reportID = "+reportID+" AND " +
                            "taskID = "+taskID+" AND jobID = "+jobID+") LIMIT 1"
            );
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            conn.close(); }
        return false;
    }

    // Inserting a new staff_performanceReport - working
    public static boolean
        addStaffPerformanceReport(final int reportID, final int staffID, final int taskID, final int jobID) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO staff_performanceReport SELECT * FROM (SELECT "+reportID+", "+staffID+", " +
                            ""+taskID+", "+jobID+") AS tmp WHERE NOT EXISTS (SELECT * FROM staff_performanceReport " +
                            "WHERE reportID = "+reportID+" AND staffID = "+staffID+" AND taskID = "+taskID+" AND " +
                            "jobID = "+jobID+") LIMIT 1"
            );
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            conn.close(); }
        return false;
    }

    // Inserting a new job report - working
    public static boolean
        addJobReport(final int reportID, final int numberOfJobs) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO jobReport SELECT * FROM (SELECT '"+reportID+"', '"+numberOfJobs+"') AS tmp " +
                            "WHERE NOT EXISTS (SELECT * FROM jobReport WHERE reportID = '"+reportID+"' AND " +
                            "numberOfJobs = '"+numberOfJobs+"') LIMIT 1"
            );
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            conn.close(); }
        return false;
    }

    // Inserting a new summary report - working
    public static boolean
        addSummaryReport(final int reportID, final int numberOfTasks) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO summaryReport SELECT * FROM (SELECT '"+reportID+"', '"+numberOfTasks+"') AS tmp " +
                            "WHERE NOT EXISTS (SELECT * FROM summaryReport WHERE reportID = '"+reportID+"' AND " +
                            "numberOfTasks = '"+numberOfTasks+"') LIMIT 1"
            );
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            conn.close(); }
        return false;
    }

    // Inserting a new performance report - working
    public static boolean
        addPerformanceReport(final int reportID, final int numberOfStaff) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO performanceReport SELECT * FROM (SELECT '"+reportID+"', '"+numberOfStaff+"') AS tmp " +
                            "WHERE NOT EXISTS (SELECT * FROM performanceReport WHERE reportID = '"+reportID+"' AND " +
                            "numberOfStaff = '"+numberOfStaff+"') LIMIT 1"
            );
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            conn.close(); }
        return false;
    }

    // Inserting a new report - working
    public static boolean
        addReport(final String reportType, final String content, final LocalDate startDate, final LocalDate endDate) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO report (reportType, content, startDate, endDate) SELECT * FROM (SELECT '"+reportType+"', " +
                            "'"+content+"', '"+startDate+"', '"+endDate+"') AS tmp WHERE NOT EXISTS (SELECT reportType, " +
                            "content, startDate, endDate FROM report WHERE reportType = '"+reportType+"' AND content = '" +
                            ""+content+"' AND startDate = '"+startDate+"' AND endDate = '"+endDate+"') LIMIT 1"
            );
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            conn.close(); }
        return false;
    }

    // Inserting a new cash payment - working
    public static boolean
        addCash(final int jobID, final double cashPaid, final double changeGiven) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO cash (jobID, cashPaid, changeGiven) SELECT * FROM (SELECT '"+jobID+"', '"+cashPaid+"', " +
                            "'"+changeGiven+"') AS tmp WHERE NOT EXISTS (SELECT jobID, cashPaid, changeGiven FROM cash WHERE " +
                            "jobID = '"+jobID+"' AND cashPaid = '"+cashPaid+"' AND changeGiven = '"+changeGiven+"') LIMIT 1"
            );
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            conn.close(); }
        return false;
    }

    // Inserting a new card payment - working
    public static boolean
        addCard(final int jobID, final String cardType, final String expiryDate, final int lastFourDigits) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO card (jobID, cardType, expiryDate, lastFourDigits) SELECT * FROM (SELECT '"+jobID+"', " +
                            "'"+cardType+"', '"+expiryDate+"', "+lastFourDigits+") AS tmp WHERE NOT EXISTS (SELECT jobID, " +
                            "cardType, expiryDate, lastFourDigits FROM card WHERE jobID = '"+jobID+"' AND cardType = " +
                            "'"+cardType+"' AND expiryDate = '"+expiryDate+"' AND lastFourDigits = "+lastFourDigits+") LIMIT 1"
            );
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            conn.close(); }
        return false;
    }

    // Inserting a new payment - working
    public static boolean
        addPayment(final int jobID, final double amountDue, final int isPaid, final double discount,
                   final String paymentType, final int customerID, final int staffID) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO payment (jobID, amountDue, isPaid, discount, paymentType, customerID, staffID) SELECT * " +
                            "FROM (SELECT '"+jobID+"', '"+amountDue+"', '"+isPaid+"', '"+discount+"', '"+paymentType+"', " +
                            "'"+customerID+"', '"+staffID+"') AS tmp WHERE NOT EXISTS (SELECT jobID, amountDue, isPaid, " +
                            "discount, paymentType, customerID, staffID FROM payment WHERE jobID = '"+jobID+"' AND " +
                            "amountDue = '"+amountDue+"' AND isPaid = '"+isPaid+"' AND discount = '"+discount+"' AND " +
                            "paymentType = '"+paymentType+"' AND customerID = '"+customerID+"' AND staffID = '"+staffID+"') LIMIT 1"
            );
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            conn.close(); }
        return false;
    }

    // Inserting a new task - working
    public static boolean
        addTask(final int ID, final int jobID, final String description, final String department, final String timeTaken,
                final double price, final int discountRate, final int staffID) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO task (ID, jobID, description, department, timeTaken, price, discountRate, staffID) SELECT * " +
                            "FROM (SELECT '"+ID+"', '"+jobID+"', '"+description+"', '"+department+"', '"+timeTaken+"', " +
                            "'"+price+"', '"+discountRate+"', '"+staffID+"') AS tmp WHERE NOT EXISTS (SELECT ID, jobID, " +
                            "description, department, timeTaken, price, discountRate, staffID FROM task WHERE ID = '"+ID+"' AND " +
                            "jobID = '"+jobID+"' AND description = '"+description+"' AND department = '"+department+"' AND " +
                            "timeTaken = '"+timeTaken+"' AND price = '"+price+"' AND discountRate = '"+discountRate+"' AND " +
                            "staffID = '"+staffID+"') LIMIT 1"
            );
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            conn.close(); }
        return false;
    }

    // Inserting a new job - working
    public static boolean
        addJob(final int isUrgent, final double price, final LocalDate startDate, final LocalDate endDate,
               final LocalDate deadline, final String status, final int customerID) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO job (isUrgent, price, startDate, endDate, deadline, status, customerID) SELECT * " +
                            "FROM (SELECT '"+isUrgent+"', '"+price+"', '"+startDate+"', '"+endDate+"', '"+deadline+"', " +
                            "'"+status+"', '"+customerID+"') AS tmp WHERE NOT EXISTS (SELECT isUrgent, price, startDate, " +
                            "endDate, deadline, status, customerID FROM job WHERE isUrgent = '"+isUrgent+"' AND " +
                            "price = '"+price+"' AND startDate = '"+startDate+"' AND endDate = '"+endDate+"' AND " +
                            "deadline = '"+deadline+"' AND status = '"+status+"' AND customerID = '"+customerID+"') LIMIT 1"
            );
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            conn.close(); }
        return false;
    }

    // Inserting a new staff member - working
    public static boolean
        addStaff(final String firstName, final String lastName, final String contactNumber, final String address,
                 final String email, final String nationalInsurance, final int workHours, final String username,
                 final String password, final String role, final String privileges) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT IGNORE INTO staff (firstName, lastName, contactNumber, address, email, nationalInsurance," +
                            "workHours, username, password, role, privileges) VALUES ('"+firstName+"', '"+lastName+"', " +
                            "'"+contactNumber+"', '"+address+"', '"+email+"', '"+nationalInsurance+"', '"+workHours+"', " +
                            "'"+username+"', '"+password+"', '"+role+"', '"+privileges+"')"
            );
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            conn.close(); }
        return false;
    }

    // Inserting a new valued customer - working
    public static boolean
        addValuedCustomer(final int customerID, final String agreedDiscount, final String discountRate) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT IGNORE INTO valuedCustomer VALUES (" +
                            ""+customerID+", '"+agreedDiscount+"', '"+discountRate+"')"
            );
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            conn.close(); }
        return false;
    }

    // Inserting a new customer - working
    public static boolean
        addCustomer(final String firstName, final String lastName, final String contactNumber, final String address,
                    final String email) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT IGNORE INTO customer (firstName, lastName, contactNumber, address, email) VALUES (" +
                            "'"+firstName+"', '"+lastName+"', '"+contactNumber+"', '"+address+"', '"+email+"')"
            );
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            conn.close(); }
        return false;
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

    // Verifies login credentials - working
    public static boolean VerifyLogInCredentials(String username, char[] password) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT username FROM staff WHERE username = '"+username+"' AND password = '"+md5(new String(password))+"'",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            ResultSet res = statement.executeQuery();
            int count = 0;
            while (res.next())
                count++;
            res.previous();
            if (count == 1)
                return res.getString("username").equals(username);
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
