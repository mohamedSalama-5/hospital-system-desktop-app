package main.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/hospital-system";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgre@#";

    public static Connection getConnection() {

        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully.");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }
}
