package com.studentmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/StudentManagement";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

   
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found!", e);
        }
    }


    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Database Connected Successfully!");
        } catch (SQLException e) {
            System.out.println("Connection Failed: " + e.getMessage());
        }
    }
}
