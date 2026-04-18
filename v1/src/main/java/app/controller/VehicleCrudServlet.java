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
import javax.servlet.http.HttpSession;

@WebServlet("/manageVehicles")
public class VehicleCrudServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VehicleService vehicleService = new VehicleService();

    // Staff-only check
    private boolean isStaff(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return false;
        Integer roleId = (Integer) session.getAttribute("roleId");
        return roleId != null && roleId == 2;
    }

    // GET: show vehicle management page (list + forms)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!isStaff(request)) {
            response.sendRedirect(request.getContextPath() + "/signIn");
            return;
        }

        String action = request.getParameter("action");
        String idStr  = request.getParameter("id");

        if ("edit".equals(action) && idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                VehicleDTO vehicle = vehicleService.getVehicleById(id);
                request.setAttribute("editVehicle", vehicle);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid vehicle ID.");
            }
        } else if ("delete".equals(action) && idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                boolean deleted = vehicleService.deleteVehicle(id);
                if (deleted) {
                    response.sendRedirect(request.getContextPath() + "/manageVehicles?msg=deleted");
                } else {
                    response.sendRedirect(request.getContextPath() + "/manageVehicles?error=deletefailed");
                }
                return;
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/manageVehicles?error=invalidid");
                return;
            }
        }

        List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
        request.setAttribute("vehicles", vehicles);
        request.getRequestDispatcher("/WEB-INF/view/manageVehicles.jsp").forward(request, response);
    }

    // POST: add or update vehicle
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!isStaff(request)) {
            response.sendRedirect(request.getContextPath() + "/signIn");
            return;
        }

        String action = request.getParameter("action");

        try {
            VehicleDTO dto = new VehicleDTO();
            dto.setMake(request.getParameter("make").trim());
            dto.setModel(request.getParameter("model").trim());
            dto.setYear(Integer.parseInt(request.getParameter("year").trim()));
            dto.setMileage(Integer.parseInt(request.getParameter("mileage").trim()));
            dto.setMsrp(Integer.parseInt(request.getParameter("msrp").trim()));
            dto.setStock(Integer.parseInt(request.getParameter("stock").trim()));
            String details = request.getParameter("details");
            dto.setDetails(details != null ? details.trim() : "");

            if ("add".equals(action)) {
                boolean added = vehicleService.addVehicle(dto);
                if (added) {
                    response.sendRedirect(request.getContextPath() + "/manageVehicles?msg=added");
                } else {
                    response.sendRedirect(request.getContextPath() + "/manageVehicles?error=addfailed");
                }
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id").trim());
                dto.setId(id);
                boolean updated = vehicleService.updateVehicle(dto);
                if (updated) {
                    response.sendRedirect(request.getContextPath() + "/manageVehicles?msg=updated");
                } else {
                    response.sendRedirect(request.getContextPath() + "/manageVehicles?error=updatefailed");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/manageVehicles");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/manageVehicles?error=invalid");
        }
    }
}
