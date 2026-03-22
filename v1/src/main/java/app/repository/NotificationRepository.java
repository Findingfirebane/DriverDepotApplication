package app.repository;

import app.model.Notification;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class NotificationRepository {

    private Connection getConnection() throws SQLException {
        Properties props = new Properties();
        try {
            InputStream input = getClass().getClassLoader()
                    .getResourceAsStream("database.properties");
            props.load(input);
        } catch (IOException e) {
            throw new SQLException("Could not load database.properties", e);
        }

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);
    }

    public void insert(Notification notification) throws SQLException {
        String sql = "INSERT INTO notifications (vehicle, customer_name, customer_email, message) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, notification.getVehicle());
            stmt.setString(2, notification.getCustomerName());
            stmt.setString(3, notification.getCustomerEmail());
            stmt.setString(4, notification.getMessage());

            stmt.executeUpdate();
        }
    }

    public List<Notification> getAll() throws SQLException {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT id, vehicle, customer_name, customer_email, message, submitted_at FROM notifications ORDER BY submitted_at DESC";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Notification n = new Notification(
                        rs.getInt("id"),
                        rs.getString("vehicle"),
                        rs.getString("customer_name"),
                        rs.getString("customer_email"),
                        rs.getString("message"),
                        rs.getString("submitted_at")
                );
                notifications.add(n);
            }
        }
        return notifications;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM notifications WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}