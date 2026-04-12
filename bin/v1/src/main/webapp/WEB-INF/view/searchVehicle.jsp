<%@ page import="java.util.List" %>
<%@ page import="app.dto.VehicleDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Vehicle Search</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Optional: Add Bootstrap for styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

    <h2>Search Vehicles</h2>

    <form method="get" action="searchVehicles" class="mb-3">
        <input type="text" name="make" placeholder="Enter make" class="form-control mb-2" style="max-width: 300px; display:inline-block;">
        <input type="text" name="model" placeholder="Enter model" class="form-control mb-2" style="max-width: 300px; display:inline-block;">
        <input type="number" name="year" placeholder="Enter Year" class="form-control mb-2" style="max-width: 300px; display:inline-block;">
        <input type="number" name="minPrice" placeholder="Enter Min Price" step=1000 class="form-control mb-2" style="max-width: 300px; display:inline-block;">
        <input type="number" name="maxPrice" placeholder="Enter Max Price" step=1000 class="form-control mb-2" style="max-width: 300px; display:inline-block;">

        <button type="submit" class="btn btn-primary">Search</button>
    </form>

    <hr>

    <%
        // Retrieve the vehicles list from the request
        List<VehicleDTO> vehicles = (List<VehicleDTO>) request.getAttribute("vehicles");

        if (vehicles != null && !vehicles.isEmpty()) {
            for (VehicleDTO v : vehicles) {
    %>
                <div class="card mb-2" style="max-width: 500px;">
                    <div class="card-body">
                        <h5 class="card-title"><%= v.getMake() %> - <%= v.getModel() %></h5>
                        <p class="card-text">
                            Year: <%= v.getYear() %> <br>
                            Mileage: <%= v.getMileage() %> miles <br>
                            MSRP: $<%= v.getMsrp() %>
                        </p>
                    </div>
                </div>
    <%
            }
        } else {
    %>
        <p>No vehicles found.</p>
    <%
        }
    %>

    <!-- Optional Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>