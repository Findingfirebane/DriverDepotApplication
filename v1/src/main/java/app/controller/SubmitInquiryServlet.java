package app.controller;

import app.service.NotificationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/submitInquiry")
public class SubmitInquiryServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NotificationService service = new NotificationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("vehicle", "2024 BMW X5");
        req.getRequestDispatcher("/view/submitInquiry.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String vehicle = req.getParameter("vehicle");
        String customerName = req.getParameter("customerName");
        String customerEmail = req.getParameter("customerEmail");
        String message = req.getParameter("message");

        try {
            service.createInquiry(vehicle, customerName, customerEmail, message);
            resp.sendRedirect(req.getContextPath() + "/submitInquiry?success=true");
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Something went wrong. Please try again.");
            req.setAttribute("vehicle", vehicle);
            req.getRequestDispatcher("/view/submitInquiry.jsp").forward(req, resp);
        }
    }
}