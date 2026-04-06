package app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.model.User;
import app.util.DatabaseConnection;

public class UserDAO {

    public boolean userExistsByEmail(String email) {
        String sql = "SELECT id FROM users WHERE email = ?";

        try (Connection con = DatabaseConnection.INSTANCE.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean signUpUser(User user) {
        String sql = "INSERT INTO users(user_name, email, password, role_id) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.INSTANCE.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getRole_id());

            int rows = ps.executeUpdate();

            System.out.println("User inserted " + user.getUsername());
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User signInUser(String email, String password) {
        String sql = "SELECT id, user_name, email, password, role_id FROM users WHERE email = ? AND password = ?";

        try (Connection con = DatabaseConnection.INSTANCE.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole_id(rs.getInt("role_id"));
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}