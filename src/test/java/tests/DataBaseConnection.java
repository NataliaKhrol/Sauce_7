package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static final String URL =
            "jdbc:postgresql://ep-misty-wave-av1z2ivd.c-11.us-east-1.aws.neon.tech:5432/neondb?sslmode=require";
    private static final String USER = "neondb_owner";
    private static final String PASSWORD = "npg_0aolwAjqm3ZR";
    char o = '^';

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
