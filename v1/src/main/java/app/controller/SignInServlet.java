package app.controller;

import app.model.User;
import app.service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("SignInServlet GET hit");
        request.getRequestDispatcher("/WEB-INF/view/signIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("SignInServlet POST hit");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {

            request.setAttribute("error", "Email and password are required.");
            request.getRequestDispatcher("/WEB-INF/view/signIn.jsp").forward(request, response);
            return;
        }

        try {
            User user = userService.signInUser(email.trim(), password.trim());

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", user);
                session.setAttribute("username", user.getUsername());
                session.setAttribute("roleId", user.getRole_id());


               if(user.getRole_id()==2){
                response.sendRedirect(request.getContextPath()+ "/notifications");
                return;
               } 

               
////////////////////////return to add logic for the customer dashboard route/////////////////////////
               // response.sendRedirect(request.getContextPath() + "/searchVehicles?login=success");
                // return;
            } else {
                request.setAttribute("error", "Invalid email or password.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong while signing in.");
        }

        request.getRequestDispatcher("/WEB-INF/view/signIn.jsp").forward(request, response);
    }
}