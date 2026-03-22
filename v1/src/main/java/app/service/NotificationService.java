package app.service;

import app.model.Notification;
import app.repository.NotificationRepository;

import java.sql.SQLException;
import java.util.List;

public class NotificationService {

    private NotificationRepository repository = new NotificationRepository();

    public void createInquiry(String vehicle, String customerName, 
                               String customerEmail, String message) throws SQLException {
        Notification notification = new Notification(
                0,
                vehicle,
                customerName,
                customerEmail,
                message,
                null
        );
        repository.insert(notification);
    }

    public List<Notification> getAllNotifications() throws SQLException {
        return repository.getAll();
    }

    public void deleteNotification(int id) throws SQLException {
        repository.delete(id);
    }
}
