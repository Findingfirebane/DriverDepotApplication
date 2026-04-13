package app.service;

import app.dto.UserDTO;
import app.model.User;
import app.repository.UserDAO;

public class UserService {

    UserDAO userDAO = new UserDAO();

    public String signUpUser(UserDTO userDTO) {
        if (userDAO.userExistsByEmail(userDTO.getEmail())) {
            return "exists";
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole_id(userDTO.getRole_id());

        boolean inserted = userDAO.signUpUser(user);

        if (inserted) {
            return "success";
        }

        return "error";
    }

    public User signInUser(String email, String password) {
        return userDAO.signInUser(email, password);
    }
}