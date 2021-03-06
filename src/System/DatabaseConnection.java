package System;

import javax.swing.*;
import javax.xml.transform.Result;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DatabaseConnection {

    public DatabaseConnection() throws Exception {}

    // Inserting a new job - not working yet
    public static boolean
        addJob(final byte isUrgent, final double price, final LocalDate startDate, final LocalDate endDate,
               final LocalDate deadline, final String status, final int customerID) throws SQLException {
        Connection conn = Connect();
        try {
            assert conn != null;


            PreparedStatement statement = conn.prepareStatement(
                    "INSERT IGNORE INTO job (ID, isUrgent, price, startDate, endDate, deadline, status, customerID) " +
                            "VALUES (NULL, "+isUrgent+", '"+price+"', '"+startDate+"', '"+endDate+"', '"+deadline+"', '" +
                            status+"', '"+customerID+"')"
            );

            /*
            PreparedStatement statement = conn.prepareStatement(
                    "MERGE INTO job WITH (HOLDLOCK) AS t " +
                            "USING (VALUES ("+isUrgent+", '"+price+"', '"+startDate+"', '"+endDate+"', '"+deadline+"', " +
                            "'"+status+"', '"+customerID+"')) s (isUrgent, price, startDate, endDate, deadline, status, customerID) " +
                            "ON t.isUrgent = s.isUrgent AND t.price = s.price AND t.startDate = s.startDate AND t.deadline = s.deadline " +
                            "AND t.status = s.status AND t.customerID = s.customerID WHEN NOT MATCHED THEN " +
                            "INSERT VALUES (s.isUrgent, s.price, s.startDate, s.endDate, s.deadline, s.status, s.customerID)"
            );

             */
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { conn.close(); }
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
        } finally { conn.close(); }
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally { conn.close(); }
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
        } finally { conn.close(); }
        return false;
    }

    // Converts string into hex value
    public static String md5(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(input.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; ++i)
            sb.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1, 3));
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

            Connection connect = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
            return connect;
        } catch (Exception e) { System.out.println(e); }

        return null;
    }
}
