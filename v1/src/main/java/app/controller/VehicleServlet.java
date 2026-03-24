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
        int year = 0; 
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


        try {
            List<VehicleDTO> vehicles;


            if((make != null || !make.isEmpty()) && (model == null || model.isEmpty()) && (yearParam == null || yearParam.isEmpty())) {
                vehicles = vs.getVehiclesByMake(make);
                System.out.println("Vehicels size for make search vehicles: " + vehicles.size());
            }else if((make == null || make.isEmpty()) && (model != null || !model.isEmpty()) && (yearParam == null || yearParam.isEmpty())){
                vehicles = vs.getVehiclesByModel(model);
                System.out.println("Vehicels size model: " + vehicles.size());
            }else if ((make == null || make.isEmpty()) && (model == null || model.isEmpty()) && (yearParam != null || !yearParam.isEmpty())){
                vehicles = vs.getVehiclesByYear(year);
                System.out.println("Vehicels size year: " + vehicles.size()); 
            }else if ((make != null || !make.isEmpty()) && (model != null || !model.isEmpty()) && (yearParam == null || yearParam.isEmpty())){
                vehicles = vs.getVehiclesByMakeAndModel(make, model);
                System.out.println("Vehicels size make and model: " + vehicles.size()); 
            }else {  
                 vehicles = vs.getAllVehicles();  
                System.out.println("Vehicels size  this is the search with no params" + vehicles.size());

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

