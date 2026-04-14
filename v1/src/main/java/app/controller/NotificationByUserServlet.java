package app.controller;
import app.service.NotificationService;
import app.test.userDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import app.dto.UserDTO;
import app.model.Notification;
import app.model.User;

//This servlet handles the user notifications page 
//It maps to /notificationsByUser so the users can visit localhost:8080/v1/notificationsByUser
@WebServlet("/notificationsByUser")
public class NotificationByUserServlet extends HttpServlet{

     private static final long serialVersionUID = 1L;
    private NotificationService service = new NotificationService();

    // doGet handles when the Users navigates to the user notifications page
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


                System.out.println("user notifications servlet hit");
                HttpSession session = req.getSession(false);


                if (session != null && session.getAttribute("loggedInUser") != null){

                    User user = (User) session.getAttribute("loggedInUser");

                    String email = user.getEmail();
                    System.out.println(email);

        try {
            List<Notification> notifications = service.getNotificationsByEmail(email);//this method needs to change
            req.setAttribute("notifications", notifications);
            req.getRequestDispatcher("/WEB-INF/view/CustomerPortal.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            // If the database call fails, still load the page but show an error message
            req.setAttribute("error", "Could not load notifications. Please try again.");
            req.getRequestDispatcher("/WEB-INF/view/CustomerPortal.jsp").forward(req, resp);
        }

        }else{
            resp.sendRedirect("/WEB-INF/view/CustomerPortal.jsp");// maybe need to fix
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
    }


}
