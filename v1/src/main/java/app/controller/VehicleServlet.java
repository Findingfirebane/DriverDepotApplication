package app.controller;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import app.dto.VehicleDTO;
import app.service.VehicleService;



@WebServlet("/searchVehicles")
public class VehicleServlet extends HttpServlet{

    public VehicleService vs = new VehicleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException {
        System.out.println("searchVehicleServlet HIT");

        String make = request.getParameter("make");
        String model = request.getParameter("model");
        String yearParam = request.getParameter("year");
        String maxPriceParam = request.getParameter("maxPrice");
        String minPriceParam = request.getParameter("minPrice");

        Integer maxPrice = null;
        Integer minPrice = null;
        Integer year = null;  

// section checks that params have values and stores them in a wrapper 
         if(maxPriceParam != null && !maxPriceParam.isEmpty()){
            try {
                maxPrice = Integer.parseInt(maxPriceParam);
                System.out.println("int Max Price conversion from param us: " + maxPrice);
                
            } catch (NumberFormatException e) { 
                System.out.println("invalid format"); 
                e.printStackTrace();
            }
        } 

        if(minPriceParam != null && !minPriceParam.isEmpty()){
            try {
                minPrice = Integer.parseInt(minPriceParam);
                System.out.println("Min Price conversion from param us: " + minPrice);
                
            } catch (NumberFormatException e) { 
                System.out.println("invalid format"); 
                e.printStackTrace();
            }
        }
        
        // need to convert param to integer, and catch numberFormatExeption
        if(yearParam != null && !yearParam.isEmpty()){
            try {
                year = Integer.parseInt(yearParam);
                System.out.println("int year conversion from param us: " + year);
                
            } catch (NumberFormatException e) { 
                System.out.println("invalid format"); 
                e.printStackTrace();
            }
        }
        System.out.println("Make param: " + make);
        System.out.println("model param: " + model);
        System.out.println("year param: " + year);
        System.out.println("Max Price param: " + maxPrice); 
        System.out.println("min Price param: " + minPrice);

        boolean makeValid =  make != null && !make.isEmpty();  
        boolean modelValid = model != null && !model.isEmpty(); 
        boolean yearValid = yearParam != null && !yearParam.isEmpty(); 
        boolean minPriceValid = minPriceParam != null && !minPriceParam.isEmpty();
        boolean maxPriceValid = maxPriceParam != null && !maxPriceParam.isEmpty();
        
        


//need to clean this with bool vars to check validity 
        try { 
            List<VehicleDTO> vehicles; 
            if(!makeValid && !modelValid && !yearValid && minPriceValid && maxPriceValid){
                vehicles=vs.getVehiclesByPriceRange(minPrice, maxPrice); 
                System.out.println("Vehicels size for Price range search vehicles: " + vehicles.size());
            // search by make only
            }else if(makeValid && !modelValid && !yearValid) {
                vehicles = vs.getVehiclesByMake(make);
                System.out.println("Vehicels size for make search vehicles: " + vehicles.size());
              // search by model   
            }else if(!makeValid && modelValid && !yearValid){ 
                vehicles = vs.getVehiclesByModel(model);
                System.out.println("Vehicels size model: " + vehicles.size());
                // search by year 
            }else if (!makeValid && !modelValid && yearValid){
                vehicles = vs.getVehiclesByYear(year);
                System.out.println("Vehicels size year: " + vehicles.size());
                // search by make and model   
            }else if (makeValid && modelValid && !yearValid){ 
                vehicles = vs.getVehiclesByMakeAndModel(make, model);
                System.out.println("Vehicels size make and model: " + vehicles.size()); 
                // if all fields empty
            }else if(!makeValid && !modelValid && !yearValid && !minPriceValid && !maxPriceValid){  
                 vehicles = vs.getAllVehicles();   
                System.out.println("Vehicels size  this is the search with no params" + vehicles.size());  
            }else{ 
                vehicles = vs.getAllVehicles();   

            }

            System.out.println("Vehicles returned: " + vehicles.size());

            request.setAttribute("vehicles", vehicles);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/searchVehicle.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();

            response.setContentType("text/plain");
            e.printStackTrace(response.getWriter());
        }

    }

    
}

