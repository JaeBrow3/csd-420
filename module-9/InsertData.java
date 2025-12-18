package com.james.dev;

/*
 * Professor Darrell Payne
 * Bellevue University
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertData {

    private static final String URL =
            "jdbc:mysql://localhost:3306/databasedb"
            + "?useSSL=false"
            + "&allowPublicKeyRetrieval=true"
            + "&serverTimezone=UTC";

    private static final String USER = "student1";
    private static final String PASSWORD = "pass";

    public static void main(String[] args) {

        System.out.println("Connecting...");

        String sql = "INSERT INTO address33 "
                   + "(ID, LASTNAME, FIRSTNAME, STREET, CITY, STATE, ZIP) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        Object[][] rows = new Object[][]{
                {55, "Larry",  "Rich",    "1111 Redwing Circle888", "Bellevue", "NE", "68123"},
                {1,  "Fine",   "Ruth",    "1111 Redwing Circle",    "Bellevue", "NE", "68123"},
                {2,  "Howard", "Curly",   "1000 Galvin Road South", "Bellevue", "NE", "68005"},
                {3,  "Howard", "Will",    "2919 Redwing Circle",    "Bellevue", "NE", "68123"},
                {4,  "Wilson", "Larry",   "1121 Redwing Circle",    "Bellevue", "NE", "68124"},
                {5,  "Johnson","George",  "1300 Galvin Road South", "Bellevue", "NE", "68006"},
                {6,  "Long",   "Matthew", "2419 Redwing Circle",    "Bellevue", "NE", "68127"},
                {44, "Tom",    "Matthew", "1999 Redwing Circle",    "Bellevue", "NE", "68123"},
        };

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            return;
        }

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Optional: control commit yourself
            // con.setAutoCommit(false);

            int total = 0;
            for (Object[] r : rows) {
                ps.setInt(1, (int) r[0]);
                ps.setString(2, (String) r[1]);
                ps.setString(3, (String) r[2]);
                ps.setString(4, (String) r[3]);
                ps.setString(5, (String) r[4]);
                ps.setString(6, (String) r[5]);
                ps.setString(7, (String) r[6]);
                total += ps.executeUpdate();
            }

            // If you turned autocommit off:
            // con.commit();

            System.out.println("Data inserted. Rows added: " + total);
            System.out.println("Database connections closed.");

        } catch (Exception e) {
            System.out.println("Insert Data Failed");
            e.printStackTrace();
        }
    }
}
