package app.service;

import app.model.Notification;
import app.repository.NotificationRepository;

import java.sql.SQLException;
import java.util.List;

/*
 * This owns the instance of the notification repository the servlet will communicate with
 * Image: this is who connnects with who: servlet -> talks to -> service -> repository -> db
 */
		
public class NotificationService {

    private NotificationRepository repository = new NotificationRepository();
    /*
     * The servlet will call this function to make a notification row
     * the two empty fields will be filled by MySQL
     */
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

    public List<Notification> getNotificationsByEmail(String email) throws SQLException{
        return repository.getNotificationsByEmail(email);
    }
}
