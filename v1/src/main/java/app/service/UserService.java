package app.service;

import app.dto.UserDTO;
import app.model.User;
import app.repository.UserDAO;

public class UserService {

    UserDAO userDAO = new UserDAO();



    public UserDTO signUpUser(UserDTO user){

        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole_id(user.getRole_id());
        
        return userDTO;
    }

}
