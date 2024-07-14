package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pp_1.1.4";
    private static final String DB_NAME = "root";
    private static final String DB_PASSWORD = "1234";

    public static Connection getConnection() {
        Connection DBConnection = null;
        try {
            DBConnection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
            if (!DBConnection.isClosed()){
                System.out.println("Connected to database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DBConnection;
    }
}
