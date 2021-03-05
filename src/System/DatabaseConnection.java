package System;

import javax.swing.*;
import javax.xml.transform.Result;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DatabaseConnection {
    private Connection conn;

    public DatabaseConnection() throws Exception {}

    public static ArrayList<String> getData(String tableName, String columns) throws Exception {
        try {
            Connection conn = Connect();
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT "+columns+" FROM "+tableName
            );
            ResultSet res = statement.executeQuery();
            ArrayList<String> rows = new ArrayList<String>();
            String[] cArr = columns.split(",");
            String row;
            while (res.next()) {
                row = "";
                for (String s : cArr)
                    row += res.getString(s) + ", ";
                row = row.substring(0, row.length() - 2); // Removes last ', '
                System.out.println(row);
                rows.add(row);
            }
            System.out.println("All names have been received");
            return rows;
        } catch (Exception e) { System.out.println(e); }
        return null;
    }

    public static String md5(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(input.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; ++i) {
            sb.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    public boolean VerifyLogInCredentials(String username, char[] password) {
        try {
            conn = Connect();
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
        } return false;
    }

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
