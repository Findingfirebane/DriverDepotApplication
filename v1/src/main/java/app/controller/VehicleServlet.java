package app.controller;

import app.dto.VehicleDTO;
import app.service.VehicleService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchVehicles")
public class VehicleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private VehicleService vehicleService = new VehicleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String make = request.getParameter("make");
        String model = request.getParameter("model");
        String yearStr = request.getParameter("year");
        String minPriceStr = request.getParameter("minPrice");
        String maxPriceStr = request.getParameter("maxPrice");

        List<VehicleDTO> vehicles = null;

        try {
            boolean hasMake = make != null && !make.trim().isEmpty();
            boolean hasModel = model != null && !model.trim().isEmpty();
            boolean hasYear = yearStr != null && !yearStr.trim().isEmpty();
            boolean hasMin = minPriceStr != null && !minPriceStr.trim().isEmpty();
            boolean hasMax = maxPriceStr != null && !maxPriceStr.trim().isEmpty();

            if (hasMake && hasModel) {
                vehicles = vehicleService.getVehiclesByMakeAndModel(make.trim(), model.trim());
            } else if (hasMake) {
                vehicles = vehicleService.getVehiclesByMake(make.trim());
            } else if (hasModel) {
                vehicles = vehicleService.getVehiclesByModel(model.trim());
            } else if (hasYear) {
                vehicles = vehicleService.getVehiclesByYear(Integer.parseInt(yearStr.trim()));
            } else if (hasMin && hasMax) {
                vehicles = vehicleService.getVehiclesByPriceRange(
                        Integer.parseInt(minPriceStr.trim()),
                        Integer.parseInt(maxPriceStr.trim())
                );
            } else {
                vehicles = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error searching vehicles.");
        }

        request.setAttribute("vehicles", vehicles);
        request.getRequestDispatcher("/WEB-INF/view/searchVehicle.jsp").forward(request, response);
    }
}