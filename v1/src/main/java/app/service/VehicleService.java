package app.service;

import java.util.ArrayList;
import java.util.List;

import app.dto.VehicleDTO;
import app.model.Vehicle;
import app.repository.VehicleDAO;

/**
 * Service class wraps model objects from repository class to dtos for cleaner serpeation 
 */
public class VehicleService {

    VehicleDAO vehicleDAO = new VehicleDAO();


    public List<VehicleDTO> getAllVehicles(){

        List <Vehicle> vehicles = vehicleDAO.getAllVehicles();
        List <VehicleDTO> vehicleDTOList= new ArrayList<>();

        for (Vehicle v: vehicles){
            VehicleDTO vehicleDTO = new VehicleDTO();

            vehicleDTO.setId(v.getId());
            vehicleDTO.setMake(v.getMake());
            vehicleDTO.setModel(v.getModel());
            vehicleDTO.setMileage(v.getMileage());
            vehicleDTO.setMsrp(v.getMsrp());
            vehicleDTO.setYear(v.getYear());
            vehicleDTO.setDetails(v.getDetails());

            vehicleDTOList.add(vehicleDTO);


            
        }
        return vehicleDTOList;
    }


/**
 * Get vehicles by make from user input
 * Wrapper to wrap DAO into DTO 
 **/ 
    public List<VehicleDTO> getVehiclesByMake(String make){
       List <Vehicle> vehicles = vehicleDAO.getAllVehicles();
        List <VehicleDTO> vehicleDTOList= new ArrayList<>();

        for (Vehicle v: vehicles){
            if(v.getMake().equalsIgnoreCase(make)){
            VehicleDTO vehicleDTO = new VehicleDTO();

            vehicleDTO.setId(v.getId());
            vehicleDTO.setMake(v.getMake());
            vehicleDTO.setModel(v.getModel());
            vehicleDTO.setMileage(v.getMileage());
            vehicleDTO.setMsrp(v.getMsrp());
            vehicleDTO.setYear(v.getYear());
            vehicleDTO.setDetails(v.getDetails());

            vehicleDTOList.add(vehicleDTO);
        }


            
        }
        return vehicleDTOList;
    }

/**
 * Get vehicles by model from user input
 * Wrapper to wrap DAO into DTO 
 **/ 
    public List<VehicleDTO> getVehiclesByModel(String model){
       List <Vehicle> vehiclesByModel = vehicleDAO.getVehiclesByModel(model);
        List <VehicleDTO> vehicleDTOList= new ArrayList<>();

        for (Vehicle v: vehiclesByModel){
            if(v.getModel().equalsIgnoreCase(model)){
            VehicleDTO vehicleDTO = new VehicleDTO();

            vehicleDTO.setId(v.getId());
            vehicleDTO.setMake(v.getMake());
            vehicleDTO.setModel(v.getModel());
            vehicleDTO.setMileage(v.getMileage());
            vehicleDTO.setMsrp(v.getMsrp());
            vehicleDTO.setYear(v.getYear());
            vehicleDTO.setDetails(v.getDetails());

            vehicleDTOList.add(vehicleDTO);
        }


            
        }
        return vehicleDTOList;
    }




// this section will be where we keep the filtered search methods//
/**
 * 
 * Method for filtered searching of vehicels 
 */
 public List<VehicleDTO> getVehiclesByMakeAndModel(String make, String model){
       List <Vehicle> vehiclesByModel = vehicleDAO.getVehiclesByMakeAndModel(make, model);
        List <VehicleDTO> vehicleDTOList= new ArrayList<>();

        for (Vehicle v: vehiclesByModel){
            if(v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model)){
            VehicleDTO vehicleDTO = new VehicleDTO();

            vehicleDTO.setId(v.getId());
            vehicleDTO.setMake(v.getMake());
            vehicleDTO.setModel(v.getModel());
            vehicleDTO.setMileage(v.getMileage());
            vehicleDTO.setMsrp(v.getMsrp());
            vehicleDTO.setYear(v.getYear());
            vehicleDTO.setDetails(v.getDetails());

            vehicleDTOList.add(vehicleDTO);
        }


            
        }
        return vehicleDTOList;
    }

}
