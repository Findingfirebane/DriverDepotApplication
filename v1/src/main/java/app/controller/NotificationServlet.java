package app.controller;
import app.service.NotificationService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import app.model.Notification;

@WebServlet("/notifications")
public class NotificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NotificationService service = new NotificationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Notification> notifications = service.getAllNotifications();
            req.setAttribute("notifications", notifications);
            req.getRequestDispatcher("/WEB-INF/view/notifications.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Could not load notifications. Please try again.");
            req.getRequestDispatcher("/WEB-INF/view/notifications.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idParam = req.getParameter("id");
        try {
            int id = Integer.parseInt(idParam);
            service.deleteNotification(id);
            resp.sendRedirect(req.getContextPath() + "/notifications");
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Could not delete notification. Please try again.");
            try {
                List<Notification> notifications = service.getAllNotifications();
                req.setAttribute("notifications", notifications);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            req.getRequestDispatcher("/WEB-INF/view/notifications.jsp").forward(req, resp);
        }
    }
}