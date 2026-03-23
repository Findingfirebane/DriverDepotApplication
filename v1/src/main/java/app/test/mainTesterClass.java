package app.test;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.util.DatabaseConnection;
public class mainTesterClass {
    public static void main (String[] args){


        
        
        try {
            Connection con = DatabaseConnection.INSTANCE.getConnection();
             Statement stmt = con.createStatement();

            // Quick select
            String sql = "SELECT id, user_name, email, role_id FROM users LIMIT 5";
            ResultSet rs = stmt.executeQuery(sql);

            // Print results
            System.out.println("Users:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("user_name");
                String email = rs.getString("email");
                String role = rs.getString("role_id");

                System.out.printf("%d: %s | %s | %s%n", id, username, email, role);
            }

            // Close resources
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
