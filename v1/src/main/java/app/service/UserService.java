package app.service;

import app.dto.UserDTO;
import app.model.User;
import app.repository.UserDAO;

public class UserService {

    UserDAO userDAO = new UserDAO();



    public UserDTO signUpUser(UserDTO userDTO){

        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole_id(userDTO.getRole_id());
        userDAO.signUpUser(user);
        return userDTO;
    }

}
