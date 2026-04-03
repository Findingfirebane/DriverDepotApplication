package app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.model.User;
import app.model.Vehicle;
import app.util.DatabaseConnection;

public class UserDAO {

    public User signUpUser(User user){
        User new_user = new User();
        
        String sql = "insert into users(user_name, email, password, role_id) values (?,?,?,?);";


        try (Connection con = DatabaseConnection.INSTANCE.getConnection();) {


                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPassword());
                ps.setInt(4, user.getRole_id());
                /*role id is an int but will be converted from the html form into an integer representing role-type  */
                 ps.executeUpdate();

                
                System.out.println("User inserted" +user.getUsername());
                
            
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new_user;
    }

}
