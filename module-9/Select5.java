package com.james.dev;

/*
 * Professor Darrell Payne
 * Bellevue University
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Select5 {

    public static void main(String[] args) {

        String url =
            "jdbc:mysql://localhost:3306/databasedb"
            + "?useSSL=false"
            + "&allowPublicKeyRetrieval=true"
            + "&serverTimezone=UTC";

        String user = "student1";
        String password = "pass";

        try (
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM address33");
        ) {

            System.out.println("Connection established");
            System.out.println("Results:");

            int cols = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    System.out.print(rs.getString(i) + " | ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
