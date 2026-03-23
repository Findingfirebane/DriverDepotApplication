package app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.dto.VehicleDTO;
import app.model.Vehicle;
import app.util.DatabaseConnection;

public class VehicleDAO {

    public ArrayList<Vehicle> getAllVehicles(){

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try(Connection con = DatabaseConnection.INSTANCE.getConnection();
            PreparedStatement ps = con.prepareStatement("select * FROM vehicles");
            ResultSet rs = ps.executeQuery();){
// make, model, year, mileage, msrp, stock, details column names
            while(rs.next()){
                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setMake(rs.getString("make"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setYear(rs.getInt("year"));
                vehicle.setMilage(rs.getInt("mileage"));
                vehicle.setMsrp(rs.getInt("msrp"));
                vehicle.setStock(rs.getInt("stock"));
                vehicle.setDetails(rs.getString("details"));
                vehicles.add(vehicle);
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return vehicles;

    }
}
