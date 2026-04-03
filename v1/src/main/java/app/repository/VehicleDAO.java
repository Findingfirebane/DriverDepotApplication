package app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
                vehicle.setMileage(rs.getInt("mileage"));
                vehicle.setMsrp(rs.getInt("msrp"));
                vehicle.setStock(rs.getInt("stock"));
                vehicle.setDetails(rs.getString("details"));
                vehicles.add(vehicle);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;

    }


    public List<Vehicle> getVehiclesByMake(String make){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try {
        Connection conn = DatabaseConnection.INSTANCE.getConnection();
        String sql = "SELECT * FROM vehicles WHERE make LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + make + "%");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
             Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setMake(rs.getString("make"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setYear(rs.getInt("year"));
                vehicle.setMileage(rs.getInt("mileage"));
                vehicle.setMsrp(rs.getInt("msrp"));
                vehicle.setStock(rs.getInt("stock"));
                vehicle.setDetails(rs.getString("details"));
                vehicles.add(vehicle);
            
        }

        rs.close();
        stmt.close();
        conn.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return vehicles;
    }



    public List<Vehicle> getVehiclesByModel(String model){
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.INSTANCE.getConnection();
            String sql = "SELECT * FROM vehicles WHERE model LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + model + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                    vehicle.setId(rs.getInt("id"));
                    vehicle.setMake(rs.getString("make"));
                    vehicle.setModel(rs.getString("model"));
                    vehicle.setYear(rs.getInt("year"));
                    vehicle.setMileage(rs.getInt("mileage"));
                    vehicle.setMsrp(rs.getInt("msrp"));
                    vehicle.setStock(rs.getInt("stock"));
                    vehicle.setDetails(rs.getString("details"));
                    vehicles.add(vehicle);
                
            }

            rs.close();
            stmt.close();
            conn.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return vehicles;
    }

public List<Vehicle> getVehiclesByYear(int year){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
    try {
        Connection conn = DatabaseConnection.INSTANCE.getConnection();
        String sql = "SELECT * FROM vehicles WHERE year = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, year );  

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
             Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setMake(rs.getString("make"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setYear(rs.getInt("year"));
                vehicle.setMileage(rs.getInt("mileage"));
                vehicle.setMsrp(rs.getInt("msrp"));
                vehicle.setStock(rs.getInt("stock"));
                vehicle.setDetails(rs.getString("details"));
                vehicles.add(vehicle);
            
        }

        rs.close();
        stmt.close();
        conn.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return vehicles;
    }


    //this section contains the filtering methods
     
public List<Vehicle> getVehiclesByMakeAndModel(String make, String model){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
    try {
        Connection conn = DatabaseConnection.INSTANCE.getConnection();
        String sql = "SELECT * FROM vehicles WHERE model LIKE ? AND make LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + model + "%");
        stmt.setString(2, "%" + make + "%");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
             Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setMake(rs.getString("make"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setYear(rs.getInt("year"));
                vehicle.setMileage(rs.getInt("mileage"));
                vehicle.setMsrp(rs.getInt("msrp"));
                vehicle.setStock(rs.getInt("stock"));
                vehicle.setDetails(rs.getString("details"));
                vehicles.add(vehicle);
            
        }

        rs.close();
        stmt.close();
        conn.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return vehicles;
    }
/**
 * used to search vehicles passed on a range 
 */
    public List<Vehicle> getVehiclesByPriceRange(int rangeMin, int rangeMax){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try {
        Connection conn = DatabaseConnection.INSTANCE.getConnection();
        String sql = "SELECT * FROM vehicles WHERE msrp BETWEEN ? AND  ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,  rangeMin);
        stmt.setInt(2, rangeMax); 

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
             Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setMake(rs.getString("make"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setYear(rs.getInt("year"));
                vehicle.setMileage(rs.getInt("mileage"));
                vehicle.setMsrp(rs.getInt("msrp"));
                vehicle.setStock(rs.getInt("stock"));
                vehicle.setDetails(rs.getString("details"));
                vehicles.add(vehicle);
            
        }

        rs.close();
        stmt.close();
        conn.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return vehicles;
    }
}
