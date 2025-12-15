package com.james.dev;

import java.sql.*;

public class FanDao {

    private static final String URL =
            "jdbc:mysql://localhost:3306/databasedb"
                    + "?useSSL=false"
                    + "&allowPublicKeyRetrieval=true"
                    + "&serverTimezone=UTC";

    private static final String USER = "student1";
    private static final String PASSWORD = "pass";

    // Make this public so tests can verify connectivity if desired
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public Fan getFanById(int id) {
        final String sql = "SELECT id, firstname, lastname, favoriteteam FROM fans WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                return new Fan(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("favoriteteam")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB error in getFanById(" + id + "): " + e.getMessage(), e);
        }
    }

    public boolean updateFan(Fan fan) {
        final String sql = "UPDATE fans SET firstname=?, lastname=?, favoriteteam=? WHERE id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, fan.getFirstName());
            ps.setString(2, fan.getLastName());
            ps.setString(3, fan.getFavoriteTeam());
            ps.setInt(4, fan.getId());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException("DB error in updateFan(id=" + fan.getId() + "): " + e.getMessage(), e);
        }
    }
}
