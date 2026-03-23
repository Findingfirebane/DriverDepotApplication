package app.test;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.dto.VehicleDTO;
import app.repository.VehicleDAO;
import app.util.DatabaseConnection;

public class mainTesterClass {
    public static void main (String[] args){


        System.out.println("Testing output: %n");
        VehicleDAO vehicle = new VehicleDAO();
        ArrayList<VehicleDTO> vehicles = vehicle.getAllVehicles();
        for(VehicleDTO v: vehicles){
            System.out.println(
            v.getId() + " " +
            v.getMake() + " " +
            v.getModel() + " " +
            v.getYear()
            );
        }


    }
}
