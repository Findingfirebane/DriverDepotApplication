package app.controller;

import app.service.NotificationService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import app.model.Notification;

// This servlet handles the staff notifications page (US.5)
// It maps to /notifications so only staff can visit localhost:8080/v1/notifications
@WebServlet("/notifications")
public class NotificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NotificationService service = new NotificationService();

    // Helper method to check if the current user is logged in as staff
    private boolean isStaff(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("roleId") == null) {
            return false;
        }

        int roleId = (int) session.getAttribute("roleId");
        return roleId == 1; // staff = 1
    }

    // doGet handles when the staff member navigates to the notifications page
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        // Not logged in
        if (session == null || session.getAttribute("roleId") == null) {
            resp.sendRedirect(req.getContextPath() + "/signIn");
            return;
        }

        // Logged in but not staff
        if (!isStaff(req)) {
            resp.sendRedirect(req.getContextPath() + "/searchVehicles");
            return;
        }

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

    // doPost handles when the staff member clicks the Delete button on a row
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        // Not logged in
        if (session == null || session.getAttribute("roleId") == null) {
            resp.sendRedirect(req.getContextPath() + "/signIn");
            return;
        }

        // Logged in but not staff
        if (!isStaff(req)) {
            resp.sendRedirect(req.getContextPath() + "/searchVehicles");
            return;
        }

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