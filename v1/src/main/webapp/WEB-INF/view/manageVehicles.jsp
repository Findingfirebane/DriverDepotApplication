<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page import="java.util.List" %>
<%@ page import="app.dto.VehicleDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DriverDepot - Manage Vehicles</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }

        body {
            font-family: Arial, sans-serif;
            background-color: #f8fafc;
            color: #1e293b;
        }

        header {
            background-color: #1e293b;
            padding: 18px 36px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .brand h1 { font-size: 22px; color: #ffffff; margin-bottom: 2px; }
        .brand p  { font-size: 12px; color: #94a3b8; }

        .nav-buttons { display: flex; gap: 12px; align-items: center; }

        .nav-buttons a {
            text-decoration: none;
            padding: 9px 16px;
            border-radius: 8px;
            font-size: 13px;
            font-weight: 600;
            transition: 0.2s;
        }

        .btn-light  { background: #fff; color: #1e293b; }
        .btn-light:hover { background: #e2e8f0; }
        .btn-danger { background: #dc2626; color: #fff; }
        .btn-danger:hover { background: #b91c1c; }

        .container { max-width: 1200px; margin: 32px auto; padding: 0 20px; }

        .page-title { font-size: 26px; font-weight: 700; color: #1e293b; margin-bottom: 6px; }
        .page-sub   { font-size: 14px; color: #64748b; margin-bottom: 24px; }

        .alert {
            padding: 12px 16px;
            border-radius: 10px;
            margin-bottom: 20px;
            font-size: 14px;
        }
        .alert-success { background: #f0fdf4; border: 1px solid #bbf7d0; color: #166534; }
        .alert-error   { background: #fef2f2; border: 1px solid #fecaca; color: #991b1b; }

        .card {
            background: #fff;
            border: 1px solid #e2e8f0;
            border-radius: 14px;
            padding: 24px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.04);
            margin-bottom: 28px;
        }

        .card h3 { font-size: 18px; margin-bottom: 18px; color: #1e293b; }

        .form-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 14px;
        }

        .form-grid.wide { grid-template-columns: 1fr; }

        .form-group label {
            display: block;
            font-size: 13px;
            font-weight: 600;
            color: #475569;
            margin-bottom: 5px;
        }

        .form-group input,
        .form-group textarea {
            width: 100%;
            padding: 10px 12px;
            border: 1px solid #cbd5e1;
            border-radius: 8px;
            font-size: 14px;
            background: #fff;
            font-family: inherit;
        }

        .form-group input:focus,
        .form-group textarea:focus {
            outline: none;
            border-color: #475569;
        }

        textarea { resize: vertical; height: 80px; }

        .form-actions { margin-top: 16px; display: flex; gap: 10px; flex-wrap: wrap; }

        .btn-primary {
            padding: 10px 20px;
            background: #0f766e;
            color: #fff;
            border: none;
            border-radius: 8px;
            font-size: 14px;
            font-weight: 600;
            cursor: pointer;
        }
        .btn-primary:hover { background: #115e59; }

        .btn-secondary {
            padding: 10px 20px;
            background: #fff;
            color: #334155;
            border: 1px solid #cbd5e1;
            border-radius: 8px;
            font-size: 14px;
            font-weight: 600;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
        }
        .btn-secondary:hover { background: #f1f5f9; }

        .btn-edit {
            padding: 6px 12px;
            background: #3b82f6;
            color: #fff;
            border: none;
            border-radius: 6px;
            font-size: 13px;
            cursor: pointer;
            text-decoration: none;
        }
        .btn-edit:hover { background: #2563eb; }

        .btn-del {
            padding: 6px 12px;
            background: #dc2626;
            color: #fff;
            border: none;
            border-radius: 6px;
            font-size: 13px;
            cursor: pointer;
            text-decoration: none;
        }
        .btn-del:hover { background: #b91c1c; }

        table {
            width: 100%;
            border-collapse: collapse;
            font-size: 14px;
        }

        th {
            background: #f1f5f9;
            padding: 11px 14px;
            text-align: left;
            font-weight: 600;
            color: #475569;
            border-bottom: 2px solid #e2e8f0;
        }

        td {
            padding: 11px 14px;
            border-bottom: 1px solid #f1f5f9;
            color: #334155;
        }

        tr:last-child td { border-bottom: none; }
        tr:hover td { background: #f8fafc; }

        .stock-badge {
            display: inline-block;
            padding: 2px 10px;
            border-radius: 999px;
            font-size: 12px;
            font-weight: 600;
        }
        .stock-ok  { background: #dcfce7; color: #166534; }
        .stock-low { background: #fef9c3; color: #92400e; }
        .stock-out { background: #fee2e2; color: #991b1b; }

        .empty-state {
            text-align: center;
            padding: 40px;
            color: #94a3b8;
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
        <p>Staff Vehicle Management</p>
    </div>
    <div class="nav-buttons">
        <%
            String username = (String) session.getAttribute("username");
        %>
        <% if (username != null) { %>
            <span style="color:#94a3b8;font-size:13px;">Welcome, <%= username %></span>
        <% } %>
        <a href="<%= request.getContextPath() %>/searchVehicles" class="btn-light">Search Vehicles</a>
        <a href="<%= request.getContextPath() %>/notifications" class="btn-light">Notifications</a>
        <a href="<%= request.getContextPath() %>/signOut" class="btn-danger">Sign Out</a>
    </div>
</header>

<div class="container">

    <div class="page-title">Vehicle Management</div>
    <div class="page-sub">Add, edit, or remove vehicles from the inventory.</div>

    <%-- Flash messages --%>
    <% String msg = request.getParameter("msg"); String err = request.getParameter("error"); %>
    <% if ("added".equals(msg))   { %><div class="alert alert-success">Vehicle added successfully.</div><% } %>
    <% if ("updated".equals(msg)) { %><div class="alert alert-success">Vehicle updated successfully.</div><% } %>
    <% if ("deleted".equals(msg)) { %><div class="alert alert-success">Vehicle deleted successfully.</div><% } %>
    <% if ("addfailed".equals(err))    { %><div class="alert alert-error">Failed to add vehicle. Please try again.</div><% } %>
    <% if ("updatefailed".equals(err)) { %><div class="alert alert-error">Failed to update vehicle. Please try again.</div><% } %>
    <% if ("deletefailed".equals(err)) { %><div class="alert alert-error">Failed to delete vehicle.</div><% } %>
    <% if ("invalid".equals(err))      { %><div class="alert alert-error">Invalid input. Please check all fields.</div><% } %>
    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error"><%= request.getAttribute("error") %></div>
    <% } %>

    <%-- Edit Form (shows only when editing) --%>
    <%
        VehicleDTO editVehicle = (VehicleDTO) request.getAttribute("editVehicle");
        if (editVehicle != null) {
    %>
    <div class="card">
        <h3>✏️ Edit Vehicle — ID: <%= editVehicle.getId() %></h3>
        <form method="post" action="<%= request.getContextPath() %>/manageVehicles">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="<%= editVehicle.getId() %>">
            <div class="form-grid">
                <div class="form-group">
                    <label>Make</label>
                    <input type="text" name="make" value="<%= editVehicle.getMake() %>" required maxlength="25">
                </div>
                <div class="form-group">
                    <label>Model</label>
                    <input type="text" name="model" value="<%= editVehicle.getModel() %>" required maxlength="25">
                </div>
                <div class="form-group">
                    <label>Year</label>
                    <input type="number" name="year" value="<%= editVehicle.getYear() %>" required min="1900" max="2100">
                </div>
                <div class="form-group">
                    <label>Mileage</label>
                    <input type="number" name="mileage" value="<%= editVehicle.getMileage() %>" required min="0">
                </div>
                <div class="form-group">
                    <label>MSRP ($)</label>
                    <input type="number" name="msrp" value="<%= editVehicle.getMsrp() %>" required min="0">
                </div>
                <div class="form-group">
                    <label>Stock</label>
                    <input type="number" name="stock" value="<%= editVehicle.getStock() %>" required min="0">
                </div>
            </div>
            <div class="form-grid wide" style="margin-top:14px;">
                <div class="form-group">
                    <label>Details</label>
                    <textarea name="details" maxlength="500"><%= editVehicle.getDetails() != null ? editVehicle.getDetails() : "" %></textarea>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn-primary">Save Changes</button>
                <a href="<%= request.getContextPath() %>/manageVehicles" class="btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
    <% } else { %>

    <%-- Add New Vehicle Form --%>
    <div class="card">
        <h3>➕ Add New Vehicle</h3>
        <form method="post" action="<%= request.getContextPath() %>/manageVehicles">
            <input type="hidden" name="action" value="add">
            <div class="form-grid">
                <div class="form-group">
                    <label>Make</label>
                    <input type="text" name="make" placeholder="e.g. Toyota" required maxlength="25">
                </div>
                <div class="form-group">
                    <label>Model</label>
                    <input type="text" name="model" placeholder="e.g. Corolla" required maxlength="25">
                </div>
                <div class="form-group">
                    <label>Year</label>
                    <input type="number" name="year" placeholder="e.g. 2023" required min="1900" max="2100">
                </div>
                <div class="form-group">
                    <label>Mileage</label>
                    <input type="number" name="mileage" placeholder="e.g. 15000" required min="0">
                </div>
                <div class="form-group">
                    <label>MSRP ($)</label>
                    <input type="number" name="msrp" placeholder="e.g. 25000" required min="0">
                </div>
                <div class="form-group">
                    <label>Stock</label>
                    <input type="number" name="stock" placeholder="e.g. 3" required min="0">
                </div>
            </div>
            <div class="form-grid wide" style="margin-top:14px;">
                <div class="form-group">
                    <label>Details</label>
                    <textarea name="details" placeholder="Optional description..." maxlength="500"></textarea>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn-primary">Add Vehicle</button>
            </div>
        </form>
    </div>
    <% } %>

    <%-- Vehicle Table --%>
    <div class="card">
        <h3>📋 Current Inventory</h3>
        <%
            List<VehicleDTO> vehicles = (List<VehicleDTO>) request.getAttribute("vehicles");
            if (vehicles != null && !vehicles.isEmpty()) {
        %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Make</th>
                    <th>Model</th>
                    <th>Year</th>
                    <th>Mileage</th>
                    <th>MSRP</th>
                    <th>Stock</th>
                    <th>Details</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            <%
                for (VehicleDTO v : vehicles) {
                    String stockClass = v.getStock() == 0 ? "stock-out" : (v.getStock() <= 2 ? "stock-low" : "stock-ok");
            %>
                <tr>
                    <td><strong><%= v.getId() %></strong></td>
                    <td><%= v.getMake() %></td>
                    <td><%= v.getModel() %></td>
                    <td><%= v.getYear() %></td>
                    <td><%= String.format("%,d", v.getMileage()) %> mi</td>
                    <td>$<%= String.format("%,d", v.getMsrp()) %></td>
                    <td><span class="stock-badge <%= stockClass %>"><%= v.getStock() %></span></td>
                    <td><%= v.getDetails() != null && !v.getDetails().isEmpty() ? v.getDetails().length() > 50 ? v.getDetails().substring(0,50) + "…" : v.getDetails() : "—" %></td>
                    <td style="display:flex;gap:6px;flex-wrap:wrap;">
                        <a href="<%= request.getContextPath() %>/manageVehicles?action=edit&id=<%= v.getId() %>" class="btn-edit">Edit</a>
                        <a href="<%= request.getContextPath() %>/manageVehicles?action=delete&id=<%= v.getId() %>"
                           class="btn-del"
                           onclick="return confirm('Delete <%= v.getMake() %> <%= v.getModel() %> (ID:<%= v.getId() %>)?');">Delete</a>
                    </td>
                </tr>
            <% } %>
            </tbody>
        </table>
        <% } else { %>
            <div class="empty-state">No vehicles in inventory. Add one above.</div>
        <% } %>
    </div>

</div>

<footer>DriverDepot Staff Portal</footer>

</body>
</html>
