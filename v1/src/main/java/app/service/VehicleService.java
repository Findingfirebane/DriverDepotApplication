package app.service;

import java.util.ArrayList;
import java.util.List;

import app.dto.VehicleDTO;
import app.model.Vehicle;
import app.repository.VehicleDAO;

public class VehicleService {

    VehicleDAO vehicleDAO = new VehicleDAO();

    private VehicleDTO mapToDTO(Vehicle v) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(v.getId());
        dto.setMake(v.getMake());
        dto.setModel(v.getModel());
        dto.setMileage(v.getMileage());
        dto.setMsrp(v.getMsrp());
        dto.setYear(v.getYear());
        dto.setStock(v.getStock());
        dto.setDetails(v.getDetails());
        return dto;
    }

    public List<VehicleDTO> getAllVehicles() {
        List<VehicleDTO> list = new ArrayList<>();
        for (Vehicle v : vehicleDAO.getAllVehicles()) {
            list.add(mapToDTO(v));
        }
        return list;
    }

    public List<VehicleDTO> getVehiclesByMake(String make) {
        List<VehicleDTO> list = new ArrayList<>();
        for (Vehicle v : vehicleDAO.getVehiclesByMake(make)) {
            list.add(mapToDTO(v));
        }
        return list;
    }

    public List<VehicleDTO> getVehiclesByModel(String model) {
        List<VehicleDTO> list = new ArrayList<>();
        for (Vehicle v : vehicleDAO.getVehiclesByModel(model)) {
            list.add(mapToDTO(v));
        }
        return list;
    }

    public List<VehicleDTO> getVehiclesByYear(int year) {
        List<VehicleDTO> list = new ArrayList<>();
        for (Vehicle v : vehicleDAO.getVehiclesByYear(year)) {
            list.add(mapToDTO(v));
        }
        return list;
    }

    public List<VehicleDTO> getVehiclesByPriceRange(int min, int max) {
        List<VehicleDTO> list = new ArrayList<>();
        for (Vehicle v : vehicleDAO.getVehiclesByPriceRange(min, max)) {
            list.add(mapToDTO(v));
        }
        return list;
    }

    public List<VehicleDTO> getVehiclesByMakeAndModel(String make, String model) {
        List<VehicleDTO> list = new ArrayList<>();
        for (Vehicle v : vehicleDAO.getVehiclesByMakeAndModel(make, model)) {
            list.add(mapToDTO(v));
        }
        return list;
    }

    /** US.4 — staff vehicle CRUD */
    public VehicleDTO getVehicleById(int id) {
        Vehicle v = vehicleDAO.getVehicleById(id);
        return v != null ? mapToDTO(v) : null;
    }

    public boolean addVehicle(VehicleDTO dto) {
        Vehicle v = new Vehicle();
        v.setMake(dto.getMake());
        v.setModel(dto.getModel());
        v.setYear(dto.getYear());
        v.setMileage(dto.getMileage());
        v.setMsrp(dto.getMsrp());
        v.setStock(dto.getStock());
        v.setDetails(dto.getDetails());
        return vehicleDAO.addVehicle(v);
    }

    public boolean updateVehicle(VehicleDTO dto) {
        Vehicle v = new Vehicle();
        v.setId(dto.getId());
        v.setMake(dto.getMake());
        v.setModel(dto.getModel());
        v.setYear(dto.getYear());
        v.setMileage(dto.getMileage());
        v.setMsrp(dto.getMsrp());
        v.setStock(dto.getStock());
        v.setDetails(dto.getDetails());
        return vehicleDAO.updateVehicle(v);
    }

    public boolean deleteVehicle(int id) {
        return vehicleDAO.deleteVehicle(id);
    }
}