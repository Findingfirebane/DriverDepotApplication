package app.test;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.dto.UserDTO;
import app.dto.VehicleDTO;
import app.model.User;
import app.model.Vehicle;
import app.repository.VehicleDAO;
import app.service.UserService;
import app.service.VehicleService;
import app.util.DatabaseConnection;

public class mainTesterClass {
    public static void main (String[] args){


        System.out.println("Testing service layer %n");

       UserService us = new UserService();
       UserDTO userDTO = new UserDTO();
       userDTO.setUsername("sizwe");
       userDTO.setEmail("sizwe");
       userDTO.setPassword("sizwe");
       userDTO.setRole_id(1);

       us.signUpUser(userDTO);

       }


    }

