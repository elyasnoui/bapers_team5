package System;

import java.sql.*;

public class DatabaseConnection {
    private Connection conn;

    public DatabaseConnection() throws Exception {
        conn = Connect();
    }

    public boolean VerifyLogInCredentials(String username, String password) {
        return true;
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
