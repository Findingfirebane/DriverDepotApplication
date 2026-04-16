<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page import="java.util.List" %>
<%@ page import="app.dto.VehicleDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DriverDepot - Search Vehicles</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f8fafc;
            color: #1e293b;
        }

        header {
            background-color: #ffffff;
            border-bottom: 1px solid #e2e8f0;
            padding: 18px 36px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .brand h1 {
            font-size: 24px;
            color: #334155;
            margin-bottom: 3px;
        }

        .brand p {
            font-size: 13px;
            color: #64748b;
        }

        .nav-buttons {
            display: flex;
            gap: 12px;
            align-items: center;
        }

        .nav-buttons a {
            text-decoration: none;
            padding: 10px 18px;
            border-radius: 8px;
            font-size: 14px;
            font-weight: 600;
            transition: 0.2s ease;
        }

        .btn-outline {
            border: 1px solid #cbd5e1;
            color: #334155;
            background-color: #ffffff;
        }

        .btn-outline:hover {
            background-color: #f1f5f9;
        }

        .btn-dark {
            background-color: #475569;
            color: white;
            border: 1px solid #475569;
        }

        .btn-dark:hover {
            background-color: #334155;
        }

        .btn-danger {
            background-color: #dc2626;
            color: white;
            border: 1px solid #dc2626;
        }

        .btn-danger:hover {
            background-color: #b91c1c;
        }

        .welcome-text {
            padding: 10px 14px;
            font-size: 14px;
            color: #334155;
            font-weight: 600;
        }

        .hero {
            max-width: 1200px;
            margin: 40px auto 24px auto;
            padding: 0 20px;
        }

        .hero-card {
            background: linear-gradient(135deg, #475569, #1e293b);
            color: white;
            border-radius: 16px;
            padding: 40px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.08);
        }

        .hero-card h2 {
            font-size: 32px;
            margin-bottom: 10px;
        }

        .hero-card p {
            font-size: 15px;
            color: #e2e8f0;
            max-width: 700px;
            line-height: 1.6;
        }

        .container {
            max-width: 1200px;
            margin: 24px auto 50px auto;
            padding: 0 20px;
        }

        .alert-success {
            background-color: #f0fdf4;
            border: 1px solid #bbf7d0;
            color: #166534;
            padding: 12px 14px;
            border-radius: 10px;
            margin-bottom: 16px;
            font-size: 14px;
        }

        .alert-error {
            background-color: #fef2f2;
            border: 1px solid #fecaca;
            color: #991b1b;
            padding: 12px 14px;
            border-radius: 10px;
            margin-bottom: 16px;
            font-size: 14px;
        }

        .search-card {
            background-color: #ffffff;
            border: 1px solid #e2e8f0;
            border-radius: 16px;
            padding: 28px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.04);
            margin-bottom: 28px;
        }

        .search-card h3 {
            font-size: 22px;
            margin-bottom: 8px;
            color: #1e293b;
        }

        .search-card p {
            font-size: 14px;
            color: #64748b;
            margin-bottom: 22px;
        }

        .search-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
            gap: 16px;
        }

        .form-group label {
            display: block;
            margin-bottom: 6px;
            font-size: 14px;
            font-weight: 600;
            color: #334155;
        }

        .form-group input {
            width: 100%;
            padding: 11px 12px;
            border: 1px solid #cbd5e1;
            border-radius: 10px;
            font-size: 14px;
            background-color: #fff;
        }

        .form-group input:focus {
            outline: none;
            border-color: #475569;
        }

        .search-actions {
            margin-top: 20px;
            display: flex;
            gap: 12px;
            flex-wrap: wrap;
        }

        .search-actions button,
        .search-actions a {
            padding: 12px 18px;
            border-radius: 10px;
            font-size: 14px;
            font-weight: 600;
            text-decoration: none;
            cursor: pointer;
        }

        .search-btn {
            background-color: #475569;
            color: #fff;
            border: none;
        }

        .search-btn:hover {
            background-color: #334155;
        }

        .clear-btn {
            background-color: #fff;
            color: #334155;
            border: 1px solid #cbd5e1;
        }

        .clear-btn:hover {
            background-color: #f8fafc;
        }

        .results-header {
            margin-bottom: 18px;
        }

        .results-header h3 {
            font-size: 22px;
            color: #1e293b;
            margin-bottom: 6px;
        }

        .results-header p {
            font-size: 14px;
            color: #64748b;
        }

        .vehicle-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(290px, 1fr));
            gap: 20px;
        }

        .vehicle-card {
            background-color: #ffffff;
            border: 1px solid #e2e8f0;
            border-radius: 16px;
            overflow: hidden;
            box-shadow: 0 4px 10px rgba(0,0,0,0.04);
            transition: transform 0.2s ease;
        }

        .vehicle-card:hover {
            transform: translateY(-3px);
        }

        .vehicle-top {
            background-color: #f8fafc;
            padding: 20px;
            border-bottom: 1px solid #e2e8f0;
        }

        .vehicle-top h4 {
            font-size: 20px;
            color: #1e293b;
            margin-bottom: 6px;
        }

        .vehicle-top span {
            font-size: 13px;
            color: #64748b;
        }

        .vehicle-body {
            padding: 20px;
        }

        .vehicle-body p {
            margin-bottom: 10px;
            font-size: 14px;
            color: #334155;
        }

        .price-tag {
            display: inline-block;
            margin-top: 6px;
            padding: 8px 12px;
            background-color: #eef2ff;
            color: #3730a3;
            border-radius: 999px;
            font-weight: 700;
            font-size: 14px;
        }

        .inquiry-btn {
            display: inline-block;
            margin-top: 14px;
            padding: 10px 14px;
            background-color: #0f766e;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-size: 14px;
            font-weight: 600;
        }

        .inquiry-btn:hover {
            background-color: #115e59;
        }

        .empty-state {
            background-color: #ffffff;
            border: 1px dashed #cbd5e1;
            border-radius: 16px;
            padding: 40px;
            text-align: center;
            color: #64748b;
            font-size: 15px;
        }

        footer {
            text-align: center;
            padding: 24px;
            color: #94a3b8;
            font-size: 13px;
        }
    </style>
</head>
<body>

<header>
    <div class="brand">
        <h1>DriverDepot</h1>
        <p>Customer Vehicle Search Dashboard</p>
    </div>

    <div class="nav-buttons">
        <%
            String username = (String) session.getAttribute("username");
            Integer userRole = (Integer) session.getAttribute("roleId");

            if (username != null && userRole == 2) {
        %>
            <span class="welcome-text">Welcome, <%= username %></span>
            <a href="<%= request.getContextPath() %>/notifications" class="btn-success">Staff Portal</a>
            <a href="<%= request.getContextPath() %>/signOut" class="btn-danger">Sign Out</a>
        
        
        <%
            } else if(username != null && userRole == 1) {
        %>
            <span class="welcome-text">Welcome, <%= username %></span>
            <a href="<%= request.getContextPath() %>/notificationsByUser" class="btn-success">Customer Portal</a>
            <a href="<%= request.getContextPath() %>/signOut" class="btn-danger">Sign Out</a>
        

        <%
            } else {
        %>
            <a href="<%= request.getContextPath() %>/signIn" class="btn-outline">Sign In</a>
            <a href="<%= request.getContextPath() %>/signUp" class="btn-dark">Sign Up</a>
        <%
            }
        %>
    </div>
</header>

<section class="hero">
    <div class="hero-card">
        <h2>Find the Right Vehicle for You</h2>
        <p>
            Browse available inventory by make, model, year, and price range.
            Use the filters below to narrow your search.
        </p>
    </div>
</section>

<div class="container">

    <% if ("success".equals(request.getParameter("login"))) { %>
        <div class="alert-success">
            Signed in successfully.
        </div>
    <% } %>

    <% if ("success".equals(request.getParameter("logout"))) { %>
        <div class="alert-success">
            Signed out successfully.
        </div>
    <% } %>

    <% if (request.getAttribute("error") != null) { %>
        <div class="alert-error">
            <%= request.getAttribute("error") %>
        </div>
    <% } %>

    <div class="search-card">
        <h3>Search Inventory</h3>
        <p>Enter one or more fields to filter available vehicles.</p>

        <form method="get" action="<%= request.getContextPath() %>/searchVehicles">
            <div class="search-grid">
                <div class="form-group">
                    <label for="make">Make</label>
                    <input type="text" id="make" name="make" placeholder="e.g. Toyota">
                </div>

                <div class="form-group">
                    <label for="model">Model</label>
                    <input type="text" id="model" name="model" placeholder="e.g. Corolla">
                </div>

                <div class="form-group">
                    <label for="year">Year</label>
                    <input type="number" id="year" name="year" placeholder="e.g. 2022">
                </div>

                <div class="form-group">
                    <label for="minPrice">Min Price</label>
                    <input type="number" id="minPrice" name="minPrice" placeholder="e.g. 10000">
                </div>

                <div class="form-group">
                    <label for="maxPrice">Max Price</label>
                    <input type="number" id="maxPrice" name="maxPrice" placeholder="e.g. 30000">
                </div>
            </div>

            <div class="search-actions">
                <button type="submit" class="search-btn">Search Vehicles</button>
                <a href="<%= request.getContextPath() %>/searchVehicles" class="clear-btn">Clear Filters</a>
            </div>
        </form>
    </div>

    <div class="results-header">
        <h3>Available Vehicles</h3>
        <p>Browse the results returned by your search.</p>
    </div>

    <%
        List<VehicleDTO> vehicles = (List<VehicleDTO>) request.getAttribute("vehicles");
        // Integer userRole = (Integer) session.getAttribute("roleId");
        if (vehicles != null) {
            if (!vehicles.isEmpty()) {
    %>
                <div class="vehicle-grid">
                <%
                    for (VehicleDTO v : vehicles) {
                        String vehicleLabel = v.getMake() + " " + v.getModel() + " (" + v.getYear() + ")";
                %>
                    <div class="vehicle-card">
                        <div class="vehicle-top">
                            <h4><%= v.getMake() %> <%= v.getModel() %></h4>

                                <%
                                if (userRole != null){
                                if(userRole == 2){
                                %>
                                    <span>Vehicle ID: <%= v.getId() %></span>
                                <% 
                                }
                                else if(userRole == 1) {
                                    
                                %>
                                    <span></span>
                                <%
                                
                                }
                                }
                                %>
                        </div>
                        <div class="vehicle-body">
                            <p><strong>Year:</strong> <%= v.getYear() %></p>
                            <p><strong>Mileage:</strong> <%= v.getMileage() %> miles</p>
                            <p><strong>Details:</strong> <%= v.getDetails() != null ? v.getDetails() : "No additional details available." %></p>
                            <span class="price-tag">$<%= v.getMsrp() %></span>
                            <br>


                            <% 
                            if (username != null && (userRole == 2 || userRole == 1)) {
                            %>

                            <a class="inquiry-btn"
                               href="<%= request.getContextPath() %>/submitInquiry?vehicle=<%= java.net.URLEncoder.encode(vehicleLabel, "UTF-8") %>">
                               Submit Inquiry
                            </a>

                           <% 
                            }else{
                            %>  
                            <br>
                            <a href="<%= request.getContextPath() %>/signIn" class="inquiry-btn">Sign In To Submit Inquiry</a>

                            <%
                            }
                            %>


                        </div>
                    </div>
                <%
                    }
                %>
                </div>
    <%
            } else {
    %>
                <div class="empty-state">
                    No vehicles found for your search. Try changing the filters.
                </div>
    <%
            }
        } else {
    %>
            <div class="empty-state">
                Start by using the search filters above to view matching vehicles.
            </div>
    <%
        }
    %>

</div>

<footer>
    DriverDepot Inventory Portal
</footer>

</body>
</html>