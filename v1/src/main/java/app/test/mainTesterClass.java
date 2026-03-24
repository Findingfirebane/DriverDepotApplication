package app.test;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.dto.VehicleDTO;
import app.model.Vehicle;
import app.repository.VehicleDAO;
import app.service.VehicleService;
import app.util.DatabaseConnection;

public class mainTesterClass {
    public static void main (String[] args){


        System.out.println("Testing service layer %n");

        VehicleService vs = new VehicleService();

        List <VehicleDTO> vehicle= vs.getVehiclesByPriceRange(15000, 30000);

        // VehicleDAO vehicle = new VehicleDAO();
        // ArrayList<Vehicle> vehicles = vehicle.getAllVehicles();
        for(VehicleDTO v: vehicle){
            System.out.println(
            v.getId() + " " +
            v.getMake() + " " +
            v.getModel() + " " +
            v.getMsrp()
            );
       }


    }
}
