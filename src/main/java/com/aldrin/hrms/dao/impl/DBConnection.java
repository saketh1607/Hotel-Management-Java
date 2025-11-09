package com.aldrin.hrms.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection con;

    public void getDBConn() {
        synchronized ("") {
            try {
                if (this.getCon() == null || this.getCon().isClosed()) {
                    try {
                        String url = "jdbc:mysql://localhost:3306/hrms";
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        setCon(DriverManager.getConnection(url, "root", "12345"));
                        System.out.println("Connection established successfully.");
                    } catch (Exception e) {
                        System.out.println("Failed to establish connection.");
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Using existing connection.");
                }
            } catch (SQLException ex) {
                System.out.println("SQL Exception during connection.");
                ex.printStackTrace();
            }
        }
    }

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection aCon) {
        con = aCon;
    }

    public static void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Connection closed successfully.");
            } else {
                System.out.println("Connection is already closed or was never established.");
            }
        } catch (Exception e) {
            System.out.println("Failed to close connection.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.getDBConn(); // Establish the connection

        // Close the connection after testing
        closeConnection();
    }
}
