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
        System.out.println("VehicleServlet HIT");

        String make = request.getParameter("make");
        System.out.println("Make param: " + make);

        try {
            List<VehicleDTO> vehicles;

            if(make == null || make.isEmpty()){
                vehicles = vs.getAllVehicles();
                System.out.println("Vehicels size: " + vehicles.size());
            }else{
                vehicles = vs.getVehiclesByMake(make);
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
