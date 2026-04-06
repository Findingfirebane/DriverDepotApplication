package app.controller;

import app.dto.UserDTO;
import app.service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("SignUpServlet GET hit");
        request.getRequestDispatcher("/WEB-INF/view/signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("SignUpServlet POST hit");

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (username == null || username.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            role == null || role.trim().isEmpty()) {

            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/WEB-INF/view/signUp.jsp").forward(request, response);
            return;
        }

        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username.trim());
            userDTO.setEmail(email.trim());
            userDTO.setPassword(password.trim());
            userDTO.setRole_id(Integer.parseInt(role.trim()));

            String result = userService.signUpUser(userDTO);

            if ("success".equals(result)) {
                response.sendRedirect(request.getContextPath() + "/signIn?signup=success");
                return;
            } else if ("exists".equals(result)) {
                request.setAttribute("error", "This user already has an account.");
            } else {
                request.setAttribute("error", "Signup failed. Please try again.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong while signing up.");
        }

        request.getRequestDispatcher("/WEB-INF/view/signUp.jsp").forward(request, response);
    }
}