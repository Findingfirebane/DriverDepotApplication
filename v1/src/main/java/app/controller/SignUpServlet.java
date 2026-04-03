package app.controller;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.dto.UserDTO;
import app.model.User;
import app.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet{

    public UserService user_service = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
        System.out.println("Signup servlet hit");

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        Integer role_id = null;





        if(role != null && !role.isEmpty()){

            try {
                role_id = Integer.parseInt(role);
                
            } catch (NumberFormatException e) {
                System.out.println("invalid number format");
                e.printStackTrace();
            }            
        }


        boolean username_valid = username != null && !username.isEmpty();
        boolean email_valid = email != null && !email.isEmpty();
        boolean password_valid = password != null && !password.isEmpty();
        boolean role_valid = role != null && !role.isEmpty();
        

        try {
            
            if(username_valid && email_valid && password_valid && role_valid){
                UserDTO user = new UserDTO(username,email,password,role_id);
                
                user_service.signUpUser(user);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }



    }

}
