package com.james.dev;

/*
 * Professor Darrell Payne
 * Bellevue University
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable {

    private static final String URL =
            "jdbc:mysql://localhost:3306/databasedb"
            + "?useSSL=false"
            + "&allowPublicKeyRetrieval=true"
            + "&serverTimezone=UTC";

    private static final String USER = "student1";
    private static final String PASSWORD = "pass";

    public static void main(String[] args) {

        System.out.println("Connecting...");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            return;
        }

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement()) {

            // Drop and create
            stmt.executeUpdate("DROP TABLE IF EXISTS address33");
            System.out.println("Table address33 dropped (if it existed).");

            stmt.executeUpdate(
                    "CREATE TABLE address33 ("
                    + "ID INT PRIMARY KEY, "
                    + "LASTNAME VARCHAR(40), "
                    + "FIRSTNAME VARCHAR(40), "
                    + "STREET VARCHAR(40), "
                    + "CITY VARCHAR(40), "
                    + "STATE VARCHAR(40), "
                    + "ZIP VARCHAR(40)"
                    + ")"
            );

            System.out.println("Table address33 created.");
            System.out.println("Database connections closed.");

        } catch (Exception e) {
            System.out.println("Error connecting to database or creating table.");
            e.printStackTrace();
        }
    }
}
